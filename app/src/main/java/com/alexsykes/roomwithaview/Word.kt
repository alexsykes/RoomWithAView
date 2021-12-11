package com.alexsykes.roomwithaview

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
class Word(@PrimaryKey @ColumnInfo(name = "word") val word: String,
           @ColumnInfo(name="meaning") val meaning: String?,
           @ColumnInfo(name="synonym") val synonym: String?,
           @ColumnInfo(name="antonym") val antonym: String?
) {

}