package org;

import java.util.Arrays;

public class Main {

    int[] arraySum(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length <= 0) return arr2;
        if (arr2 == null || arr2.length <= 0) return arr1;

        int i = arr1.length - 1;
        int j = arr2.length - 1;
        int carry = 0;

        int[] output = new int[Math.max(arr1.length, arr2.length) + 1];
        while (i >= 0 && j >= 0) {
            int sum = arr1[i] + arr2[j] + carry;
            output[i + 1] = sum % 10;

            carry = (sum >= 10) ? 1 : 0;
            i--; j--;
        }

        int leftovers = Math.max(i, j);
        for (int k = leftovers; k >= 0; k--) {
            output[k + 1] = carry + [k];
            carry = 0;
        }

        return output;
    }

    public static void main(String[] args) {

        Main main = new Main();
        int[] result = main.arraySum(new int[] {3, 4, 5}, new int[] {9, 4});

        System.out.println("Expected output: [4, 3, 9], actual: " + Arrays.toString(result));
    }
}
