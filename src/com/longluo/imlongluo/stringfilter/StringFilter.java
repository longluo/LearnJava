package com.imlongluo.stringfilter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;



public class StringFilter {

    public static void main(String[] args) {
//        testStringFilter();
        
        String s1 = "1445938584329.499";
//        String s2 = s1.split(".")[0];
        String s2 = s1.substring(0, 13);
        System.out.println("s1=" + s1 + ",s2=" + s2);
    }

    // ���������ַ�
    public static String StringFilter(String str) throws PatternSyntaxException {
        // ֻ������ĸ������
        // String regEx = "[^a-zA-Z0-9]";
        // ��������������ַ�
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~��@#��%����&*��������+|{}������������������������]";
//        String regEx = ".*[\\\\/*:?<>|\"]+?";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);

        return m.replaceAll(" ").trim();
    }

    public static void testStringFilter() throws PatternSyntaxException {
        String str = "*adCVs*34_a _09_b5*[/435^*&�ǳ�()^$$&*).{}+.|.)%%*(*.�й�}34{45[]12.fd'*&999���������ĵ��ַ�������{}��������������������";
        System.out.println(str);
        System.out.println(StringFilter(str));

        String testString = "��Զͬ������� ������Ļ��|ľ�幭 & ����ר��|409741";
        System.out.println(testString);
        System.out.println(StringFilter(testString));
    }
}
