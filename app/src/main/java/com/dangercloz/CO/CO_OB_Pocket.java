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

public class CO_OB_Pocket {

    private int PocketID;
    private String UserID;
    private String PocketName;
    private String PocketDesc;
    private String PocketSecrete;
    private String PocketColor;

    public CO_OB_Pocket() {
    }

    public CO_OB_Pocket(String userID, String pocketName, String pocketDesc, String pocketSecrete, String pocketColor) {
        this.UserID = userID;
        this.PocketName = pocketName;
        this.PocketDesc = pocketDesc;
        this.PocketSecrete = pocketSecrete;
        this.PocketColor = pocketColor;
    }

    public CO_OB_Pocket(int pocketID, String userID, String pocketName, String pocketDesc, String pocketSecrete, String pocketColor) {
        this.PocketID = pocketID;
        this.UserID = userID;
        this.PocketName = pocketName;
        this.PocketDesc = pocketDesc;
        this.PocketSecrete = pocketSecrete;
        this.PocketColor = pocketColor;
    }

    public int getPocketID() {
        return PocketID;
    }

    public String getUserID() {
        return UserID;
    }

    public String getPocketName() {
        return PocketName;
    }

    public String getPocketDesc() {
        return PocketDesc;
    }

    public String getPocketSecrete() {
        return PocketSecrete;
    }

    public String getPocketColor() {
        return PocketColor;
    }

}
