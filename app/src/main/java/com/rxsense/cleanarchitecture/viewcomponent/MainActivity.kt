package com.rxsense.cleanarchitecture.viewcomponent

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rxsense.cleanarchitecture.R
import com.rxsense.cleanarchitecture.commoncomponents.Resource
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val mainViewModel by viewModels<MainViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        mainViewModel.getPosts()
        observeLiveData()
    }

    private fun observeLiveData() {
        mainViewModel.postsLiveData.observe(
            this,
            {
                when(it)
                {
                    is Resource.Success ->  Log.d("Tag", it.data)
                    else -> Unit
                }
            })
    }
}