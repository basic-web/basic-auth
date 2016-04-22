package com.github.ququzone.common;

/**
 * number tools.
 *
 * @author Yang XuePing
 */
public class NumberTools {
    private NumberTools() {
    }

    public static String bytesToHex(byte[] bytes) {
        String result = "";
        for (byte aByte : bytes) result += byteToHex(aByte);
        return result.toLowerCase();
    }

    public static String byteToHex(byte bt) {
        String temp = Integer.toHexString(bt & 0XFF);
        if (temp.length() == 1) {
            return "0" + temp;
        } else {
            return temp;
        }
    }
}
