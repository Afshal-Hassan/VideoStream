package com.youtube.login.config

import com.youtube.login.repos.LoginRepo
import com.youtube.login.services.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RepoModule {


    @Singleton
    @Provides
    fun provideLoginRepo(loginService: LoginService): LoginRepo {
        return LoginRepo(loginService)
    }
}