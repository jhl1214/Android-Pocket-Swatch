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

public class CO_LO_PocketContainerDB extends SQLiteOpenHelper{

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "pocketContainerDatabase";

    // Fibers table name
    private static final String TABLE_POCKET_CONTAINER = "pocketcontainer";

    // Fibers Table Columns names
    private static final String KEY_POCKET = "pocket";
    private static final String KEY_FIBER = "fiber";

    public CO_LO_PocketContainerDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POCKET_CONTAINER_TABLE = "CREATE TABLE " + TABLE_POCKET_CONTAINER + "("
                + KEY_POCKET + " TEXT, "
                + KEY_FIBER + " TEXT)";

        db.execSQL(CREATE_POCKET_CONTAINER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POCKET_CONTAINER);

        // Create tables again
        onCreate(db);
    }

    public void addPocketContainer(CO_OB_PocketContainer container) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_POCKET, container.getPocketID());
        values.put(KEY_FIBER, container.getFiberID());

        // Inserting Row
        db.insert(TABLE_POCKET_CONTAINER, null, values);
    }

    public List<CO_OB_PocketContainer> getPocketContainers(String pocket) {
        List<CO_OB_PocketContainer> pocketContainers = new ArrayList<CO_OB_PocketContainer>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_POCKET_CONTAINER,
                new String[]{KEY_POCKET, KEY_FIBER},
                KEY_POCKET + "=?",
                new String[]{pocket},
                null, null, null, null);

        if(cursor.moveToFirst()) {
            do {
                CO_OB_PocketContainer container = new CO_OB_PocketContainer(
                        cursor.getString(0),
                        cursor.getString(1)
                );

                pocketContainers.add(container);
            } while(cursor.moveToNext());
        }

        return pocketContainers;
    }

    public boolean pocketExists(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_POCKET_CONTAINER,
                new String[]{KEY_POCKET, KEY_FIBER},
                KEY_FIBER + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if(cursor.getCount() != 0) {
            return true;
        }

        return false;
    }

    public int getPocketContainerCount() {
        String countQuery = "SELECT  * FROM " + TABLE_POCKET_CONTAINER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    public int updatePocketContainer(CO_OB_PocketContainer container) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_POCKET, container.getPocketID());
        values.put(KEY_FIBER, container.getFiberID());

        return db.update(TABLE_POCKET_CONTAINER, values, KEY_POCKET + " = ?", new String[] { String.valueOf(container.getPocketID()) });
    }

    public void deletePocketContainer(CO_OB_PocketContainer container) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_POCKET_CONTAINER, KEY_POCKET + " = ?", new String[] { String.valueOf(container.getPocketID())});
    }

    public void deletePocketContainerTable(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

}
