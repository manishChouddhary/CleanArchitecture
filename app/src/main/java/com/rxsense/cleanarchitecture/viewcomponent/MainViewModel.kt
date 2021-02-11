package com.rxsense.cleanarchitecture.viewcomponent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rxsense.cleanarchitecture.commoncomponents.Resource
import com.rxsense.cleanarchitecture.datacomponent.model.Post
import com.rxsense.cleanarchitecture.domain.usecase.LoadPosts
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 *
 */
class MainViewModel @Inject constructor(private val postsUseCase: LoadPosts) : ViewModel() {
    private val compositeDisposable by lazy { CompositeDisposable() }

    val postsLiveData by lazy { MutableLiveData<Resource<List<Post>>>() }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getPosts() {
        compositeDisposable.add(
            postsUseCase.getPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    postsLiveData.postValue(it)
                }, {
                    postsLiveData.postValue(Resource.Error("Error"))
                })
        )
    }
}