package ir.kazemcodes.infinity.core.domain.use_cases.remote

import ir.kazemcodes.infinity.core.data.network.models.Source
import ir.kazemcodes.infinity.core.domain.models.Book
import ir.kazemcodes.infinity.core.domain.models.Chapter
import ir.kazemcodes.infinity.core.domain.repository.RemoteRepository
import ir.kazemcodes.infinity.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.select.Selector
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class GetRemoteChapters(private val remoteRepository: RemoteRepository) {
    operator fun invoke(book: Book, source: Source): Flow<Resource<List<Chapter>>> =
        flow {
            try {
                emit(Resource.Loading())
                Timber.d("Timber: GetRemoteChaptersUseCase was Called")
                val chapters = mutableListOf<Chapter>()
                var currentPage = 1

                var hasNextPage = true

                while (hasNextPage) {
                    Timber.d("Timber: GetRemoteChaptersUseCase was with pages $currentPage Called")
                    val chaptersPage = source.fetchChapters(book = book, page = currentPage)
                    chapters.addAll(chaptersPage.chapters)
                    hasNextPage = chaptersPage.hasNextPage
                    currentPage += 1
                }
                emit(Resource.Success<List<Chapter>>(chapters))
                Timber.d("Timber: GetRemoteChaptersUseCase was Finished Successfully")

            } catch (e: HttpException) {
                emit(
                    Resource.Error<List<Chapter>>(
                        message = e.localizedMessage ?: "An Unexpected Error Occurred."
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error<List<Chapter>>(message = "Couldn't Read Remote Server, Check Your Internet Connection."))
            } catch (e: Selector.SelectorParseException) {
                emit(Resource.Error<List<Chapter>>(message = "Source is not working."))
            } catch (e: Exception) {
                emit(Resource.Error<List<Chapter>>(message = e.localizedMessage
                    ?: "An Unexpected Error Occurred"))
            }
        }
}