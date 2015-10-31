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

public class CO_OB_StoreContainer {

    private int StoreID;
    private int FiberID;
    private double FiberPrice;

    public CO_OB_StoreContainer() {
    }

    public CO_OB_StoreContainer(int storeID, int fiberID, double fiberPrice) {
        StoreID = storeID;
        FiberID = fiberID;
        FiberPrice = fiberPrice;
    }

    public int getStoreID() {
        return StoreID;
    }

    public int getFiberID() {
        return FiberID;
    }

    public double getFiberPrice() {
        return FiberPrice;
    }

}
