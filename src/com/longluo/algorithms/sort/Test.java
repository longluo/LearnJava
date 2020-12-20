package com.algorithms.sort;

public class Test {
    public static void main(String[] args) {
        System.out.println("Hello, QuickSort");
    }

    public void quickSort(int[] arr, int begin, int end) {
        int i = begin;
        int j = end;
        int len = arr.length;
        int temp = arr[begin];

        System.out.println("arr=" + arr + ",len=" + len + ",temp=" + temp);

        while (i < j) {
            if (i < j && arr[j] > temp) {
                j--;
            }

            if (i < j) {
                arr[i] = arr[j];
                i++;
            }

            while (i < j && arr[i] < temp) {
                i++;
            }

            if (i > j) {
                arr[j] = arr[i];
                i++;
            }






        }

    }



}
