package com.algorithms.aes;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author ngh AES128 算法
 * <p>
 * CBC 模式
 * <p>
 * PKCS7Padding 填充模式
 * <p>
 * CBC模式需要添加一个参数iv
 * <p>
 * 介于java 不支持PKCS7Padding，只支持PKCS5Padding 但是PKCS7Padding 和 PKCS5Padding
 * 没有什么区别 要实现在java端用PKCS7Padding填充，需要用到bouncycastle组件来实现
 */
public class AESUtils {
    private final static String IVKEY = "9greqde7banhkxfv";
    private static Cipher cipher = null;

    public static byte[] encrypt(String data, String key) {
        try {

            cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            SecretKeySpec keyspec = new SecretKeySpec(fullZore(key, blockSize),
                    "AES");
            IvParameterSpec ivspec = new IvParameterSpec(fullZore(IVKEY,
                    blockSize));
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(fullZore(data, blockSize));
            return encrypted;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] decrypt(byte[] data, String key) {
        try {
            cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            SecretKeySpec keyspec = new SecretKeySpec(fullZore(key, blockSize),
                    "AES");
            IvParameterSpec ivspec = new IvParameterSpec(fullZore(IVKEY,
                    blockSize));
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] decrypted = cipher.doFinal(data);
            return decrypted;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] fullZore(String data, int blockSize) {
        byte[] dataBytes = data.getBytes();
        int plaintextLength = dataBytes.length;
        if (plaintextLength % blockSize != 0) {
            plaintextLength = plaintextLength
                    + (blockSize - (plaintextLength % blockSize));

        }
        byte[] plaintext = new byte[plaintextLength];
        System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
        return plaintext;
    }

    /**
     * 加密和解密
     *
     * @param args
     */
    public static void main2(String[] args) {
        /** 数据初始化 **/
        String content = "{\"port\":\"5222\",\"host\":\"10.10.121.29\",\"userId\":755025,\"password\":\"755025+755025\",\"has\":1,\"serviceName\":\"linux-x57o\"}";
        String pwds = "r593iole95han3g5";
        /** 加密 **/
        System.out.println("加密前：" + content);
        byte[] encryptResultStr = encrypt(content, pwds);
        System.out.println("加密后：" + encryptResultStr);
        /** 解密 **/
        System.out.println("base64EncodeResult:"
                + Base64.encodeToString(encryptResultStr, Base64.DEFAULT));
        byte[] decryptString = decrypt(encryptResultStr, pwds);
        System.out.println("解密后：" + new String(decryptString));

        System.out.println();
        System.out.println();
    }

    /**
     * 加密和解密
     *
     * @param args
     */
    public static void main(String[] args) {
        /** 数据初始化 **/
        String content = "WLqfiUiV+YTgKhcgEnBhoMxL8cWRWu4C5Unwz3YLV1elWXOXuzx/YOgG7e61HhnKxGSKR9+ao19SSztI2eewgQBJQrt/WO4yEuACRjtSHXBhDbSM7ooDbKKFqRcLgmXanq07V1uZeOquCq808esBxzjbi9Xacj4UBEZDfJ7d2Z9mfb6MzP6o30HKiMwMANTL";
        String pwds = "r593iole95han3g5";
        byte[] decode64 = Base64.decode(content, Base64.DEFAULT);
        byte[] decryptString = decrypt(decode64, pwds);
        System.out.println("解密后：" + new String(decryptString));
    }
}
