package com.example.st_project.st;

public class kmp {
    public static boolean search(String text, String pattern) {
        int[] lps = computeLPSArray(pattern);
        int m = pattern.length();
        int n = text.length();
        int i = 0, j = 0;

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                j = lps[j - 1];
               return true;

            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return false;
    }

    private static int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0;
        int i = 1;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

//    public static void main(String[] args) {
//        String text = "ABABDABACDABABCABAB";
//        String pattern = "BBBBB";
//        search(text, pattern);
//    }
}
