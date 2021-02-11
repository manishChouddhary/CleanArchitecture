package com.rxsense.cleanarchitecture.datacomponent.networkservice

import com.rxsense.cleanarchitecture.datacomponent.model.PostListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 *
 */
interface IService {
    @GET("/data/api/post")
    fun getPosts(@QueryMap map: Map<String, Int>): Single<PostListResponse>
}