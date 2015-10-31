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

public class CO_LO_FiberDB extends SQLiteOpenHelper{

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "fiberDatabase";

    // Fibers table name
    private static final String TABLE_FIBERS = "fibers";

    // Fibers Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TYPE = "type";
    private static final String KEY_MIXTURE = "mixture";
    private static final String KEY_COLOR = "color";
    private static final String KEY_DENSITY = "density";
    private static final String KEY_THICK = "thick";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_PRICE = "price";

    public CO_LO_FiberDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FIBERS_TABLE = "CREATE TABLE " + TABLE_FIBERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_NAME + " TEXT, "
                + KEY_TYPE + " TEXT, "
                + KEY_MIXTURE + " TEXT, "
                + KEY_COLOR + " TEXT, "
                + KEY_DENSITY + " TEXT, "
                + KEY_THICK + " TEXT, "
                + KEY_IMAGE + " TEXT, "
                + KEY_PRICE + " TEXT" + ")";

        db.execSQL(CREATE_FIBERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FIBERS);

        // Create tables again
        onCreate(db);
    }

    public void addFiber(CO_OB_Fiber fiber) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, fiber.getFiberName());
        values.put(KEY_TYPE, fiber.getFiberType());
        values.put(KEY_MIXTURE, fiber.getFiberMixture());
        values.put(KEY_COLOR, fiber.getFiberColor());
        values.put(KEY_DENSITY, fiber.getFiberDensity());
        values.put(KEY_THICK, fiber.getFiberThick());
        values.put(KEY_IMAGE, fiber.getFiberImage());
        values.put(KEY_PRICE, fiber.getFiberPrice());

        // Inserting Row
        db.insert(TABLE_FIBERS, null, values);
    }

    public CO_OB_Fiber getFiber(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FIBERS,
                new String[]{KEY_ID, KEY_NAME, KEY_TYPE, KEY_MIXTURE, KEY_COLOR, KEY_DENSITY, KEY_THICK, KEY_IMAGE, KEY_PRICE},
                KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if(cursor.getCount() != 0) {
            cursor.moveToFirst();

            CO_OB_Fiber fiber = new CO_OB_Fiber(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    Integer.parseInt(cursor.getString(6)),
                    cursor.getString(7),
                    Integer.parseInt(cursor.getString(8))
            );

            return fiber;
        }

        return null;
    }

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
                        cursor.getString(7),
                        Integer.parseInt(cursor.getString(8))
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
        values.put(KEY_PRICE, fiber.getFiberPrice());

        return db.update(TABLE_FIBERS, values, KEY_ID + " = ?", new String[] { String.valueOf(fiber.getFiberID()) });
    }

    public void deleteFiber(CO_OB_Fiber fiber) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FIBERS, KEY_ID + " = ?", new String[] { String.valueOf(fiber.getFiberID()) });
    }

    public void deleteFiberTable(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

}
