package com.dagger_fundamentals.part_1_without_dagger

fun main() {
    App().start()
}

class App {
    lateinit var presenter: HomeScreenPresenter

    fun start() {
        presenter = HomeScreenPresenter(HomeScreen())
        presenter.present()
    }
}

class HomeScreen {
    fun show() {
        print("Showing main screen!")
    }
}

class HomeScreenPresenter(private val homeScreen: HomeScreen) {
    fun present() {
        homeScreen.show()
    }
}
