package com.test.databasemodule.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.databasemodule.Post
import com.test.databasemodule.data.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(
    private val postsRepository: PostsRepository
) : ViewModel() {

    private val _postsLiveData = MutableLiveData<List<Post>>()

    val postsLiveData: LiveData<List<Post>>
        get() = _postsLiveData

    fun getPosts() {
        viewModelScope.launch {
            postsRepository.getAllPosts().collect {
                _postsLiveData.value = it
            }
        }
    }

    suspend fun addPostsToDb(posts: List<Post>) {

        viewModelScope.launch {
            postsRepository.addPosts(posts)
        }
    }
}
