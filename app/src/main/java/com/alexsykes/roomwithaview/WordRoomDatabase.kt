package com.alexsykes.roomwithaview

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
public  abstract  class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase {
            // If the INSTANCE is not null, then return it
            // If it is null, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                WordRoomDatabase::class.java,
                "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}