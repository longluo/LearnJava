package com.interview.exam;

/**
 * Created by luolong on 15/11/26.
 */
public class Exam {
    public static void main(String[] args) {
        System.out.println("1. Math.round() ");
        mathRound();

        System.out.println("2. Loop");
        loopOutput();

        System.out.println("3. String Unchange");
        stringUnchange();

        System.out.println("4. Array Quest");
        arrayQuest();
    }

    public static void mathRound() {
        System.out.println("Math.round(11.5) = " + Math.round(11.5) + ", Math.round(-11.5) = " + Math.round(-11.5));
    }

    public static boolean loop(char ch) {
        System.out.print(ch);
        return true;
    }

    public static void loopOutput() {
        int i = 0;
        for (loop('A'); loop('B') && (i < 2); loop('C')) {
            i++;
            loop('D');
        }
    }

    public static void stringUnchange() {
        String str = new String("abc");
        char[] ch = {'a', 'b', 'c'};
        change(str, ch);
        System.out.println("str=" + str);
        System.out.println(ch);
    }

    public static void change(String str, char[] ch) {
        str = "gbc";
        ch[0] = 'g';
    }

    static class Foo {
        int mValue;

        Foo(int value) {
            mValue = value;
        }
    }

    public static void arrayQuest() {
        Foo[] a1 = {new Foo(1), new Foo(2), new Foo(3)};
        Foo[] a2 = new Foo[a1.length];

        System.arraycopy(a1, 0, a2, 0, a1.length);
        System.out.println((a1 == a2) + ", " + a1.equals(a2) + ", " + (a1[1] == a2[1]));
        System.out.println(a2[1].mValue);
    }

}
