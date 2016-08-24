package com.denkmayr.andreas.ct800_client.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.denkmayr.andreas.ct800_client.Database.DBContract.CowEntry;
import com.denkmayr.andreas.ct800_client.Database.DBContract.FarmerEntry;
import com.denkmayr.andreas.ct800_client.Database.DBContract.KeyValueEntry;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ClawTrimmerDB";
    private static DBHelper sInstance;

    public static synchronized DBHelper getInstance(Context context) {
        if (sInstance == null)
        {
            sInstance = new DBHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //KeyValuetable
        db.execSQL("CREATE TABLE " + KeyValueEntry.TABLE_NAME +
                " (" +
                KeyValueEntry.COLUMN_ROWID + " integer primary key autoincrement, " +
                KeyValueEntry.COLUMN_KEY + " text not null unique, " +
                KeyValueEntry.COLUMN_VALUE + " text" +
                ");");

        //Cowtable
        db.execSQL("CREATE TABLE " + CowEntry.TABLE_NAME +
                " (" +
                CowEntry.COLUMN_ROWID + " integer primary key autoincrement, " +
                CowEntry.COLUMN_EARTAG + " text not null, " +
                CowEntry.COLUMN_NAME + " text" +
                ");");

        //Farmertable
        db.execSQL("CREATE TABLE " + FarmerEntry.TABLE_NAME +
                " (" +
                FarmerEntry.COLUMN_ROWID + " integer primary key autoincrement, " +
                FarmerEntry.COLUMN_NAME + " text, " +
                FarmerEntry.COLUMN_EMAIL + " text, " +
                FarmerEntry.COLUMN_RESIDENCY + " text, " +
                FarmerEntry.COLUMN_ZIP + " text, " +
                FarmerEntry.COLUMN_STREET + " text, " +
                FarmerEntry.COLUMN_STREETNUMBER + " text" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CowEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FarmerEntry.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
