package com.rxsense.cleanarchitecture.domain.usecase

import com.rxsense.cleanarchitecture.domain.IRepository
import javax.inject.Inject

/**
 *
 */
class LoadPosts @Inject constructor(val repository: IRepository) {
    fun getPosts(){
        repository.getPosts()
    }
}