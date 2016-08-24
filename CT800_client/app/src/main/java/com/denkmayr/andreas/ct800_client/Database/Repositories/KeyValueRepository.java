package com.denkmayr.andreas.ct800_client.Database.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.denkmayr.andreas.ct800_client.Database.DBContract;
import com.denkmayr.andreas.ct800_client.Database.DBContract.KeyValueEntry;
import com.denkmayr.andreas.ct800_client.Database.DBHelper;
import com.denkmayr.andreas.ct800_client.Entity.Cow;

public class KeyValueRepository {
    private static KeyValueRepository sInstance;
    private static DBHelper dbHelper;

    public static synchronized KeyValueRepository getInstance(Context context) {
        if (sInstance == null)
        {
            sInstance = new KeyValueRepository(context);
        }
        return sInstance;
    }

    private KeyValueRepository(Context context)
    {
        dbHelper = DBHelper.getInstance(context);
    }

    public void setKeyValue(String key, String value) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KeyValueEntry.COLUMN_KEY, key);
        values.put(KeyValueEntry.COLUMN_VALUE, value);

        db.insertWithOnConflict(KeyValueEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public String getKeyValue(String key) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.query(KeyValueEntry.TABLE_NAME, null,
                KeyValueEntry.COLUMN_KEY + "=?", new String[]{key}, null, null, null);
        if (c != null)
            c.moveToFirst();
        return c.getString(2);
    }

    public void deleteAllKeyValues() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(KeyValueEntry.TABLE_NAME, null, null);
    }
}
