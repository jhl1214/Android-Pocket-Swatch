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

public class CO_LO_PocketDB extends SQLiteOpenHelper{

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "pocketDatabase";

    // Fibers table name
    private static final String TABLE_POCKET = "pockets";

    // Fibers Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_USER = "user";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESC = "desc";
    private static final String KEY_SECRETE = "secrete";
    private static final String KEY_COLOR = "color";

    public CO_LO_PocketDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POCKET_TABLE = "CREATE TABLE " + TABLE_POCKET + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_USER + " TEXT, "
                + KEY_NAME + " TEXT, "
                + KEY_DESC + " TEXT, "
                + KEY_SECRETE + " TEXT, "
                + KEY_COLOR + " TEXT)";

        db.execSQL(CREATE_POCKET_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POCKET);

        // Create tables again
        onCreate(db);
    }

    public void addPocket(CO_OB_Pocket pocket) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER, pocket.getUserID());
        values.put(KEY_NAME, pocket.getPocketName());
        values.put(KEY_DESC, pocket.getPocketDesc());
        values.put(KEY_SECRETE, pocket.getPocketSecrete());
        values.put(KEY_COLOR, pocket.getPocketColor());

        // Inserting Row
        db.insert(TABLE_POCKET, null, values);
    }

    public CO_OB_Pocket getPocket(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_POCKET,
                new String[]{KEY_ID, KEY_USER, KEY_NAME, KEY_DESC, KEY_SECRETE, KEY_COLOR},
                KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        CO_OB_Pocket pocket = new CO_OB_Pocket(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5)
        );

        cursor.close();

        return pocket;
    }

    public List<CO_OB_Pocket> getUserPockets(String user) {
        List<CO_OB_Pocket> pocketList = new ArrayList<CO_OB_Pocket>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_POCKET,
                new String[]{KEY_ID, KEY_USER, KEY_NAME, KEY_DESC, KEY_SECRETE, KEY_COLOR},
                KEY_USER + "=?",
                new String[]{String.valueOf(user)},
                null, null, null, null);

        if(cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    CO_OB_Pocket pocket = new CO_OB_Pocket(
                            Integer.parseInt(cursor.getString(0)),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5)
                    );

                    pocketList.add(pocket);
                } while (cursor.moveToNext());
            }
        }

        return pocketList;
    }

    public int getPocketCount() {
        String countQuery = "SELECT  * FROM " + TABLE_POCKET;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    public int updatePocket(CO_OB_Pocket pocket) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, pocket.getPocketName());
        values.put(KEY_DESC, pocket.getPocketDesc());
        values.put(KEY_SECRETE, pocket.getPocketSecrete());
        values.put(KEY_COLOR, pocket.getPocketColor());

        return db.update(TABLE_POCKET, values, KEY_ID + " = ?", new String[] { String.valueOf(pocket.getPocketID()) });
    }

    public void deletePocket(CO_OB_Pocket pocket) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_POCKET, KEY_ID + " = ?", new String[] { String.valueOf(pocket.getPocketID()) });
    }

    public void deletePocketTable(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

}
