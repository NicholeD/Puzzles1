package com.kenzie.gettingstarted.balance;

import java.util.*;

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
    public boolean smileyAreParensBalanced(String text) {
        List<Character> openGroupings = getOpenGroupingChars();
        List<Character> closingGroupings = getClosingGroupingChars();

        Stack<Character> openParensStack = new Stack<>();
        char[] textList = text.toCharArray();

        for (char i : textList) {

            if (openParensStack.empty() && (openGroupings.contains(i) || i == ':')) {
                openParensStack.push(i);

            } else if (openParensStack.empty() && closingGroupings.contains(i)) {
                return false;

            } else if (!openParensStack.empty()) {
                char charPeek = openParensStack.peek();

                if (charPeek == ':' && i == ':') {
                    openParensStack.pop();
                    openParensStack.push(i);

                } else if (charPeek == ':') {
                    openParensStack.pop();

                } else if (openGroupings.contains(i) || i == ':'){
                    openParensStack.push(i);

                } else if (closingGroupings.contains(i) && isGroupingMatch(charPeek, i)){
                    openParensStack.pop();

                } else if (closingGroupings.contains(i) && !isGroupingMatch(charPeek, i)) {
                    return false;
                }
            }
        }
        //return whether the stack is empty
        return openParensStack.empty();
    }

    public List<Character> getClosingGroupingChars() {
        List<Character> groupingChars = new ArrayList<Character>();
        groupingChars.add(')');
        groupingChars.add('}');
        groupingChars.add(']');
        return groupingChars;
    }

    public List<Character> getOpenGroupingChars() {
        List<Character> groupingChars = new ArrayList<Character>();
        groupingChars.add('(');
        groupingChars.add('{');
        groupingChars.add('[');
        return groupingChars;
    }

    private boolean isGroupingMatch(Character popped, Character index) {
        Map<Character, Character> groupingPairs = new HashMap<>();
        groupingPairs.put('(', ')');
        groupingPairs.put('{', '}');
        groupingPairs.put('[', ']');

        if (groupingPairs.get(popped) == index) {
            return true;
        }
        return false;
    }


}
