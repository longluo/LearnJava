package com.algorithms.aes;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.iqiyi.channel.StringUtils;

public class AESUtil {

    static final String algorithmStr = "AES/ECB/PKCS5Padding";

    static private KeyGenerator keyGen;

    static private Cipher cipher;

    static boolean isInited = false;

    static String AESKey = "12,23,41,12,-21,13,-65,-65,3,61,-42,75,-42,-55,-115,4";

    static private void init() {

        try {
            keyGen = KeyGenerator.getInstance("AES");
            String pwd = "passordgggggg";
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(pwd.getBytes());
            keyGen.init(128, random);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            cipher = Cipher.getInstance(algorithmStr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        isInited = true;
    }

    public static byte[] GenKey() {
        if (!isInited) {
            init();
        }
        return keyGen.generateKey().getEncoded();
    }

    public static byte[] encrypt(byte[] content, byte[] keyBytes) {
        if (content == null) {
            return null;
        }
        byte[] encryptedText = null;
        if (!isInited) {
            init();
        }
        Key key = new SecretKeySpec(keyBytes, "AES");
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        try {
            encryptedText = cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptedText;
    }

    public static byte[] decrypt(byte[] content, byte[] keyBytes) {
        if (content == null) {
            return null;
        }
        byte[] originBytes = null;
        if (!isInited) {
            init();
        }

        Key key = new SecretKeySpec(keyBytes, "AES");
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        try {
            originBytes = cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return originBytes;
    }

    public static byte[] getPublicKey(String propertyValue) {
        if (StringUtils.isEmpty(propertyValue)) {
            return null;
        }
        String[] arr = propertyValue.split(",");
        if (arr.length != 16) {
            return null;
        }
        byte[] publicKey = new byte[16];
        int i = 0;
        for (String str : arr) {
            publicKey[i++] = Byte.valueOf(str.trim());
        }
        return publicKey;
    }

    public static byte[] getAESKey() {
        return getPublicKey(AESKey);
    }

}
