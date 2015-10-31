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

public class CO_OB_Hash {

    private String Tag;
    private String Value;

    public CO_OB_Hash() {
    }

    public CO_OB_Hash(String tag, String value) {
        Tag = tag;
        Value = value;
    }

    public String getTag() {
        return Tag;
    }

    public String getValue() {
        return Value;
    }

}