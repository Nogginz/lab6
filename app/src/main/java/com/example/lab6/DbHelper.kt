package com.example.lab6

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE_PEOPLE($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, $KEY_NAME TEXT, $KEY_SURNAME TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PEOPLE")
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "peopleListDB.db"
        const val TABLE_PEOPLE = "peopleList"
        const val DATABASE_VERSION = 1
        const val KEY_ID = "_id"
        const val KEY_NAME = "name"
        const val KEY_SURNAME = "surname"
    }
}
