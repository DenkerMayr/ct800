package com.denkmayr.andreas.ct800_client.Database;

import android.provider.BaseColumns;

public class DBContract {

    public DBContract() {}

    public static final class CowEntry implements BaseColumns {
        public static final String TABLE_NAME = "cows";
        public static final String COLUMN_ROWID = "_id";
        public static final String COLUMN_EARTAG = "eartag";
        public static final String COLUMN_NAME = "name";
    }

    public static final class FarmerEntry implements BaseColumns {
        public static final String TABLE_NAME = "farmers";
        public static final String COLUMN_ROWID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_RESIDENCY = "residency";
        public static final String COLUMN_ZIP = "zip";
        public static final String COLUMN_STREET = "street";
        public static final String COLUMN_STREETNUMBER = "streetnumber";
    }

    public static final class KeyValueEntry implements BaseColumns {
        public static final String TABLE_NAME = "keyvalue";
        public static final String COLUMN_ROWID = "_id";
        public static final String COLUMN_KEY = "key";
        public static final String COLUMN_VALUE = "value";

    }
}