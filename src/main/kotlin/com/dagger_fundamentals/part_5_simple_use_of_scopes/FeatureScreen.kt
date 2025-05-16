package com.dagger_fundamentals.part_5_simple_use_of_scopes

import dagger.Binds
import dagger.Module
import javax.inject.Inject

@Module
interface FeatureScreenModule {
    @Binds
    fun bindPresenter(impl: FeatureScreenPresenter): FeatureScreenContract.Presenter

    @Binds
    fun bindScreen(impl: TicketsScreen): FeatureScreenContract.Screen
}

interface FeatureScreenContract {
    interface Presenter {
        fun present()
    }

    interface Screen {
        fun show()
    }
}

class FeatureScreenPresenter @Inject constructor(
    private val featureRepo: FeatureRepo,
    private val screen: FeatureScreenContract.Screen,
) : FeatureScreenContract.Presenter {
    override fun present() {
        featureRepo.getFeatureData("1")
        // Do some stuff
        screen.show()
    }
}

class TicketsScreen @Inject constructor() : FeatureScreenContract.Screen {
    override fun show() {
        print("Showing data")
    }
}
