package com.rxsense.cleanarchitecture.domain

import com.rxsense.cleanarchitecture.commoncomponents.Resource
import com.rxsense.cleanarchitecture.datacomponent.model.PostListResponse
import io.reactivex.Single

/**
 *
 */
interface IRepository {
    fun getPosts(): Single<Resource<PostListResponse>>
}