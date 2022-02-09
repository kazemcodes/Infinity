package org.ireader.data.repository

import org.ireader.domain.local.BookDatabase
import org.ireader.domain.repository.LocalChapterRepository
import org.ireader.domain.repository.PreferencesHelper
import org.ireader.domain.repository.Repository
import org.ireader.infinity.core.domain.repository.LocalBookRepository
import org.ireader.infinity.core.domain.repository.RemoteRepository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    override val localBookRepository: LocalBookRepository,
    override val localChapterRepository: LocalChapterRepository,
    override val preferencesHelper: PreferencesHelper,
    override val remoteRepository: RemoteRepository,
    override val database: BookDatabase,
) : Repository