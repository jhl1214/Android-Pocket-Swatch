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

public class CO_LO_StoreContainerDB extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "storeContainerDatabase";

    // Store table name
    private static final String TABLE_STORE_CONTAINER = "container";

    // Store Table Columns names
    private static final String KEY_STORE_ID = "storeID";
    private static final String KEY_FIBER_ID = "fiberID";
    private static final String KEY_FIBER_PRICE = "fiberPrice";

    public CO_LO_StoreContainerDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STORE_CONTAINER_TABLE = "CREATE TABLE " + TABLE_STORE_CONTAINER + "{"
                + KEY_STORE_ID + " TEXT"
                + KEY_FIBER_ID + " TEXT, "
                + KEY_FIBER_PRICE + " TEXT" + ")";

        db.execSQL(CREATE_STORE_CONTAINER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORE_CONTAINER);

        // Create tables again
        onCreate(db);
    }

    public void addStoreContainer(CO_OB_StoreContainer storeContainer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STORE_ID, storeContainer.getStoreID());
        values.put(KEY_FIBER_ID, storeContainer.getFiberID());
        values.put(KEY_FIBER_PRICE, storeContainer.getFiberPrice());

        // Inserting Row
        db.insert(TABLE_STORE_CONTAINER, null, values);
    }

    public int getStoreCount() {
        String countQuery = "SELECT  * FROM " + TABLE_STORE_CONTAINER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    public int updateStoreContainer(CO_OB_StoreContainer storeContainer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STORE_ID, storeContainer.getStoreID());
        values.put(KEY_FIBER_ID, storeContainer.getFiberID());
        values.put(KEY_FIBER_PRICE, storeContainer.getFiberPrice());

        return db.update(TABLE_STORE_CONTAINER, values, KEY_STORE_ID + " = ?, " + KEY_FIBER_ID + " = ?", new String[]{String.valueOf(storeContainer.getStoreID()), String.valueOf(storeContainer.getFiberID())});
    }

    public void deleteStoreContainer(CO_OB_StoreContainer storeContainer) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STORE_CONTAINER, KEY_STORE_ID + " = ?, " + KEY_FIBER_ID + " = ?", new String[] { String.valueOf(storeContainer.getStoreID()), String.valueOf(storeContainer.getFiberID()) });
    }

    public void deleteStoreContainerTable(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

}
