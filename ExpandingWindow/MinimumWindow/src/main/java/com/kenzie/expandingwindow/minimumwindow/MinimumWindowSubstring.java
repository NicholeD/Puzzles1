package com.kenzie.expandingwindow.minimumwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains a problem that can be solved using the Expanding Window Technique.
 */
public class MinimumWindowSubstring {

    /**
     * Given two strings s and t, return the minimum window substring of s such that every character in
     * t (including duplicates) is included in the window. If there is no such substring, return the
     * empty string "".
     *
     * Example:
     *   s = "ADOBECODEBANC"
     *   t = "ABC"
     *
     *   result = "BANC"
     *
     * @param s - the string from which to identify the shortest substring where each character in t appears.
     * @param t - the string containing all the characters that must appear in the substring from s.
     * @return the shortest substring of s in which each character in t appears.
     */
    public static String minimumWindowSubstring(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char tChar : t.toCharArray()){
            map.put(tChar, map.getOrDefault(tChar,0) + 1);
        }
        int start = 0;
        int minStartIndex = 0;
        int minLen = s.length() + 1;
        int missing = t.length();
        for (int i = 0; i < s.length(); i++){
            char sChar = s.charAt(i);
            if (map.containsKey(sChar)){
                map.put(sChar, map.get(sChar) - 1);
                if (map.get(sChar) >= 0){
                    missing--;
                }
                while (missing == 0){
                    if (i - start + 1 < minLen){
                        minLen = i - start + 1;
                        minStartIndex = start;
                    }
                    char sChar2 = s.charAt(start);
                    if (map.containsKey(sChar2)){
                        map.put(sChar2, map.get(sChar2) + 1);
                        if (map.get(sChar2) > 0){
                            missing++;
                        }
                    }
                    start++;
                }
            }
        }
        if (minLen > s.length()){
            return "";
        }
        return s.substring(minStartIndex, minStartIndex + minLen);
    }
}
