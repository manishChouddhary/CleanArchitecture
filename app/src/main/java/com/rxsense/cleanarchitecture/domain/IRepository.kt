package com.rxsense.cleanarchitecture.domain

import com.rxsense.cleanarchitecture.commoncomponents.Resource
import io.reactivex.Single

/**
 *
 */
interface IRepository {
    fun getPosts(): Single<Resource<String>>
}