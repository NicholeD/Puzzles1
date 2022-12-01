package com.kenzie.gettingstarted.balance;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * Drills to apply the problem-solving framework on variations of the Balanced Parentheses problem.
 */
public class BalancedParens {

    /**
     * Determine whether a string composed entirely of opening and closing parentheses is balanced.
     * If a closing paren appears without an opening paren, the string is not balanced.
     * If an opening paren is not closed by the end of the string, it is not balanced.
     *
     * BalancedParensTest includes some sample test cases.*
     *
     * @param parens A String of opening and closing parentheses
     * @return true if every opening and closing paren has a partner
     */
    public boolean areParensBalanced(String parens) {
        int count = 0;
        for (char c : parens.toCharArray()) {
            if (c == '(') {
                count++;
            }
            if (c == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    /**
     * Placeholder for the we-do classroom activity for Problem-Solving Framework.
     *
     * BalancedParensTest includes some sample test cases.
     *
     * @param text A text String, described in the classroom.
     * @return True as described in the classroom, false otherwise.
     */
    public boolean stackAreParensBalanced(String text) {
        if (text == null) {
            return false;
        }
        Stack<Character> openParensStack = new Stack<Character>();
        char[] textList = text.toCharArray();

        for (char i : textList) {
            if (i == '(' || i == '{' || i == '[') {
                openParensStack.push(i);
            } else if (i == ')' || i == '}' || i == ']') {
                if (openParensStack.empty()) {
                    return false;
                }
                char popped = openParensStack.pop();

                if ((i == ')' && popped != '(') || (i == '}' && popped != '{') || (i == ']' && popped != '[')) {
                    return false;
                }
            }
        }
        return openParensStack.empty();
    }

    /**
     * Placeholder for the you-do classroom activity for Problem-Solving Framework.
     *
     * BalancedParensTest includes some sample test cases.
     *
     * @param text A text String, described in the classroom.
     * @return True as described in the classroom, false otherwise.
     */
    public boolean yourMethodHere(String text) {
        return false;
    }

}
