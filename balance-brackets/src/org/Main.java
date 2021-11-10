package org;

import java.util.Stack;

public class Main {

    /*
        A bracket is considered to be any one of the following characters: (, ), {, }, [, or ].
        We consider two brackets to be matching if the first element is an open-bracket, e.g., (, {, or [, and the second bracket is a close-bracket of the same type, e.g., ( and ), [ and ], and { and } are the only pairs of matching brackets.
        Furthermore, a sequence of brackets is said to be balanced if the following conditions are met:
        - The sequence is empty, or
        - The sequence is composed of two, non-empty, sequences both of which are balanced, or
        - The first and last brackets of the sequence are matching, and the portion of the sequence without the first and last elements is balanced.
        - You are given a string of brackets. Your task is to determine whether each sequence of brackets is balanced. If a string is balanced, return true, otherwise, return false

        Signature
            bool isBalanced(String s)

        Input
            String s with length between 1 and 1000
        Output
            A boolean representing if the string is balanced or not

        Example 1
        s = {[()]}
        output: true

        Example 2
        s = {}()
        output: true

        Example 3
        s = {(})
        output: false

        Example 4
        s = )
        output: false
     */
    // () bracket type
    boolean isRoundBracket(char prev, char curr) {
        return prev == '(' && curr == ')';
    }

    // [] bracket type
    boolean isBoxBracket(char prev, char curr) {
        return prev == '[' && curr == ']';
    }

    // {} bracket type
    boolean isCurlyBracket(char prev, char curr) {
        return prev == '{' && curr == '}';
    }

    boolean isPairOfMatching(char pre, char curr) {
        return isRoundBracket(pre, curr)
                || isBoxBracket(pre, curr)
                || isCurlyBracket(pre, curr);
    }

    boolean isLeftBracket(char c) {
        return c == '{' || c == '(' || c == '[';
    }

    boolean isBalanced(String s) {
        // Write your code here
        if (s.length() <= 1) {
            return false; // one bracket cannot be balanced
        }

        Stack<Character> brackets = new Stack<>();

        char[] str = s.toCharArray();
        // O(n)
        for (int i = 0; i < str.length; i++) {
            char curr = str[i];
            if (isLeftBracket(curr)) {
                brackets.push(curr);
            } else  if (brackets.isEmpty()) {
                return false;
            } else {
                char prev = brackets.peek();
                if (isPairOfMatching(prev, curr)) {
                    brackets.pop();
                }
            }
        }

        return brackets.isEmpty();
    }












    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;
    void check(boolean expected, boolean output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        }
        else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            System.out.print(expected);
            System.out.print(" Your output: ");
            System.out.print(output);
            System.out.println();
        }
        test_case_number++;
    }
    void printString(String str) {
        System.out.print("[" + str + "]");
    }

    public void run() {
        String s_1 = "{[(])}";
        boolean expected_1 = false;
        boolean output_1 = isBalanced(s_1);
        check(expected_1, output_1);

        String s_2 = "{{[[(())]]}}";
        boolean expected_2 = true;
        boolean output_2 = isBalanced(s_2);
        check(expected_2, output_2);

        // Add your own test cases here
        String s_3 = ")";
        boolean expected_3 = false;
        boolean output_3 = isBalanced(s_3);
        check(expected_3, output_3);

        String s_4 = ")(";
        boolean expected_4 = false;
        boolean output_4 = isBalanced(s_4);
        check(expected_4, output_4);
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
