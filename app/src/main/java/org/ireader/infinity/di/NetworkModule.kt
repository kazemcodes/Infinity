package org.ireader.infinity.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.ireader.data.local.dao.RemoteKeysDao
import org.ireader.data.repository.RemoteKeyRepositoryImpl
import org.ireader.domain.repository.RemoteKeyRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {



    @Provides
    @Singleton
    fun provideRemoteKeyRepository(
        remoteKeysDao: RemoteKeysDao,
    ): RemoteKeyRepository {
        return RemoteKeyRepositoryImpl(
            dao = remoteKeysDao,
        )
    }






}
