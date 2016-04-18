package com.example.max.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Martin on 13.03.2016.
 */
    public final class FeedReaderContract {

        public static abstract class FeedEntry{
            public static final String TABLE_NAME = "person";
            public static final String COLUMN_ID = "pID";
            public static final String COLUMN_LAST_NAME = "last_name";
            public static final String COLUMN_FIRST_NAME = "first_name";

        }

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;


    }




