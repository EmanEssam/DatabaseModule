package com.test.databasemodule.di

import android.app.Application
import com.test.databasemodule.App
import com.test.databasemodule.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(app: App) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun providePostsDao(database: AppDatabase) = database.getPostsDao()


    @Singleton
    @Provides
    fun provideApp() = App()


}
