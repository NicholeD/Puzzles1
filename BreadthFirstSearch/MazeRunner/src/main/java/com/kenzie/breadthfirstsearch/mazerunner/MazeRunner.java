package com.kenzie.breadthfirstsearch.mazerunner;

import com.kenzie.breadthfirstsearch.mazerunner.model.MazePattern;
import com.kenzie.breadthfirstsearch.mazerunner.model.MazeSpace;
import com.kenzie.breadthfirstsearch.mazerunner.sharedmodel.Direction;
import com.kenzie.breadthfirstsearch.mazerunner.sharedmodel.Node;
import com.kenzie.breadthfirstsearch.mazerunner.utils.MazeGenerator;

import java.util.*;
import java.util.stream.Collectors;

import static com.kenzie.breadthfirstsearch.mazerunner.SampleMazes.MAZE_ONE_EXIT;

/**
 * Class for running through mazes.
 */
public class MazeRunner {
    private Direction direction;

    /**
     * Private constructor, as all methods are static.
     */
    private MazeRunner() {}

    /**
     * Utility main method, to run MazeRunner methods without adding tests.
     *
     * @param args - Method arguments (unused)
     */
    public static void main(String[] args) {
        System.out.println(MazeRunner.findClosestExit(MAZE_ONE_EXIT));
    }

    /**
     * Finds the exit out of the maze closest to its entrance.
     *
     * @param pattern - the pattern of maze being run through
     * @return the closest reachable exit to the maze, or empty if there are no reachable exits
     */
    public static Optional<MazeSpace> findClosestExit(MazePattern pattern) {
        Optional<Node<MazeSpace>> entrance = MazeGenerator.generateMaze(pattern);

        if (entrance.isEmpty()) {
            return Optional.empty();
        }

        Set<Node<MazeSpace>> visitedNodes = new HashSet<>();
        Queue<Node<MazeSpace>> currentLevel = new LinkedList<>();
        currentLevel.add(entrance.get());

        while (!currentLevel.isEmpty()) {
            Node<MazeSpace> currentNode = currentLevel.poll();

            if (currentNode.getValue().isExit()) {
                return Optional.ofNullable(currentNode.getValue());
            } else {
                visitedNodes.add(currentNode);

                for (Node<MazeSpace> node : currentNode.getNeighbors()) {
                    if (!visitedNodes.contains(node)) {
                        currentLevel.add(node);
                    }
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Finds the path to the exit out of the maze closest to its entrance.
     *
     * @param pattern - the pattern of maze being run through
     * @return the path closest reachable exit to the maze, or an empty list if there are no reachable exits
     */
    public static List<MazeSpace> findShortestPathToExit(MazePattern pattern) {
        Optional<Node<MazeSpace>> entrance = MazeGenerator.generateMaze(pattern);

        if (entrance.isEmpty()) {
            return Collections.emptyList();
        }

        Set<Node<MazeSpace>> visitedNodes = new HashSet<>();
        Queue<List<Node<MazeSpace>>> currentLevel = new LinkedList<>();
        Queue<List<Node<MazeSpace>>> nextLevel = new LinkedList<>();
        List<Node<MazeSpace>> currentPath = new ArrayList<>();

        currentPath.add(entrance.get());
        currentLevel.add(currentPath);

        while(!currentLevel.isEmpty()) {
            currentPath = currentLevel.poll();
            Node<MazeSpace> currentNode = currentPath.get(currentPath.size() - 1);
            visitedNodes.add(currentNode);

            if (currentNode.getValue().isExit()) {
                return currentPath.stream()
                        .map(Node::getValue)
                        .collect(Collectors.toList());
            } else {
                for (Node<MazeSpace> node : currentNode.getNeighbors()) {
                    if (!visitedNodes.contains(node)) {
                        List<Node<MazeSpace>> path = new ArrayList<>(currentPath);
                        path.add(node);
                        nextLevel.add(path);
                    }
                }
            }

            if (currentLevel.isEmpty()) {
                currentLevel = nextLevel;
                nextLevel = new LinkedList<>();
            }
        }

        return Collections.emptyList();
    }
}
