package com.iqiyi.channel;


public class ChannelInterfaceTest {
    public static final int secureCode1 = 1771777171;
    public static final String secureCode2 = "L8d:d^)DBei";

    public static final String oemOppoKey = "100202020212683645b3e876cb661d63";
    public static final String UA_VERSION = "4.4.2";

    public static void main(String[] args) {
        System.out.println("test=" + getEncryptTimestamp());
        System.out.println("sign=" + getSign());
    }

/*    public static String encryptAndUncrypt(String value, char secret) {
        byte[] bt = value.getBytes();
        for (int i = 0; i < bt.length; i++) {
            bt[i] = (byte) (bt[i] ^ (int)secret);
        }

        return new String(bt, 0, bt.length);
    }*/

    public static String getTimestamp() {
        long time = System.currentTimeMillis();
        int seconds = (int) (time / 1000);

        System.out.println("time=" + time + " seconds=" + seconds);

        String timestamp = String.valueOf(seconds);

        return timestamp;
    }

    public static String getEncryptTimestamp() {
        long time = System.currentTimeMillis();
        int seconds = (int) (time / 1000);

        int test = seconds ^ secureCode1;

        System.out.println("seconds=" + seconds + ",test=" + test);

        return String.valueOf(test);
    }

    public static String getSign() {
        StringBuilder signStr = new StringBuilder();

        signStr.append(getTimestamp());
        signStr.append(secureCode2);
        signStr.append(oemOppoKey);
        signStr.append(UA_VERSION);

        return MD5.GetMD5Code(signStr.toString());
    }

}
