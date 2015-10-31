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

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CO_LO_DataCenter {

    private static CO_LO_HashDB hashDB;
    private static CO_LO_FiberDB fiberDB;
    private static CO_LO_StoreDB storeDB;
    private static CO_LO_PocketDB pocketDB;
    private static CO_LO_PocketContainerDB pocketContainerDB;
    private static CO_LO_WishListDB wishListDB;
    private static CO_LO_PurchaseHistoryDB purchaseHistoryDB;
    private static HashMap<String, List<CO_OB_Hash>> hashList;
    private static ArrayList<CO_OB_Fiber> searchResult;

    public CO_LO_DataCenter(Context context) {
        hashDB = new CO_LO_HashDB(context);
        fiberDB = new CO_LO_FiberDB(context);
        storeDB = new CO_LO_StoreDB(context);
        pocketDB = new CO_LO_PocketDB(context);
        pocketContainerDB = new CO_LO_PocketContainerDB(context);
        wishListDB = new CO_LO_WishListDB(context);
        purchaseHistoryDB = new CO_LO_PurchaseHistoryDB(context);

        hashList = new HashMap<String, List<CO_OB_Hash>>();

        searchResult = new ArrayList<CO_OB_Fiber>();
    }

    public static HashMap<String, List<CO_OB_Hash>> getHashList() {
        hashList.clear();

        List<String> hashes = hashDB.getHashList();
        for(String temp : hashes) {
            hashList.put(temp, null);
        }

        return hashList;
    }

    public static List<String> getHashListofItem(int id) {
        return hashDB.getHashListofItem(id);
    }

    public static ArrayList<CO_OB_Fiber> searchResultList(List<String> tagList) {
        searchResult.clear();
        boolean first = true;

        for(String tag : tagList) {
            if(first) {
                List<CO_OB_Hash> hashList = hashDB.getHashItems(tag);

                for (int i = 0; i < hashList.size(); i++) {
                    CO_OB_Fiber fiber = fiberDB.getFiber(Integer.parseInt(hashList.get(i).getValue()));
                    searchResult.add(fiber);
                }

                first = false;
            } else {
                ArrayList<CO_OB_Fiber> temp = new ArrayList<CO_OB_Fiber>();
                List<CO_OB_Hash> hashList = hashDB.getHashItems(tag);

                for(CO_OB_Fiber fiber : searchResult) {
                    for(int i=0;i<hashList.size();i++) {
                        if(fiber.getFiberID() == Integer.parseInt(hashList.get(i).getValue())){
                            temp.add(fiber);
                        }
                    }
                }

                searchResult = temp;
            }
        }

        return searchResult;
    }

    public static void addFiberToPocket(String pocketID, String fiberID) {
        CO_OB_PocketContainer pocketContainer = new CO_OB_PocketContainer(pocketID, fiberID);

        pocketContainerDB.addPocketContainer(pocketContainer);
    }

    public static void addToWishlist(int fiberID) {
        CO_OB_WishList fiber = new CO_OB_WishList(fiberID);

        wishListDB.addWishlist(fiber);
    }

    public static void addPurchaseHistory(int fiberID, int count, String ptime, String ptype, String complete, String ctime, String delete) {
        CO_OB_PurchaseHistory purchase = new CO_OB_PurchaseHistory(fiberID, count, ptime, ptype, complete, ctime, delete);

        purchaseHistoryDB.addPurchase(purchase);
    }

    public static void deleteWishlist(int fiberID) {
        wishListDB.deleteWishlist(fiberID);
    }

    public static void deletePurchaseHistory(int pid) { purchaseHistoryDB.deletePurchase(pid);}

    public static List<CO_OB_PocketContainer> getPocketContainer(String pocketID) {
        return pocketContainerDB.getPocketContainers(pocketID);
    }

    public static CO_OB_Fiber getFiber(int id) {
        return fiberDB.getFiber(id);
    }

    public static CO_OB_Store getStore(int id) { return storeDB.getStore(id); }

    public static CO_OB_Pocket getPocket(int id) { return pocketDB.getPocket(id); }

    public static CO_OB_WishList wishListExists(int id) { return wishListDB.wishlistExists(id); }

    public static boolean pocketFiberExists(int id) { return pocketContainerDB.pocketExists(id); }

    public static CO_OB_PurchaseHistory getPurchase(int id) { return purchaseHistoryDB.getPurchase(id); }

    public static List<CO_OB_Fiber> getAllFibers() {
        return fiberDB.getAllFibers();
    }

    public static List<CO_OB_Store> getAllStores() {
        return storeDB.getAllStores();
    }

    public static List<CO_OB_WishList> getWishList() {
        return wishListDB.getWishlist();
    }

    public static List<CO_OB_PurchaseHistory> getPurchaseHistory() { return purchaseHistoryDB.getAllHistory(); }

    public static int getPurchaseCount(int id) { return purchaseHistoryDB.getPurchaseCount(id); }

    public static int updatePurchaseHistory(CO_OB_PurchaseHistory purchase) { return purchaseHistoryDB.updatePurchaseHistory(purchase);}

}
