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

public class CO_OB_Store {

    private int StoreID;
    private String StoreName;
    private String StoreLocation;
    private String StoreTelephone;
    private String StoreFax;
    private String StoreOpenTime;

    public CO_OB_Store() {
    }

    public CO_OB_Store(String name, String location, String telephone, String fax, String time) {
        StoreName = name;
        StoreLocation = location;
        StoreTelephone = telephone;
        StoreFax = fax;
        StoreOpenTime = time;
    }

    public CO_OB_Store(int id, String name, String location, String telephone, String fax, String time) {
        StoreID = id;
        StoreName = name;
        StoreLocation = location;
        StoreTelephone = telephone;
        StoreFax = fax;
        StoreOpenTime = time;
    }

    public int getStoreID() {
        return StoreID;
    }

    public String getStoreName() {
        return StoreName;
    }

    public String getStoreLocation() {
        return StoreLocation;
    }

    public String getStoreTelephone() {
        return StoreTelephone;
    }

    public String getStoreFax() {
        return StoreFax;
    }

    public String getStoreOpenTime() {
        return StoreOpenTime;
    }

}