package com.example.max.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Martin on 15.03.2016.
 */
public class FeedReaderDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry.COLUMN_ID + TEXT_TYPE + COMMA_SEP +
                    FeedReaderContract.FeedEntry.COLUMN_FIRST_NAME + TEXT_TYPE + COMMA_SEP +
                    FeedReaderContract.FeedEntry.COLUMN_LAST_NAME + TEXT_TYPE +
                    " );";

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("Database Operations", "Database created");
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.e("Table", "Table created");
    }

    public void insert(String id, String first_name, String last_name, SQLiteDatabase db){

            ContentValues contentValues = new ContentValues();
            contentValues.put(FeedReaderContract.FeedEntry.COLUMN_ID, id);
            contentValues.put(FeedReaderContract.FeedEntry.COLUMN_FIRST_NAME, first_name);
            contentValues.put(FeedReaderContract.FeedEntry.COLUMN_LAST_NAME, last_name);
            db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, contentValues);

    }

    public void delete(String[] id, SQLiteDatabase db){


        db.delete(FeedReaderContract.FeedEntry.TABLE_NAME,"pID LIKE ?", id);

    }

    public Cursor show(SQLiteDatabase db){

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_ID,
                FeedReaderContract.FeedEntry.COLUMN_LAST_NAME,
                FeedReaderContract.FeedEntry.COLUMN_FIRST_NAME

        };

// How you want the results sorted in the resulting Cursor
       String sortOrder = FeedReaderContract.FeedEntry.COLUMN_ID + " DESC";

        Cursor c = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        return c;
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }



}
