package com.rxsense.cleanarchitecture.viewcomponent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.ViewSizeResolver
import com.rxsense.cleanarchitecture.R
import com.rxsense.cleanarchitecture.databinding.ItemPostBinding
import com.rxsense.cleanarchitecture.domain.models.Post

/**
 * Post Adaptor with data binding
 */
class PostsAdapter(val imageLoader: ImageLoader) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    private val list by lazy { ArrayList<Post>() }

    fun setList(posts: List<Post>) {
        if (posts.isNullOrEmpty()) return
        list.clear()
        list.addAll(posts)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.post = post
            val request = ImageRequest.Builder(binding.root.context)
                .data(post.imageUrl)
                .scale(Scale.FIT)
                .size(ViewSizeResolver(binding.ivPost))
                .error(R.drawable.ic_error_placeholder)
                .placeholder(R.drawable.ic_placeholder)
                .target(
                    onSuccess = { drawable ->
                        binding.ivPost.apply {
                            setImageDrawable(drawable)
                        }
                    }
                )
                .build()
            imageLoader.enqueue(request)
        }
    }

}
