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

public class CO_LO_WishListDB extends SQLiteOpenHelper{

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "wishListDatabase";

    // Fibers table name
    private static final String TABLE_WISHLIST = "wishlist";

    // Fibers Table Columns names
    private static final String KEY_FIBER = "fiber";

    public CO_LO_WishListDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POCKET_CONTAINER_TABLE = "CREATE TABLE " + TABLE_WISHLIST + "("
                + KEY_FIBER + " TEXT)";

        db.execSQL(CREATE_POCKET_CONTAINER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WISHLIST);

        // Create tables again
        onCreate(db);
    }

    public void addWishlist(CO_OB_WishList wishList) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FIBER, wishList.getFiberID());

        // Inserting Row
        db.insert(TABLE_WISHLIST, null, values);
    }

    public void deleteWishlist(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WISHLIST, KEY_FIBER + "=?", new String[]{String.valueOf(id)});
    }

    public CO_OB_WishList wishlistExists(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WISHLIST,
                new String[]{KEY_FIBER},
                KEY_FIBER + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if(cursor.getCount() != 0) {
            cursor.moveToFirst();

            CO_OB_WishList wishlist = new CO_OB_WishList(
                    Integer.parseInt(cursor.getString(0))
            );

            return wishlist;
        }

        return null;
    }

    public List<CO_OB_WishList> getWishlist() {
        List<CO_OB_WishList> wishlist = new ArrayList<CO_OB_WishList>();

        // Select all query
        String selectQuery = "SELECT * FROM " + TABLE_WISHLIST;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                CO_OB_WishList fiber = new CO_OB_WishList(
                        Integer.parseInt(cursor.getString(0))
                );

                wishlist.add(fiber);
            } while(cursor.moveToNext());
        }

        return wishlist;
    }

    public int getWishlistCount() {
        String countQuery = "SELECT  * FROM " + TABLE_WISHLIST;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    public void deleteWishlistTable(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

}
