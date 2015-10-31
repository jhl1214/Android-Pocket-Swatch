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

public class CO_OB_PocketContainer {

    private String PocketID;
    private String FiberID;

    public CO_OB_PocketContainer() {
    }

    public CO_OB_PocketContainer(String pocketID, String fiberID) {
        this.PocketID = pocketID;
        this.FiberID = fiberID;
    }

    public String getPocketID() {
        return PocketID;
    }

    public String getFiberID() {
        return FiberID;
    }

}
