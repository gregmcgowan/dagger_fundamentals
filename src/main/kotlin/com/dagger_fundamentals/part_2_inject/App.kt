package com.dagger_fundamentals.part_2_inject

import dagger.Component
import javax.inject.Inject

fun main() {
    App().start()
}

class App {
    @Inject
    lateinit var homeScreenPresenter: HomeScreenPresenter

    fun start() {
        DaggerAppComponent
            .create()
            .inject(this)

        homeScreenPresenter.present()
    }
}

@Component
interface AppComponent {
    fun inject(app: App)
}

class HomeScreenPresenter @Inject constructor(
    private val homeScreen: HomeScreen,
) {
    fun present() {
        homeScreen.show()
    }
}

class HomeScreen @Inject constructor() {
    fun show() {
        print("Showing main screen!")
    }
}
