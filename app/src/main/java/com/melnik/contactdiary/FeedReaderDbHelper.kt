package com.melnik.contactdiary

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class FeedReaderDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(ContactDatabase.SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) { db.execSQL(ContactDatabase.SQL_UPDATE_2) }
        if (oldVersion < 3) {
            db.execSQL(ContactDatabase.SQL_UPDATE_3)
            MigrationTools().migrateTo3(db)
        }
        if (oldVersion < 4) {
//          Rename table to temporary name
            db.execSQL(ContactDatabase.SQL_UPDATE_4_PART1)
//          Create new table
            db.execSQL(ContactDatabase.SQL_CREATE_ENTRIES)
//          Move relevant information
            db.execSQL(ContactDatabase.SQL_UPDATE_4_PART2)
//          Create new information (EndTime = BeginTime + Duration)
            MigrationTools().migrateTo4(db)
//          Remove old table
            db.execSQL(ContactDatabase.SQL_UPDATE_4_PART3)
        }
        if (oldVersion < 5) {
//          Creation of each column must have its own instruction
            db.execSQL(ContactDatabase.SQL_UPDATE_5_PART1)
            db.execSQL(ContactDatabase.SQL_UPDATE_5_PART2)
            MigrationTools().migrateTo5(db)
        }
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 5
        const val DATABASE_NAME = "ContactDiary.db"
    }

    fun viewData(onlyRisky: Boolean): Cursor {
        val db = this.readableDatabase

        val query = if (onlyRisky) {
            "Select * from " + ContactDatabase.ContactDatabase.FeedEntry.TABLE_NAME +
                    " WHERE " + ContactDatabase.ContactDatabase.FeedEntry.CLOSECONTACT_COLUMN + "!=0" +
                    " ORDER BY " + ContactDatabase.ContactDatabase.FeedEntry.TIME_BEGIN_COLUMN + " DESC"
        } else {
            "Select * from " + ContactDatabase.ContactDatabase.FeedEntry.TABLE_NAME +
                    " ORDER BY " + ContactDatabase.ContactDatabase.FeedEntry.TIME_BEGIN_COLUMN + " DESC"
        }

        return db.rawQuery(query, null)
    }

}
