package com.example.cleanarchitecture.di.component

import com.example.cleanarchitecture.di.module.LandingPageModule
import com.example.cleanarchitecture.ui.landingpage.LandingPagePresenter
import dagger.Component

@Component(
    modules = [LandingPageModule::class],
    dependencies = [ApplicationComponent::class]
)
interface LandingPageComponent {
    fun presenter(): LandingPagePresenter
}