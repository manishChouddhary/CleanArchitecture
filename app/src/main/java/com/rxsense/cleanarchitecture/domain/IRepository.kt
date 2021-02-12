package com.rxsense.cleanarchitecture.domain

import com.rxsense.cleanarchitecture.commoncomponents.Resource
import com.rxsense.cleanarchitecture.datacomponent.model.PostListResponse
import io.reactivex.Single

/**
 * Domain layer abstraction for repository work flow
 */
interface IRepository {
    fun getPosts(): Single<Resource<PostListResponse>>
}