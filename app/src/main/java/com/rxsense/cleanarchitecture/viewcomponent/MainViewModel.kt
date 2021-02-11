package com.rxsense.cleanarchitecture.viewcomponent

import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 *
 */
class MainViewModel @Inject constructor(postsUseCase: LoadPosts):ViewModel() {
    fun getPosts() {

    }
}