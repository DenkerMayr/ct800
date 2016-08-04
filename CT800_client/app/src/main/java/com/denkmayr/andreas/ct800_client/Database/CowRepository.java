package com.denkmayr.andreas.ct800_client.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.denkmayr.andreas.ct800_client.Database.DBContract.CowEntry;

import com.denkmayr.andreas.ct800_client.Entity.Cow;

import java.util.ArrayList;
import java.util.List;

public final class CowRepository {
    private static CowRepository sInstance;
    private static DBHelper dbHelper;

    public static synchronized CowRepository getInstance(Context context) {
        if (sInstance == null)
        {
            sInstance = new CowRepository(context);
        }
        return sInstance;
    }

    private CowRepository(Context context)
    {
        dbHelper = DBHelper.getInstance(context);
    }

    public void insertCow(Cow cow) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CowEntry.COLUMN_EARTAG, cow.getEartag());
        values.put(CowEntry.COLUMN_NAME, cow.getName());

        try {
            db.insertOrThrow(CowEntry.TABLE_NAME, null, values);
        } catch (Exception e) {
            Log.e(this.getClass().getSimpleName(), "Error while trying to insert a cow into the database");
        }
    }

    public List<Cow> getAllCows() {
        List<Cow> cows = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(CowEntry.TABLE_NAME, null, null, null, null, null, null);

        if(cursor.moveToFirst())
        {
            while(!cursor.isAfterLast())
            {
                Cow cow = new Cow();
                cow.setEartag(cursor.getString(1));
                cow.setName(cursor.getString(2));
                cows.add(cow);
                cursor.moveToNext();
            }
        }

        return cows;
    }

    public void deleteAllCows() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(CowEntry.TABLE_NAME, null, null);
    }
}
