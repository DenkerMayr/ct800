package com.denkmayr.andreas.ct800_client.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.denkmayr.andreas.ct800_client.Entity.Farmer;
import com.denkmayr.andreas.ct800_client.Database.DBContract.FarmerEntry;
import java.util.ArrayList;
import java.util.List;

public final class FarmerRepository {
    private static FarmerRepository sInstance;
    private static DBHelper dbHelper;

    public static synchronized FarmerRepository getInstance(Context context) {
        if (sInstance == null)
        {
            sInstance = new FarmerRepository(context);
        }
        return sInstance;
    }

    private FarmerRepository(Context context)
    {
        dbHelper = DBHelper.getInstance(context);
    }

    public void insertFarmer(Farmer farmer) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FarmerEntry.COLUMN_NAME, farmer.getName());
        values.put(FarmerEntry.COLUMN_EMAIL, farmer.getEmail());
        values.put(FarmerEntry.COLUMN_RESIDENCY, farmer.getResidence());
        values.put(FarmerEntry.COLUMN_ZIP, farmer.getZip());
        values.put(FarmerEntry.COLUMN_STREET, farmer.getStreet());
        values.put(FarmerEntry.COLUMN_STREETNUMBER, farmer.getStreetNumber());

        try {
            db.insertOrThrow(FarmerEntry.TABLE_NAME, null, values);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getSimpleName(), "Error while trying to insert a farmer into the database");
        }
    }

    public List<Farmer> getAllFarmers() {
        List<Farmer> farmers = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(FarmerEntry.TABLE_NAME, null, null, null, null, null, null);

        if(cursor.moveToFirst())
        {
            while(!cursor.isAfterLast())
            {
                Farmer farmer = new Farmer();
                farmer.setName(cursor.getString(1));
                farmer.setEmail(cursor.getString(2));
                farmer.setResidence(cursor.getString(3));
                farmer.setZip(cursor.getString(4));
                farmer.setStreet(cursor.getString(5));
                farmer.setStreetNumber(cursor.getString(6));
                farmers.add(farmer);
                cursor.moveToNext();
            }
        }
        return farmers;
    }

    public void deleteAllFarmers() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(FarmerEntry.TABLE_NAME, null, null);
    }
}
