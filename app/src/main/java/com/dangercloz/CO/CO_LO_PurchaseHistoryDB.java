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

public class CO_LO_PurchaseHistoryDB extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "purchaseHistoryDatabase";

    // Store table name
    private static final String TABLE_HISTORY = "history";

    // Store Table Columns names
    private static final String KEY_PURCHASE = "pid";
    private static final String KEY_FIBER = "id";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_PTIME = "purchasetime";
    private static final String KEY_PTYPE = "type";
    private static final String KEY_COMPLETE = "complete";
    private static final String KEY_CTIME = "completiontime";
    private static final String KEY_DELETE = "completiondelete";

    public CO_LO_PurchaseHistoryDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STORES_TABLE = "CREATE TABLE " + TABLE_HISTORY + "("
                + KEY_PURCHASE + " INTEGER PRIMARY KEY, "
                + KEY_FIBER + " INTEGER, "
                + KEY_NUMBER + " INTEGER, "
                + KEY_PTIME + " TEXT, "
                + KEY_PTYPE + " TEXT, "
                + KEY_COMPLETE + " TEXT, "
                + KEY_CTIME + " TEXT, "
                + KEY_DELETE + " TEXT" + ")";

        db.execSQL(CREATE_STORES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);

        // Create tables again
        onCreate(db);
    }

    public void addPurchase(CO_OB_PurchaseHistory purchase) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FIBER, purchase.getFiberID());
        values.put(KEY_NUMBER, purchase.getFiberNumber());
        values.put(KEY_PTIME, purchase.getPurchaseTime());
        values.put(KEY_PTYPE, purchase.getPurchaseType());
        values.put(KEY_COMPLETE, purchase.getCompletion());
        values.put(KEY_CTIME, purchase.getCompletionTime());
        values.put(KEY_DELETE, purchase.getDelete());

        db.insert(TABLE_HISTORY, null, values);
    }

    public CO_OB_PurchaseHistory getPurchase(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_HISTORY,
                new String[]{KEY_PURCHASE, KEY_FIBER, KEY_NUMBER, KEY_PTIME, KEY_PTYPE, KEY_COMPLETE, KEY_CTIME, KEY_DELETE},
                KEY_PURCHASE + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if(cursor.getCount() != 0) {
            cursor.moveToFirst();

            CO_OB_PurchaseHistory purchase = new CO_OB_PurchaseHistory(
                    Integer.parseInt(cursor.getString(0)),
                    Integer.parseInt(cursor.getString(1)),
                    Integer.parseInt(cursor.getString(2)),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7)
            );

            return purchase;
        }

        return null;
    }

    public int getPurchaseCount(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_HISTORY,
                new String[]{KEY_PURCHASE, KEY_FIBER, KEY_NUMBER, KEY_PTIME, KEY_PTYPE, KEY_COMPLETE, KEY_CTIME, KEY_DELETE},
                KEY_FIBER + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        return cursor.getCount();
    }

    public List<CO_OB_PurchaseHistory> getAllHistory() {
        List<CO_OB_PurchaseHistory> history = new ArrayList<CO_OB_PurchaseHistory>();

        String selectQuery = "SELECT * FROM " + TABLE_HISTORY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                CO_OB_PurchaseHistory purchase = new CO_OB_PurchaseHistory(
                        Integer.parseInt(cursor.getString(0)),
                        Integer.parseInt(cursor.getString(1)),
                        Integer.parseInt(cursor.getString(2)),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)
                );

                history.add(purchase);
            } while(cursor.moveToNext());
        }

        return history;
    }

    public int updatePurchaseHistory(CO_OB_PurchaseHistory purchaseHistory) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PURCHASE, purchaseHistory.getPurchaseID());
        values.put(KEY_FIBER, purchaseHistory.getFiberID());
        values.put(KEY_NUMBER, purchaseHistory.getFiberNumber());
        values.put(KEY_PTIME, purchaseHistory.getPurchaseTime());
        values.put(KEY_PTYPE, purchaseHistory.getPurchaseType());
        values.put(KEY_COMPLETE, purchaseHistory.getCompletion());
        values.put(KEY_CTIME, purchaseHistory.getCompletionTime());
        values.put(KEY_DELETE, purchaseHistory.getDelete());

        return db.update(TABLE_HISTORY, values, KEY_PURCHASE + " = ?", new String[]{String.valueOf(purchaseHistory.getPurchaseID())});
    }

    public int getHistoryCount() {
        String countQuery = "SELECT  * FROM " + TABLE_HISTORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    public void deletePurchase(int pid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HISTORY, KEY_PURCHASE + " = ?", new String[] { String.valueOf(pid)});
    }

    public void deleteHistoryTable(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

}