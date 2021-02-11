package com.rxsense.cleanarchitecture.domain.usecase

import com.rxsense.cleanarchitecture.commoncomponents.Resource
import com.rxsense.cleanarchitecture.commoncomponents.formattedDate
import com.rxsense.cleanarchitecture.datacomponent.model.NetworkPost
import com.rxsense.cleanarchitecture.datacomponent.model.PostListResponse
import com.rxsense.cleanarchitecture.domain.IRepository
import com.rxsense.cleanarchitecture.domain.models.Post
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

/**
 *
 */
class LoadPosts @Inject constructor(private val repository: IRepository) {
    fun getPosts(): Single<Resource<List<Post>>> {
        return repository.getPosts().map(::mapPost)
    }

    private fun mapPost(response: Resource<PostListResponse>): Resource<List<Post>> {
        return when(response){
            is Resource.Success -> {
                makePostList(response.data.data)
            }
            is Resource.Error -> {
                Resource.Error(response.message)
            }
            else -> Resource.Error("Some error occured")
        }
    }

    private fun makePostList(data: List<NetworkPost>?): Resource<List<Post>> {
        if(data==null){
            return Resource.Error("")
        }
        return Resource.Success(data.map { Post(
            "${it.owner.title.capitalize(Locale.ROOT)} ${it.owner.firstName} ${it.owner.lastName}",
            it.text,
            it.publishDate.formattedDate(),
            it.image,
            it.likes
        ) })
    }
}