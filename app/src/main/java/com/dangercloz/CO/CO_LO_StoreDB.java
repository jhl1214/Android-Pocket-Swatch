/**
 *
 *  Pocket Swatch
 *
 *  Copyright © 2015 Junho. All rights reserved.
 *
 *  This is a proprietary of Junho, and you may not use this file except in compliance with license agreement with Junho.
 *  Any redistribution or use of this software, with or without modification shall be strictly prohibited without prior
 *  written approval of Junho, and the copyright notice above does not evidence any actual or intended publication of such software.
 *
 */

package com.dangercloz.CO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class CO_LO_StoreDB extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "storeDatabase";

    // Store table name
    private static final String TABLE_STORES = "stores";

    // Store Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_TELEPHONE = "telephone";
    private static final String KEY_FAX = "fax";
    private static final String KEY_TIME = "time";

    public CO_LO_StoreDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STORES_TABLE = "CREATE TABLE " + TABLE_STORES + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_NAME + " TEXT, "
                + KEY_LOCATION + " TEXT, "
                + KEY_TELEPHONE + " TEXT, "
                + KEY_FAX + " TEXT, "
                + KEY_TIME + " TEXT" + ")";

        db.execSQL(CREATE_STORES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORES);

        // Create tables again
        onCreate(db);
    }

    public void addStore(CO_OB_Store store) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, store.getStoreName());
        values.put(KEY_LOCATION, store.getStoreLocation());
        values.put(KEY_TELEPHONE, store.getStoreTelephone());
        values.put(KEY_FAX, store.getStoreFax());
        values.put(KEY_TIME, store.getStoreOpenTime());

        // Inserting Row
        db.insert(TABLE_STORES, null, values);
    }

    public CO_OB_Store getStore(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STORES,
                new String[]{KEY_ID, KEY_NAME, KEY_LOCATION, KEY_TELEPHONE, KEY_FAX, KEY_TIME},
                KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        CO_OB_Store store = new CO_OB_Store(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5)
        );

        return store;
    }

    public List<CO_OB_Store> getAllStores() {
        List<CO_OB_Store> storeList = new ArrayList<CO_OB_Store>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STORES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CO_OB_Store store = new CO_OB_Store(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );

                storeList.add(store);
            } while (cursor.moveToNext());
        }

        // return store list
        return storeList;
    }

    public int getStoreCount() {
        String countQuery = "SELECT  * FROM " + TABLE_STORES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    public int updateFiber(CO_OB_Store store) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, store.getStoreName());
        values.put(KEY_LOCATION, store.getStoreLocation());

        return db.update(TABLE_STORES, values, KEY_ID + " = ?", new String[]{String.valueOf(store.getStoreID())});
    }

    public void deleteStore(CO_OB_Store store) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STORES, KEY_ID + " = ?", new String[] { String.valueOf(store.getStoreID()) });
    }

    public void deleteStoreTable(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

}