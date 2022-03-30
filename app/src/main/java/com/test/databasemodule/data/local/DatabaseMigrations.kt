package com.test.databasemodule.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.test.databasemodule.Post


object DatabaseMigrations {
    const val DB_VERSION = 0

    val MIGRATIONS: Array<Migration>
        get() = arrayOf<Migration>(
            migration12()
        )

    private fun migration12(): Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ${Post::class.simpleName} ADD COLUMN body TEXT")
        }
    }
}
