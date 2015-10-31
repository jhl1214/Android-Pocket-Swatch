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

public class CO_LO_HashDB extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "hashDatabase";

    // Store table name
    private static final String TABLE_HASHES = "hashes";

    // Store Table Columns names
    private static final String KEY_TAG = "tag";
    private static final String KEY_VALUE = "value";

    public CO_LO_HashDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_HASH_TABLE = "CREATE TABLE " + TABLE_HASHES + " ("
                + KEY_TAG + " TEXT, "
                + KEY_VALUE + " TEXT)";

        db.execSQL(CREATE_HASH_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HASHES);

        // Create tables again
        onCreate(db);
    }

    public void addHash(CO_OB_Hash hash) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TAG, hash.getTag());
        values.put(KEY_VALUE, hash.getValue());

        // Insert Row
        db.insert(TABLE_HASHES, null, values);
    }

    public List<CO_OB_Hash> getHashItems(String tag) {
        List<CO_OB_Hash> hashList = new ArrayList<CO_OB_Hash>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_HASHES,
                new String[] {KEY_TAG, KEY_VALUE},
                KEY_TAG + "=?",
                new String[] {tag},
                null, null, null, null);

        // Looping through all rows and adding to list
        if(cursor.moveToFirst()) {
            do {
                CO_OB_Hash hash = new CO_OB_Hash(
                        cursor.getString(0),
                        cursor.getString(1)
                );

                hashList.add(hash);
            } while(cursor.moveToNext());
        }

        // Return hash list
        return hashList;
    }

    public List<String> getHashListofItem(int id) {
        List<String> hashList = new ArrayList<String>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_HASHES,
                new String[] {KEY_TAG, KEY_VALUE},
                KEY_VALUE + "=?",
                new String[] {String.valueOf(id)},
                null, null, null, null);

        if(cursor.moveToFirst()) {
            do {
                CO_OB_Hash hash = new CO_OB_Hash(
                        cursor.getString(0),
                        cursor.getString(1)
                );

                hashList.add(hash.getTag());
            } while(cursor.moveToNext());
        }

        return hashList;
    }

    public List<String> getHashList() {
        List<String> hashList = new ArrayList<String>();

        // Select query for get list of hash tags
        String selectQuery = "SELECT tag FROM " + TABLE_HASHES + " GROUP BY tag";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                hashList.add(cursor.getString(0));
            } while(cursor.moveToNext());
        }

        return hashList;
    }

    public void deleteHashTable(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

}

/*
    public List<CO_OB_Fiber> getAllFibers() {
        List<CO_OB_Fiber> fiberList = new ArrayList<CO_OB_Fiber>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FIBERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CO_OB_Fiber fiber = new CO_OB_Fiber(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        Integer.parseInt(cursor.getString(6)),
                        cursor.getString(7)
                );

                fiberList.add(fiber);
            } while (cursor.moveToNext());
        }

        // return contact list
        return fiberList;
    }

    public int getFiberCount() {
        String countQuery = "SELECT  * FROM " + TABLE_FIBERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public int updateFiber(CO_OB_Fiber fiber) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, fiber.getFiberName());
        values.put(KEY_TYPE, fiber.getFiberType());
        values.put(KEY_MIXTURE, fiber.getFiberMixture());
        values.put(KEY_COLOR, fiber.getFiberColor());
        values.put(KEY_DENSITY, fiber.getFiberDensity());
        values.put(KEY_THICK, fiber.getFiberThick());
        values.put(KEY_IMAGE, fiber.getFiberImage());

        return db.update(TABLE_FIBERS, values, KEY_ID + " = ?", new String[] { String.valueOf(fiber.getFiberID()) });
    }

    public void deleteFiber(CO_OB_Fiber fiber) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FIBERS, KEY_ID + " = ?", new String[] { String.valueOf(fiber.getFiberID()) });
        db.close();
    }

    public void deleteFiberTable(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }
 */