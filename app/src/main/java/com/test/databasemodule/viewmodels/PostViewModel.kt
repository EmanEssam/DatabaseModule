package com.test.databasemodule.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.test.databasemodule.Post
import com.test.databasemodule.data.local.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject internal constructor(
    postRepository: PostRepository
) : ViewModel() {
    val posts: LiveData<List<Post>> =
        postRepository.getPosts().asLiveData()
}
