package com.dagger_fundamentals.part_5_simple_use_of_scopes

import dagger.Binds
import dagger.Module
import okhttp3.OkHttpClient
import javax.inject.Inject

@Module
interface FeatureRepoModule {
    @Binds
    fun bindRepo(impl: RemoteFeatureRepo): FeatureRepo
}

interface FeatureRepo {
    fun getFeatureData(userID: String): List<FeatureData>
}

class RemoteFeatureRepo @Inject constructor(
    private val okHttpClient: OkHttpClient,
) : FeatureRepo {
    init {
        println("okHttpClient = $okHttpClient")
    }

    override fun getFeatureData(userID: String): List<FeatureData> {
        // nonsense okhttp call so that it is used
        okHttpClient.cache
        return listOf(FeatureData("return"))
    }
}

data class FeatureData(val name: String)
