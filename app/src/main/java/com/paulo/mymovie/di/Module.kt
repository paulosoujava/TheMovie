package com.paulo.mymovie.di

import android.content.Context
import androidx.room.Room
import com.paulo.mymovie.data.repositories.cache.okHttpClient
import com.paulo.mymovie.data.repositories.local.MyMovieDao
import com.paulo.mymovie.data.repositories.local.MyMovieDB

import com.paulo.mymovie.data.repositories.local.impls.DataStoreRepositoryImpl
import com.paulo.mymovie.data.repositories.local.impls.MyMovieLocalRepositoryImpl
import com.paulo.mymovie.data.repositories.network.apis.Api
import com.paulo.mymovie.data.repositories.network.impls.MovieNetworkImpl
import com.paulo.mymovie.domain.contracts.repositories.IDataStoreRepository
import com.paulo.mymovie.domain.contracts.repositories.ILocalRepository
import com.paulo.mymovie.domain.contracts.repositories.IRemoteRepositoryMovie
import com.paulo.mymovie.domain.usecases.favorite.GetAllFavoriteUseCase
import com.paulo.mymovie.domain.usecases.favorite.GetFavoriteUseCase
import com.paulo.mymovie.domain.usecases.favorite.RemoveFavoriteUseCase
import com.paulo.mymovie.domain.usecases.favorite.SaveFavoriteUseCase
import com.paulo.mymovie.domain.usecases.favorite.UseCasesFavorite
import com.paulo.mymovie.domain.usecases.popular.PopularGetAllUseCase
import com.paulo.mymovie.domain.usecases.seeMore.MovieGetAllUseCase
import com.paulo.mymovie.domain.usecases.video.VideoGetAllUseCase
import com.paulo.mymovie.domain.usecases.welcome.CompletedWelcomeGetUseCase
import com.paulo.mymovie.domain.usecases.welcome.GetWelcomeGetUseCase
import com.paulo.mymovie.domain.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton




@Module
@InstallIn(SingletonComponent::class)
object Module {
    //********************************************************
    // PREFERENCES
    //********************************************************
    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) = DataStoreRepositoryImpl(context = context)


    //********************************************************
    // USE CASES
    //********************************************************
    @Provides
    @Singleton
    fun provideTrendingUseCases(
        repository: IRemoteRepositoryMovie
    ) = MovieGetAllUseCase(repository = repository)

    @Provides
    @Singleton
    fun providePopularUseCases(
        repository: IRemoteRepositoryMovie
    ) = PopularGetAllUseCase(repository = repository)

    @Provides
    @Singleton
    fun provideVideoUseCases(
        repository: IRemoteRepositoryMovie
    ) = VideoGetAllUseCase(repository = repository)

    @Provides
    @Singleton
    fun provideGetWelcomeUseCases(
        repository: IDataStoreRepository
    ) = GetWelcomeGetUseCase(repository = repository)

    @Provides
    @Singleton
    fun provideCompletedWelcomeUseCases(
        repository: IDataStoreRepository
    ) = CompletedWelcomeGetUseCase(repository = repository)

    @Provides
    @Singleton
    fun provideFavoriteUseCases(
        repository: ILocalRepository
    ) = UseCasesFavorite(
        save = SaveFavoriteUseCase(repository = repository),
        get = GetAllFavoriteUseCase(repository = repository),
        remove = RemoveFavoriteUseCase(repository = repository),
        byCode = GetFavoriteUseCase(repository = repository)
    )


    //********************************************************
    // RETROFIT
    //********************************************************

    @Provides
    @Singleton
    fun provideApi(@ApplicationContext context: Context): Api {
        return Retrofit.Builder()
            .client(okHttpClient(context))
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    //********************************************************
    // REPOSITORIES
    //********************************************************
    @Provides
    @Singleton
    fun provideRemoteRepositoryDataSource(@ApplicationContext appContext: Context): IDataStoreRepository {
        return DataStoreRepositoryImpl(appContext)
    }

    @Provides
    @Singleton
    fun provideRemoteRepositoryTrending(api: Api): IRemoteRepositoryMovie {
        return MovieNetworkImpl(api)
    }

    //********************************************************
    // ROOM
    //********************************************************

    @Provides
    fun provideMyMovieDb(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context,
        MyMovieDB::class.java,
        Constants.MOVIE_DB
    ).build()

    @Provides
    fun provideMyMovieDao(
        db: MyMovieDB
    ) = db.myMovieDao()

    @Provides
    fun provideMyMovieRepository(
        dao: MyMovieDao
    ): ILocalRepository = MyMovieLocalRepositoryImpl(
        dao = dao
    )

}
