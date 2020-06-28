package com.funwithandroid.shoppinglist.dbhandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

import com.funwithandroid.shoppinglist.ItemObject;
import com.funwithandroid.shoppinglist.dbParams.DbVariables;

import java.util.ArrayList;
import java.util.List;

public class dataBaseOperation extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    public dataBaseOperation(@Nullable Context context) {
        super(context, DbVariables.ItemTable.DATABASE_NAME, null, DbVariables.ItemTable.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;
        final  String CREATE_DATA_BASE="CREATE TABLE "+
                DbVariables.ItemTable.TABLE_NAME+"("+
                DbVariables.ItemTable._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                DbVariables.ItemTable.ITEM_NAME+" TEXT,"+
                DbVariables.ItemTable.ITEM_QUANTITY+" TEXT,"+
                DbVariables.ItemTable.CHECKED_BOX+" INTEGER"+
                ")";
        db.execSQL(CREATE_DATA_BASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS '"+ DbVariables.ItemTable.TABLE_NAME+"'");
            db.delete( DbVariables.ItemTable.TABLE_NAME, null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        onCreate(db);
    }

   public void addToDataBase(ItemObject itemObject){
        db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DbVariables.ItemTable.ITEM_NAME,itemObject.getItem_name());
        contentValues.put(DbVariables.ItemTable.ITEM_QUANTITY,itemObject.getItem_quality());
        contentValues.put(DbVariables.ItemTable.CHECKED_BOX,itemObject.getMarked());
        db.insert(DbVariables.ItemTable.TABLE_NAME,null,contentValues);
        db.close();
    }
    public List<ItemObject> getAllItem(){
        db=getReadableDatabase();
        List<ItemObject> itemObjects=new ArrayList<>();
        Cursor cursor=db.rawQuery("SELECT * FROM "+DbVariables.ItemTable.TABLE_NAME,null);
        if(cursor.moveToFirst()){
            do{
                ItemObject itemObject=new ItemObject();
                itemObject.setItem_name(cursor.getString(cursor.getColumnIndex(DbVariables.ItemTable.ITEM_NAME)));
                itemObject.setItem_quality(cursor.getString(cursor.getColumnIndex(DbVariables.ItemTable.ITEM_QUANTITY)));
                itemObject.setMarked(cursor.getInt(cursor.getColumnIndex(DbVariables.ItemTable.CHECKED_BOX)));
                itemObjects.add(itemObject);
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return  itemObjects;
    }
    public  void deleteItemDataBase(String item,String quality){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(DbVariables.ItemTable.TABLE_NAME,
                DbVariables.ItemTable.ITEM_NAME+"=? and " +DbVariables.ItemTable.ITEM_QUANTITY+"=?",
                new String[]{item,quality});
        db.close();
    }
    public int updateItemTable(ItemObject itemObject,String item,String quality){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DbVariables.ItemTable.ITEM_NAME,itemObject.getItem_name());
        values.put(DbVariables.ItemTable.ITEM_QUANTITY,itemObject.getItem_quality());
        values.put(DbVariables.ItemTable.CHECKED_BOX,itemObject.getMarked());
        //upadte code
        return  db.update(DbVariables.ItemTable.TABLE_NAME,values,DbVariables.ItemTable.ITEM_NAME+"=? and " +DbVariables.ItemTable.ITEM_QUANTITY+"=?",
                new String[]{item,quality});
    }

}
