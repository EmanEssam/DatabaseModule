package com.test.databasemodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.annotation.DaoGenerator

class MainActivity : AppCompatActivity() {

    @DaoGenerator
    class Post
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}