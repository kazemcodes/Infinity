package org.ireader.presentation.feature_detail.presentation.book_detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.ireader.core.R
import org.ireader.core.utils.UiText
import org.ireader.core.utils.removeSameItemsFromList
import org.ireader.core_api.log.Log
import org.ireader.core_api.source.Source
import org.ireader.core_ui.viewmodel.BaseViewModel
import org.ireader.domain.catalog.interactor.GetLocalCatalog
import org.ireader.domain.models.entities.Book
import org.ireader.domain.models.entities.Chapter
import org.ireader.domain.models.entities.updateBook
import org.ireader.domain.use_cases.local.LocalGetChapterUseCase
import org.ireader.domain.use_cases.local.LocalInsertUseCases
import org.ireader.domain.use_cases.remote.RemoteUseCases
import org.ireader.domain.use_cases.services.ServiceUseCases
import java.util.*
import javax.inject.Inject


@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val localInsertUseCases: LocalInsertUseCases,
    private val getChapterUseCase: LocalGetChapterUseCase,
    private val getBookUseCases: org.ireader.domain.use_cases.local.LocalGetBookUseCases,
    private val remoteUseCases: RemoteUseCases,
    savedStateHandle: SavedStateHandle,
    private val getLocalCatalog: GetLocalCatalog,
    private val state: DetailStateImpl,
    private val chapterState: ChapterStateImpl,
    private val serviceUseCases: ServiceUseCases,
) : BaseViewModel(), DetailState by state, ChapterState by chapterState {

    var getBookDetailJob: Job? = null
    var getChapterDetailJob: Job? = null


    var initBooks = false
    var initChapters = false
    init {
        val bookId = savedStateHandle.get<Long>("bookId")
        val sourceId = savedStateHandle.get<Long>("sourceId")
        if (bookId != null && sourceId != null) {
            val source = getLocalCatalog.get(sourceId)?.source
            this.source = source
            toggleBookLoading(true)
            chapterIsLoading   = true
            subscribeBook(bookId =bookId , onSuccess = { book ->
                toggleBookLoading(false)
                if (!initBooks) {
                    initBooks = true
                    if (book.lastUpdated < 1L && source != null) {
                        getRemoteBookDetail(book, source)
                        getRemoteChapterDetail(book, source)
                    } else {
                        toggleBookLoading(false)
                        chapterIsLoading   = false
                    }
                }
            })
            subscribeChapters(bookId =bookId,onSuccess = {

            })
//            viewModelScope.launch {
//                getLocalBookById(bookId, source)
//                getLocalChaptersByBookId(bookId = bookId)
//            }

        } else {
            viewModelScope.launch {
                showSnackBar(UiText.StringResource(R.string.something_is_wrong_with_this_book))
            }
        }
    }

    private fun subscribeBook(bookId: Long, onSuccess:suspend (Book) -> Unit) {
            getBookUseCases.subscribeBookById(bookId)
                .onEach { snapshot->
                    snapshot?.let { book ->
                        setDetailBook(book)
                        onSuccess(book)
                    }
                }.launchIn(viewModelScope)
    }
    private fun subscribeChapters(bookId: Long, onSuccess:suspend (List<Chapter>) -> Unit) {
            getChapterUseCase.subscribeChaptersByBookId(bookId)
                .onEach { snapshot->
                    if (snapshot.isNotEmpty()) {
                        chapters = snapshot
                        onSuccess(snapshot)
                    }
                }.launchIn(viewModelScope)

    }

//    suspend fun getLocalBookById(bookId: Long, source: Source?) {
//        this.toggleLocalLoading(true)
//        clearBookError()
//        viewModelScope.launch {
//            val book = getBookUseCases.findBookById(bookId)
//            if (book != null) {
//                toggleLocalLoading(false)
//                clearBookError()
//                setDetailBook(book)
//                toggleInLibrary(book.favorite)
//                if ((book.lastUpdated < 1L) && !state.detailIsRemoteLoaded && source != null) {
//                    getRemoteBookDetail(book, source)
//                    getRemoteChapterDetail(book, source)
//                }
//                isLocalBookLoaded(true)
//            } else {
//                toggleLocalLoading(false)
//                showSnackBar(UiText.StringResource(R.string.no_book_found_error))
//            }
//        }
//    }


//    suspend fun getLocalChaptersByBookId(bookId: Long) {
//        clearChapterError()
//        this.toggleChaptersLoading(true)
//        viewModelScope.launch {
//            val chapters = getChapterUseCase.findChaptersByBookId(bookId)
//            if (chapters.isNotEmpty()) {
//                clearChapterError()
//                setStateChapters(chapters)
//                toggleAreChaptersLoaded(true)
//            }
//            toggleChaptersLoading(false)
//        }
//    }


    suspend fun getRemoteBookDetail(book: Book, source: Source) {
        toggleBookLoading(true)
        getBookDetailJob?.cancel()
        getBookDetailJob = viewModelScope.launch(Dispatchers.IO) {
            remoteUseCases.getBookDetail(
                book = book,
                source = source,
                onError = { message ->
                    toggleBookLoading(false)
                    insertBookDetailToLocal(state.book!!)
                    if (message != null) {
                        Log.error { message.toString() }
                        showSnackBar(message)
                    }
                },
                onSuccess = { resultBook ->
                    if (state.book != null) {
                        setDetailBook(
                            book = updateBook(
                                resultBook,
                                book
                            )
                        )
                        toggleBookLoading(false)
                        insertBookDetailToLocal(state.book!!)
                    }
                }

            )
        }


    }

    suspend fun getRemoteChapterDetail(book: Book, source: Source) {
        chapterIsLoading   = true
        getChapterDetailJob?.cancel()
        getChapterDetailJob = viewModelScope.launch {
            remoteUseCases.getRemoteChapters(
                book = book,
                source = source,
                onError = { message ->
                    Log.error { message.toString() }
                    showSnackBar(message)
                    chapterIsLoading   = false
                },
                onSuccess = { result ->
                    val uniqueList =
                        removeSameItemsFromList(chapterState.chapters,
                            result) {
                            it.link
                        }
                    this@BookDetailViewModel.chapters = uniqueList
                    if (uniqueList.isNotEmpty()) {
                        withContext(Dispatchers.IO) {
                            localInsertUseCases.updateChaptersUseCase(book.id, uniqueList)
                        }

                    }
                    chapterIsLoading   = false
                }
            )
        }
    }


    private fun insertBookDetailToLocal(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            localInsertUseCases.insertBook(book.copy(dataAdded = Calendar.getInstance().timeInMillis))
        }
    }

    private fun updateChaptersEntity(inLibrary: Boolean, bookId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            localInsertUseCases.insertChapters(chapterState.chapters.map {
                it.copy(bookId = bookId)
            })
        }
    }

    fun toggleInLibrary(book: Book) {
        this.inLibraryLoading = true
        viewModelScope.launch(Dispatchers.IO) {
            if (!book.favorite) {
                insertBookDetailToLocal(
                    book.copy(
                        id = book.id,
                        favorite = true,
                        dataAdded = Calendar.getInstance().timeInMillis,
                    )
                )
                updateChaptersEntity(true, book.id)
            } else {
                insertBookDetailToLocal((
                        book.copy(
                            id = book.id,
                            favorite = false,
                        )))
                updateChaptersEntity(false, book.id)
            }
            this@BookDetailViewModel.inLibraryLoading = false
        }
    }


    fun startDownloadService(book: Book) {
        serviceUseCases.startDownloadServicesUseCase(bookIds = longArrayOf(book.id))
    }

    private fun toggleBookLoading(isLoading: Boolean) {
        this.detailIsLoading = isLoading
    }


    private fun setDetailBook(book: Book) {
        this.book = book
    }


    override fun onDestroy() {
        getBookDetailJob?.cancel()
        super.onDestroy()
    }
}


