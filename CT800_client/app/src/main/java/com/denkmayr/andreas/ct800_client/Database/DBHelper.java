package com.denkmayr.andreas.ct800_client.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.denkmayr.andreas.ct800_client.Database.DBContract.CowEntry;

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
        db.execSQL("CREATE TABLE " + CowEntry.TABLE_NAME +
        " (" +
                CowEntry.COLUMN_ROWID + " integer primary key autoincrement, " +
                CowEntry.COLUMN_EARTAG + " text not null, " +
                CowEntry.COLUMN_NAME + " text not null" +
        ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CowEntry.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
