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

public class CO_OB_PurchaseHistory {

    private int PurchaseID;
    private int FiberID;
    private int FiberNumber;
    private String PurchaseTime;
    private String PurchaseType;
    private String Completion;
    private String CompletionTime;
    private String Delete;

    public CO_OB_PurchaseHistory() {
    }

    public CO_OB_PurchaseHistory(int fiberID, int fiberNumber, String purchaseTime, String purchaseType, String completion, String completionTime, String delete) {
        this.FiberID = fiberID;
        this.FiberNumber = fiberNumber;
        this.PurchaseTime = purchaseTime;
        this.PurchaseType = purchaseType;
        this.Completion = completion;
        this.CompletionTime = completionTime;
        this.Delete = delete;
    }

    public CO_OB_PurchaseHistory(int pID, int fiberID, int fiberNumber, String purchaseTime, String purchaseType, String completion, String completionTime, String delete) {
        this.PurchaseID = pID;
        this.FiberID = fiberID;
        this.FiberNumber = fiberNumber;
        this.PurchaseTime = purchaseTime;
        this.PurchaseType = purchaseType;
        this.Completion = completion;
        this.CompletionTime = completionTime;
        this.Delete = delete;
    }

    public int getPurchaseID() { return PurchaseID; }

    public int getFiberID() { return FiberID; }

    public int getFiberNumber() { return FiberNumber; }

    public String getPurchaseTime() { return PurchaseTime; }

    public String getPurchaseType() { return PurchaseType; }

    public String getCompletion() { return Completion; }

    public String getCompletionTime() { return CompletionTime; }

    public String getDelete() { return Delete; }

    public void setCompletion(String flag) {
        this.Completion = flag;
    }

    public void setCompletionTime(String time) {
        this.CompletionTime = time;
    }

    public void setDelete(String delete) { this.Delete = delete; }

}