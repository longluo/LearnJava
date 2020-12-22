package com.longluo.oop;

public class ParamPassing {
    private static int intStatic = 222;
    private static String stringStatic = "old string";
    private static StringBuilder stringBuilderStatic = new StringBuilder("old stringBuilder");

    public static void main(String[] args) {
        method(intStatic);
        method(stringStatic);
        method(stringBuilderStatic, stringBuilderStatic);

        System.out.println(intStatic);
        method();
        System.out.println(intStatic);

        System.out.println(stringStatic);
        System.out.println(stringBuilderStatic);
    }

    public static void method(int intStatic) {
        intStatic = 777;
    }

    public static void method() {
        intStatic = 888;
    }

    public static void method(String stringStatic) {
        stringStatic = "new string";
    }

    public static void method(StringBuilder stringBuilderStatic1, StringBuilder stringBuilderStatic2) {
        stringBuilderStatic1.append(".method.first-");
        stringBuilderStatic2.append("method.second-");

        stringBuilderStatic1 = new StringBuilder("new stringBuilder");
        stringBuilderStatic1.append("new method's append");
    }
}
