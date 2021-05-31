package com.melnik.contactdiary

import android.provider.BaseColumns

class ContactDatabase {
    object ContactDatabase {
        // Table contents are grouped together in an anonymous object.
        object FeedEntry : BaseColumns {
            const val TABLE_NAME = "ContactDB"
            const val TYPE_COLUMN = "Type"
            const val NAME_COLUMN = "Name"
            const val PLACE_COLUMN = "Place"
            const val TIMESTAMP_COLUMN = "Timestamp"
            const val DURATION_COLUMN = "Duration"
            const val PHONE_COLUMN = "Phone"
            const val RELATIVE_COLUMN = "Relative"
            const val COMPANIONS_COLUMN = "Companions"
            const val CLOSECONTACT_COLUMN = "CloseContact"
            const val ENCOUNTER_COLUMN = "EncounterType"
            const val NOTES_COLUMN = "Notes"
            const val TIME_BEGIN_COLUMN = "BeginTime"
            const val TIME_END_COLUMN = "EndTime"
            const val MASK_COLUMN = "Masks"
            const val VENTILATION_COLUMN = "Ventilation"
        }
    }

    companion object {
        const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${ContactDatabase.FeedEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${ContactDatabase.FeedEntry.TYPE_COLUMN} TEXT," +
                    "${ContactDatabase.FeedEntry.NAME_COLUMN} TEXT," +
                    "${ContactDatabase.FeedEntry.PLACE_COLUMN} TEXT," +
                    "${ContactDatabase.FeedEntry.TIME_BEGIN_COLUMN} INT," +
                    "${ContactDatabase.FeedEntry.TIME_END_COLUMN} INT," +
                    "${ContactDatabase.FeedEntry.PHONE_COLUMN} TEXT," +
                    "${ContactDatabase.FeedEntry.RELATIVE_COLUMN} TINYINT," +
                    "${ContactDatabase.FeedEntry.COMPANIONS_COLUMN} TEXT," +
                    "${ContactDatabase.FeedEntry.ENCOUNTER_COLUMN} TINYINT," +
                    "${ContactDatabase.FeedEntry.CLOSECONTACT_COLUMN} TINYINT," +
                    "${ContactDatabase.FeedEntry.NOTES_COLUMN} TEXT," +
                    "${ContactDatabase.FeedEntry.MASK_COLUMN} TINYINT," +
                    "${ContactDatabase.FeedEntry.VENTILATION_COLUMN} TINYINT)"
        const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${ContactDatabase.FeedEntry.TABLE_NAME}"
        const val SQL_UPDATE_2 = "ALTER TABLE ${ContactDatabase.FeedEntry.TABLE_NAME} ADD COLUMN " +
                "${ContactDatabase.FeedEntry.NOTES_COLUMN} TEXT"
        const val SQL_UPDATE_3 = "ALTER TABLE ${ContactDatabase.FeedEntry.TABLE_NAME} ADD COLUMN " +
                "${ContactDatabase.FeedEntry.DURATION_COLUMN} INT"
        const val SQL_UPDATE_4_PART1 = "ALTER TABLE ${ContactDatabase.FeedEntry.TABLE_NAME} RENAME " +
                "TO tmp_table"
        const val SQL_UPDATE_4_PART2 = "INSERT INTO ${ContactDatabase.FeedEntry.TABLE_NAME}(" +
                "${BaseColumns._ID}, ${ContactDatabase.FeedEntry.TYPE_COLUMN}, " +
                "${ContactDatabase.FeedEntry.NAME_COLUMN}, ${ContactDatabase.FeedEntry.PLACE_COLUMN}, " +
                "${ContactDatabase.FeedEntry.TIME_BEGIN_COLUMN}, ${ContactDatabase.FeedEntry.PHONE_COLUMN}, " +
                "${ContactDatabase.FeedEntry.RELATIVE_COLUMN}, ${ContactDatabase.FeedEntry.COMPANIONS_COLUMN}, " +
                "${ContactDatabase.FeedEntry.ENCOUNTER_COLUMN}, ${ContactDatabase.FeedEntry.CLOSECONTACT_COLUMN}, " +
                "${ContactDatabase.FeedEntry.NOTES_COLUMN}) " +
                "SELECT ${BaseColumns._ID}, ${ContactDatabase.FeedEntry.TYPE_COLUMN}, " +
                "${ContactDatabase.FeedEntry.NAME_COLUMN}, ${ContactDatabase.FeedEntry.PLACE_COLUMN}, " +
                "${ContactDatabase.FeedEntry.TIMESTAMP_COLUMN}, ${ContactDatabase.FeedEntry.PHONE_COLUMN}, " +
                "${ContactDatabase.FeedEntry.RELATIVE_COLUMN}, ${ContactDatabase.FeedEntry.COMPANIONS_COLUMN}, " +
                "${ContactDatabase.FeedEntry.ENCOUNTER_COLUMN}, ${ContactDatabase.FeedEntry.CLOSECONTACT_COLUMN}, " +
                "${ContactDatabase.FeedEntry.NOTES_COLUMN} " +
                "FROM tmp_table"
        const val SQL_UPDATE_4_PART3 = "DROP TABLE tmp_table"
        const val SQL_UPDATE_5_PART1 = "ALTER TABLE ${ContactDatabase.FeedEntry.TABLE_NAME} " +
                "ADD COLUMN ${ContactDatabase.FeedEntry.MASK_COLUMN} TINYINT"
        const val SQL_UPDATE_5_PART2 = "ALTER TABLE ${ContactDatabase.FeedEntry.TABLE_NAME} " +
                "ADD COLUMN ${ContactDatabase.FeedEntry.VENTILATION_COLUMN} TINYINT"
    }
}