package com.dagger_fundamentals.part_5_simple_use_of_scopes

import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton

fun main() {
    App().start()
}

class App {
    @Inject
    lateinit var homeScreenPresenter: HomeScreenContract.Presenter

    @Inject
    lateinit var featureScreenPresenter: FeatureScreenContract.Presenter

    fun start() {
        DaggerAppComponent.create().inject(this)
        showHomeScreen()
    }

    fun showHomeScreen() {
        homeScreenPresenter.present()
    }

    // not used yet but will be
    fun showFeature() {
        featureScreenPresenter.present()
    }
}

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        UserRepoModule::class,
        FeatureRepoModule::class,
        HomeScreenModule::class,
        FeatureScreenModule::class,
    ],
)
interface AppComponent {
    fun inject(app: App)
}

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttp() = OkHttpClient()
}
