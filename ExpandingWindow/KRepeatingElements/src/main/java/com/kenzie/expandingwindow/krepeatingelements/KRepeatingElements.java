package com.kenzie.expandingwindow.krepeatingelements;

import java.util.Arrays;
/**
 * Contains a problem that can be solved using the Expanding Window Technique.
 */
public class KRepeatingElements {

    /**
     * Given a string s and an integer k, return the length of the longest substring of s such that the
     * frequency of each character in this substring is greater than or equal to k.
     *
     * Example:
     *   s = aaabb
     *   k = 3
     *
     *   result = aaa
     *
     * @param s - the string from which to identify the longest substring where each character appears
     *          at least k times. s will contain only lowercase letters.
     * @param k - the minimum frequency which each character should appear in the substring. k will be
     *          a postive number.
     * @return the length of the longest substring of s where each character appears at least k times.
     */
    public static int kRepeatingElements(String s, int k) {
        char[] stringArray = s.toCharArray();
        int[]  countArray = new int[26];
        int count = 0;

        for (int i = 0; i <= distinctLettersCount(s); i++) {
            Arrays.fill(countArray, 0);
            int start = 0;
            int end = 0;
            int index = 0;
            int distinct = 0;
            int kLengthOrMore = 0;

            while(end < stringArray.length) {
                if (distinct <= i) {
                    index = stringArray[end] - 'a';

                    if (countArray[index] == 0) distinct++;
                    countArray[index]++;

                    if (countArray[index] == k) kLengthOrMore++;
                    end++;

                    } else {
                        index = stringArray[start] - 'a';

                        if (countArray[index] == k) kLengthOrMore--;
                        countArray[index]--;


                        if (countArray[index] == 0) distinct--;
                        start++;

                    }
                    if (distinct == i && distinct == kLengthOrMore) {
                        count = Math.max(end - start, count);
                    }
            }
        }
        return count;
    }

    private static int distinctLettersCount(String s) {
        boolean map[] = new boolean[26];
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (!map[s.charAt(i) - 'a']) {
                count++;
                map[s.charAt(i) - 'a'] = true;
            }
        }
        return count;
    }
}
