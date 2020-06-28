package com.funwithandroid.shoppinglist.dbParams;

import android.provider.BaseColumns;

public final class DbVariables{
    public DbVariables() {}

    public static class ItemTable implements BaseColumns {
        public static final String DATABASE_NAME="shopping_list.db";
        public static final int VERSION=1;
        public static final String TABLE_NAME="list";
        public static final String  ITEM_NAME="item";
        public static final String  ITEM_QUANTITY="quantity";
        public static final String  CHECKED_BOX="marked";
    }

}

