package com.rxsense.cleanarchitecture.viewcomponent

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coil.ImageLoader
import com.rxsense.cleanarchitecture.commoncomponents.Resource
import com.rxsense.cleanarchitecture.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Main view for the application
 */
class MainActivity : AppCompatActivity() {

    private lateinit var adapter: PostsAdapter
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var imageLoader: ImageLoader

    val mainViewModel by viewModels<MainViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        mainViewModel.getPosts()
        observeLiveData()
    }

    private fun initView() {
        binding.rvPost.layoutManager = LinearLayoutManager(this)
        adapter = PostsAdapter(imageLoader)
        binding.rvPost.adapter = adapter
    }

    private fun observeLiveData() {
        mainViewModel.postsLiveData.observe(
            this,
            {
                when (it) {
                    is Resource.Success -> {
                        adapter.setList(it.data)
                        adapter.notifyDataSetChanged()
                    }
                    else -> Unit
                }
            })
    }
}