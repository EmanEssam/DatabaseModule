package com.test.databasemodule.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.databasemodule.Post


@Database(
    entities = [Post::class],
    version = DatabaseMigrations.DB_VERSION
)
abstract class AppDatabase : RoomDatabase() {


    companion object {
        const val DB_NAME = "database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).addMigrations(*DatabaseMigrations.MIGRATIONS).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
