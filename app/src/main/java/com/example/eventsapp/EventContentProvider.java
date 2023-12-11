package com.example.eventsapp;

import static java.security.AccessController.getContext;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class EventContentProvider extends ContentProvider {
    public final static String DBNAME = "EventsDB";
    protected static final class MainDatabaseHelper extends SQLiteOpenHelper {
        MainDatabaseHelper(Context context) {
            super(context, DBNAME, null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_MAIN);
        }
        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
    }

    public final static String TABLE_NAMESTABLE = "Events";
    public final static String FIRSTCOLUMN = "name";
    public final static String SECONDCOLUMN = "date";
    public final static String THIRDCOLUMN = "location";
    public final static String FOURTHCOLUMN = "description";
    public static final String AUTHORITY = "eventsapp.com";
    public static final Uri CONTENT_URI = Uri.parse(
            "content://" + AUTHORITY +"/" + TABLE_NAMESTABLE);

    public MainDatabaseHelper mOpenHelper;

    public static final String SQL_CREATE_MAIN = "CREATE TABLE " +
            TABLE_NAMESTABLE +
            "(" +
            " _ID INTEGER PRIMARY KEY, " +
            FIRSTCOLUMN + " TEXT," +
            SECONDCOLUMN + " TEXT," +
            THIRDCOLUMN + " TEXT," +
            FOURTHCOLUMN + " TEXT)";



    public EventContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        return mOpenHelper.getWritableDatabase().delete(TABLE_NAMESTABLE, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String name = values.getAsString(FIRSTCOLUMN).trim();
        String date = values.getAsString(SECONDCOLUMN).trim();
        String location = values.getAsString(THIRDCOLUMN).trim();
        String description = values.getAsString(FOURTHCOLUMN).trim();


        if (name.equals(""))
            return null;
        if (date.equals(""))
            return null;
        if (location.equals(""))
            return null;
        if (description.equals(""))
            return null;


        long id = mOpenHelper.getWritableDatabase().insert(TABLE_NAMESTABLE, null, values);

        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new MainDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return mOpenHelper.getReadableDatabase().query(TABLE_NAMESTABLE, projection, selection, selectionArgs,
                null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().update(TABLE_NAMESTABLE, values, selection, selectionArgs);
    }
}