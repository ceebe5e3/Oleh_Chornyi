package ua.nure.chornyi.SummaryTask4.web.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Password encrypt class
 */
public class Encryption {
    private final static String ALGORITHM = "MD5";

    private Encryption() {
    }
    public static String encrypt(String toBeEncrypted) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            byte[] bytes = toBeEncrypted.getBytes();
            byte[] digested = messageDigest.digest(bytes);
            for (byte b : digested) {
                stringBuilder.append(Integer.toHexString(0xff & b));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
