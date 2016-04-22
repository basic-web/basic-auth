package com.github.ququzone.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5 hash.
 *
 * @author Yang XuePing
 */
public class MD5 {
    private MD5() {
    }

    public static byte[] digestBytes(String... infos) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            for (String info : infos) {
                messageDigest.update(info.getBytes());
            }
        } catch (NoSuchAlgorithmException e) {
            // can't occur
        }
        return messageDigest != null ? messageDigest.digest() : new byte[0];
    }

    public static String digestHexString(String... infos) {
        return NumberTools.bytesToHex(digestBytes(infos));
    }

    public static void main(String[] args) {
        System.out.println(MD5.digestHexString("23&5#9%^IFDS8*(_9785!dsfRDF35(P", "admin"));
    }
}
