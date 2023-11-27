package com.example.st_project.st;

public class RabinKarpStringMatching {
    private static final int PRIME = 101;

    public static Boolean search(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();
        int patternHash = hash(pattern, m);
        int textHash = hash(text, m);

        for (int i = 0; i <= n - m; i++) {
            if (patternHash == textHash && checkEqual(text, i, i + m - 1, pattern, 0, m - 1)) {
//                System.out.println("Pattern found at index " + i);
                return true;
            }

            if (i < n - m) {
                textHash = recalculateHash(text, i, i + m, textHash, m);
            }
        }
        return false;
    }

    private static int hash(String str, int length) {
        int hash = 0;
        for (int i = 0; i < length; i++) {
            hash += str.charAt(i) * Math.pow(PRIME, i);
        }
        return hash;
    }

    private static int recalculateHash(String str, int oldIndex, int newIndex, int oldHash, int patternLength) {
        int newHash = oldHash - str.charAt(oldIndex);
        newHash = newHash / PRIME;
        newHash += str.charAt(newIndex) * Math.pow(PRIME, patternLength - 1);
        return newHash;
    }

    private static boolean checkEqual(String str1, int start1, int end1, String str2, int start2, int end2) {

        while (start1 <= end1 && start2 <= end2) {
            if (str1.charAt(start1) != str2.charAt(start2)) {
                return false;
            }
            start1++;
            start2++;
        }
        return true;
    }


}
