package com.dagger_fundamentals.part_4_provides_and_modules

import dagger.Component
import javax.inject.Inject

fun main() {
    App().start()
}

class App {
    @Inject
    lateinit var homeScreenPresenter: HomeScreenContract.Presenter

    fun start() {
        DaggerAppComponent.create().inject(this)
        homeScreenPresenter.present()
    }
}

@Component(
    modules = [
        HomeScreenModule::class,
        UserRepoModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {
    fun inject(app: App)
}
