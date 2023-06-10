package com.example.lab6

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6.DbHelper.Companion.KEY_NAME
import com.example.lab6.DbHelper.Companion.KEY_SURNAME


class MainActivity : AppCompatActivity() {
    var etName: EditText? = null
    var etSurname: EditText? = null
    var tvOutput: TextView? = null
    var dbHelper: DbHelper? = null
    var contentValues = ContentValues()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etName = findViewById<View>(R.id.nameET) as EditText
        etSurname = findViewById<View>(R.id.surnameET) as EditText
        tvOutput = findViewById<View>(R.id.outputTV) as TextView

        dbHelper = DbHelper(applicationContext)
    }

    fun clear(v: View?) {
        val database: SQLiteDatabase? = dbHelper?.writableDatabase
        database?.delete(DbHelper.TABLE_PEOPLE, null, null)
        tvOutput!!.text = ""
        dbHelper?.close()
    }

    fun write(v: View?) {
        val database: SQLiteDatabase? = dbHelper?.writableDatabase
        contentValues.put(KEY_NAME, etName?.text.toString())
        contentValues.put(KEY_SURNAME, etSurname?.text.toString())
        database?.insert(DbHelper.TABLE_PEOPLE, null, contentValues);
        dbHelper?.close()
    }

    @SuppressLint("SetTextI18n")
    fun read(v: View?) {
        val db: SQLiteDatabase? = dbHelper?.writableDatabase
        val cursor: Cursor = db!!.query(
            DbHelper.TABLE_PEOPLE, arrayOf<String>(DbHelper.KEY_ID,DbHelper.KEY_NAME, DbHelper.KEY_SURNAME),
            null, null, null, null, null
        )
        cursor.moveToFirst()
        var old = ""
        tvOutput!!.text = ""
        for (i in 0 until cursor.count) {
            old = tvOutput!!.text.toString()
            tvOutput!!.text = old + cursor.getString(0) + " " +cursor.getString(1) +" "+ cursor.getString(2) + "\n"
            cursor.moveToNext()
        }
        dbHelper?.close()
    }

}
