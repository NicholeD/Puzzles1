package com.kenzie.breadthfirstsearch.wordsearch;

import com.kenzie.breadthfirstsearch.wordsearch.sharedmodel.Coordinate;
import com.kenzie.breadthfirstsearch.wordsearch.sharedmodel.Direction;

import java.util.*;

import static com.kenzie.breadthfirstsearch.wordsearch.SampleWordSearches.SORE_SEARCH;

/**
 * Class for completing word search puzzles.
 */
public class WordSearcher {
    private final WordSearch wordSearch;

    /**
     * Create a word search instance for the provided problem.
     * @param wordSearch - the word search puzzle to solve
     */
    public WordSearcher(WordSearch wordSearch) {
        this.wordSearch = wordSearch;
    }

    /**
     * Main method for manual testing.
     *
     * @param args - unused
     */
    public static void main(String[] args) {
        WordSearcher wordSearcher = new WordSearcher(SORE_SEARCH);

        System.out.println(wordSearcher.calculateWordCounts());
    }

    /**
     * Calculate how many ways (permutations) you can use the letters in the puzzle to spell
     * each word provided as part of the puzzle.
     *
     * @return a Map of the desired words, and how many ways (permutations) you can use the letters in the puzzle to
     * spell each word provided as part of the puzzle.
     */
    public Map<String, Long> calculateWordCounts() {
       Map<String, Long> wordCounts = new HashMap<>();

       for (String word : wordSearch.getWordsToFind()) {
           Long count = 0L;
           int width = wordSearch.getWidth();
           int height = wordSearch.getHeight();

           for (int i = 0; i < height; i++) {
               for (int j = 0; j < width; j++) {
                   if (wordSearch.getCharacterAt(new Coordinate(i, j)) == word.charAt(0)) {
                       Queue<Coordinate> checkQueue = new LinkedList<>();
                       Queue<Coordinate> nextCoordQueue = new LinkedList<>();
                       Coordinate currentCoord = new Coordinate(i, j);

                       checkingDirection(height, width, Direction.ALL_DIRECTIONS, checkQueue, currentCoord);

                       for (int n = 1; n < word.length(); n++) {
                           if (n == word.length() - 1) {
                               while (!checkQueue.isEmpty()) {
                                   Coordinate currentLetter = checkQueue.poll();

                                   if (wordSearch.getCharacterAt(currentLetter) == word.charAt(n)) {
                                       count+=1;
                                   }
                               }
                           } else {
                               while (!checkQueue.isEmpty()) {
                                   Coordinate currentLetter = checkQueue.poll();
                                   if (wordSearch.getCharacterAt(currentLetter) == word.charAt(n)) {
                                       checkingDirection(height, width, Direction.ALL_DIRECTIONS, nextCoordQueue, currentLetter);
                                   }
                               }
                               while (!nextCoordQueue.isEmpty()) {
                                   checkQueue.add(nextCoordQueue.poll());
                               }
                           }
                       }
                   }
               }
           }
           wordCounts.put(word, count);
       }

        return wordCounts;
    }

    private void checkingDirection(int height, int width, List<Direction> directions, Queue<Coordinate> checkQueue,
                                   Coordinate coordinate) {
        for (Direction direction : directions) {
            Coordinate newCoord = direction.addToCoordinate(coordinate);
            int x = newCoord.getRow();
            int y = newCoord.getColumn();

            if (x >= 0 && x < height && y >= 0 && y < width) {
                checkQueue.add(newCoord);
            }

        }

    }
}
