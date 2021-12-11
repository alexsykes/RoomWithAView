package com.alexsykes.roomwithaview

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {

    var applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy {WordRepository(database.wordDao())}
}