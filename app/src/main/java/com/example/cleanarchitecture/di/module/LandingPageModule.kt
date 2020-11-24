package com.example.cleanarchitecture.di.module

import com.example.cleanarchitecture.ui.landingpage.LandingPagePresenter
import dagger.Module
import dagger.Provides

@Module
object LandingPageModule {

    @JvmStatic
    @Provides
    fun providePresenter(
    ): com.example.cleanarchitecture.ui.landingpage.LandingPagePresenter =
        com.example.cleanarchitecture.ui.landingpage.LandingPagePresenter()
}