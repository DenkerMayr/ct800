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
}
