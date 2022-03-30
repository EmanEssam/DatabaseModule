package com.test.databasemodule.data.local

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PostRepository @Inject constructor(private val postDao: PostDao) {

    fun getPosts() = postDao.getAllPosts()
    fun getPost(plantId: Int) = postDao.getPostById(plantId)

}
