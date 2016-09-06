package com.example.dmitry.cashmonitor.DB;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Dmitry on 25.08.2016.
 */
public class DBForNotes extends SQLiteOpenHelper {

        public static final String COLUMN_ID = "_id";

        public static final String NAME_COLLUMN_NAME = "name";
        public static final String NAME_COLLUMN_SUCCESS_BOUGHT = "isbought";
        public static final String NAME_COLLUMN_COUNT_BOUGHT = "count";
        public static final String NAME_COLLUMN_TYPE_BOUGHT = "typebght";

        public static final String NAME_DB = "cash.db";
        public static final String NAME_TABLE = "tblcash";
        public static final int DB_VERSION = 1;

        private static final String DATABASE_CREATE_SCRIPT = "create table "
                + NAME_TABLE + " (" + BaseColumns._ID
                + " integer primary key autoincrement, " +
                NAME_COLLUMN_NAME + " text, " +
                NAME_COLLUMN_SUCCESS_BOUGHT + " integer, " +
                NAME_COLLUMN_COUNT_BOUGHT + " real, " +
                NAME_COLLUMN_TYPE_BOUGHT + " text);";

        public DBForNotes(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        public DBForNotes(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
            super(context, name, factory, version, errorHandler);
        }

        public DBForNotes(Context context) {
            super(context, NAME_DB, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_SCRIPT);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
