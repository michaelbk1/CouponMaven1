package com.bnhp.CouponMaven.utils;


public class Print {

    private static int count = 1;
    private static final int SIZE = 100;

    public static void printCaption(String text){
        int len = text.length();

        System.out.println();
        int spaces = (SIZE-len)/2;

        for (int i = 0; i < spaces; i++) {
            System.out.print("@");
        }
        System.out.print("  ");
        System.out.print(text);
        System.out.print("  ");
        for (int i = 0; i < spaces; i++) {
            System.out.print("@");
        }
        System.out.println();
    }
}
