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

public class CO_OB_Fiber {

    private int FiberID;
    private String FiberName;
    private String FiberType;
    private String FiberMixture;
    private String FiberColor;
    private String FiberDensity;
    private int FiberThick;
    private String FiberImage;
    private int FiberPrice;

    public CO_OB_Fiber() {
    }

    public CO_OB_Fiber(String name, String type, String mixture, String color, String density, int thick, String image, int price) {
        this.FiberName = name;
        this.FiberType = type;
        this.FiberMixture = mixture;
        this.FiberColor = color;
        this.FiberDensity = density;
        this.FiberThick = thick;
        this.FiberImage = image;
        this.FiberPrice = price;
    }

    public CO_OB_Fiber(int id, String name, String type, String mixture, String color, String density, int thick, String image, int price) {
        this.FiberID = id;
        this.FiberName = name;
        this.FiberType = type;
        this.FiberMixture = mixture;
        this.FiberColor = color;
        this.FiberDensity = density;
        this.FiberThick = thick;
        this.FiberImage = image;
        this.FiberPrice = price;
    }

    public int getFiberID() {
        return FiberID;
    }

    public String getFiberName() {
        return FiberName;
    }

    public String getFiberType() {
        return FiberType;
    }

    public String getFiberMixture() {
        return FiberMixture;
    }

    public String getFiberColor() {
        return FiberColor;
    }

    public String getFiberDensity() {
        return FiberDensity;
    }

    public int getFiberThick() {
        return FiberThick;
    }

    public String getFiberImage() {
        return FiberImage;
    }

    public int getFiberPrice() {
        return FiberPrice;
    }

}
