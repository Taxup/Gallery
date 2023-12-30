package com.example.gallery.data

import com.example.gallery.data.models.FeedNetworkModel

interface Api {


    suspend fun getFeed(): List<FeedNetworkModel>
}

//todo temp
class ApiImpl:Api {
    override suspend fun getFeed(): List<FeedNetworkModel> {
        TODO("Not yet implemented")
    }
}