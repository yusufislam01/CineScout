package com.yusufislamaltunkaynak.cinescout.network.di

import com.yusufislamaltunkaynak.cinescout.network.repository.MovieRepository
import com.yusufislamaltunkaynak.cinescout.network.repository.MovieRepositoryImpl
import com.yusufislamaltunkaynak.cinescout.network.service.MovieAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieAPI): MovieRepository {
        return MovieRepositoryImpl(api)
    }
}
