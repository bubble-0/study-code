package com.magic.springboot.leetcode;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {112, 22, 44, 123, 66, 55, 422, 1, 22};
        int tmp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));

    }
}
