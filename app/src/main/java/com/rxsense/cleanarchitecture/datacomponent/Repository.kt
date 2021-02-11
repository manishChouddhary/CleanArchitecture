package com.rxsense.cleanarchitecture.datacomponent

import com.rxsense.cleanarchitecture.commoncomponents.Resource
import com.rxsense.cleanarchitecture.datacomponent.model.PostListResponse
import com.rxsense.cleanarchitecture.datacomponent.networkservice.IService
import com.rxsense.cleanarchitecture.domain.IRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 *
 */
class Repository @Inject constructor(private val service: IService) : IRepository {
    override fun getPosts(): Single<Resource<PostListResponse>> {
        val map = mapOf("limit" to 10)
        return service.getPosts(map)
            .subscribeOn(Schedulers.io())
            .map { response ->
                if (response.data.isNullOrEmpty())
                    return@map Resource.Error("No Posts")
                return@map Resource.Success(response)
            }
            .onErrorReturn {
                return@onErrorReturn Resource.Error<PostListResponse>(
                    message = it.message,
                    error = it
                )
            }
    }
}