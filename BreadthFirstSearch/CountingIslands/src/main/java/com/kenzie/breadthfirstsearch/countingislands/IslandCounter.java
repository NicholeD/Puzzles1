package com.kenzie.breadthfirstsearch.countingislands;

import com.kenzie.breadthfirstsearch.countingislands.sharedmodel.Coordinate;
import com.kenzie.breadthfirstsearch.countingislands.sharedmodel.Direction;
import com.kenzie.breadthfirstsearch.countingislands.sharedmodel.Node;
import org.apache.commons.lang.ArrayUtils;

import java.util.*;

/**
 * Counts the number of islands for a map.
 */
public class IslandCounter {
    private final int width;
    private final int height;
    private final int[][]map;
    private Direction direction;

    public IslandCounter(int width, int height, int[][] map) {
        this.width = width;
        this.height = height;
        this.map = map;
    }

    /**
     * Main method for manual testing.
     * @param args - unused
     */
    public static void main(String[] args) {
        IslandCounter islandCounter = new IslandCounter(5, 5, SamplesMaps.FIVE_ISLAND_MAP);
        int islandCount = islandCounter.countIslands();
        System.out.println(String.format("Found %s islands.", islandCount));
    }

    /**
     * Counts the number of islands for the map.
     * @return the number of islands for the map.
     */
    public int countIslands() {
        if (ArrayUtils.isEmpty(map)) {
            return 0;
        }

        int islandCount = 0;
        boolean[][] isIsland = new boolean[map.length][map[0].length];

        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                if (map[i][j] == 1 && !isIsland[i][j]){
                    Queue<Coordinate> checkQueue = new LinkedList<>();
                    List<Coordinate> island = new ArrayList<>();
                    checkQueue.add(new Coordinate(i,j));
                    isIsland[i][j] = true;
                    island.add(new Coordinate(i,j));

                    while(!checkQueue.isEmpty()){
                        Coordinate coordinate = checkQueue.poll();
                        for (Direction d : Direction.ALL_DIRECTIONS){
                            Coordinate newCoord = d.addToCoordinate(coordinate);
                            int x = newCoord.getRow();
                            int y = newCoord.getColumn();
                            if (x >= 0 && x < map.length && y >= 0 && y < map[0].length && map[x][y] == 1 && !isIsland[x][y]){
                                island.add(newCoord);
                                isIsland[x][y] = true;
                                checkQueue.add(newCoord);
                            }
                        }
                    }
                    if (island.size() > 0){
                        islandCount++;
                    }
                }
            }
        }
        return islandCount;

    }

}
