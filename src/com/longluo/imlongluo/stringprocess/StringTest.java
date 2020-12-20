package com.imlongluo.stringprocess;

public class StringTest {

    public static void main(String[] args) {
        String test = "#8:#123:#6:#5:#2";
        
        getNumberArray(test);
        
        getId();
    }

    public static void getNumberArray(String inStr) {
        int len = inStr.length();
        String[] words = inStr.split("#");
        String result = null;

        System.out.println("inStr=" + inStr + ",len=" + len + ",words=" + words);

        for (String word : words) {
            if (word.length() > 0) {
                System.out.println("word=" + word + ",len=" + word.length());

                if ((word.charAt(word.length() - 1) == ':')) {
                    result = word.substring(0, word.length() - 1);
                } else {
                    result = word;
                }
            }


            System.out.println("result=" + result);
        }

    }
    
    private static void getId() {
    	String src = "756127@kaojin-im/Smack";
    	
    	String[] words = src.split("@");
    	
    	System.out.println("" + words[0] + "," + "756127@kaojin-im/Smack".split("@")[0]);
    	
    }

}
