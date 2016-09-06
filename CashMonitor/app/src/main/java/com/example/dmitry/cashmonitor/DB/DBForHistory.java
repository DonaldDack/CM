package com.example.dmitry.cashmonitor.DB;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Dmitry on 19.08.2016.
 */
public class DBForHistory extends DBForNotes {

    public static final String NAME_COLLUMN_PRICE_FOR_ONE = "price";

    public static final String NAME_COLLUMN_DATE_DAY_WHEN_BGHT = "day";
    public static final String NAME_COLLUMN_DATE_MONTH_WHEN_BGHT = "month";
    public static final String NAME_COLLUMN_DATE_YEAR_WHEN_BGHT = "year";

    public static final String NAME_DB = "cash_history.db";
    public static final String NAME_TABLE = "tbl_cash_history";
    public static final int DB_VERSION = 1;

    private static final String DATABASE_CREATE_SCRIPT = "create table "
            + NAME_TABLE + " (" + BaseColumns._ID
            + " integer primary key autoincrement, " +
            NAME_COLLUMN_NAME + " text, " +
            NAME_COLLUMN_SUCCESS_BOUGHT + " integer, " +
            NAME_COLLUMN_COUNT_BOUGHT + " real, " +
            NAME_COLLUMN_TYPE_BOUGHT + " text, " +
            NAME_COLLUMN_PRICE_FOR_ONE + " real, " +
            NAME_COLLUMN_DATE_DAY_WHEN_BGHT + " text, " +
            NAME_COLLUMN_DATE_MONTH_WHEN_BGHT + " text, " +
            NAME_COLLUMN_DATE_YEAR_WHEN_BGHT+ " text);";

    public DBForHistory(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBForHistory(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public DBForHistory(Context context) {
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
