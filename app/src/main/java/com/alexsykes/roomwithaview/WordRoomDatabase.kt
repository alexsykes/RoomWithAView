package com.alexsykes.roomwithaview

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
public  abstract  class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.wordDao())
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            // Delete all content here.
            wordDao.deleteAll()

            // Add sample words.
            var word = Word("Hello")
            wordDao.insert(word)
            word = Word("World!")
            wordDao.insert(word)

            // TODO: Add your own words!
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context,
        scope: CoroutineScope
        ): WordRoomDatabase {
            // If the INSTANCE is not null, then return it
            // If it is null, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                WordRoomDatabase::class.java,
                "word_database"
                ).addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }



}