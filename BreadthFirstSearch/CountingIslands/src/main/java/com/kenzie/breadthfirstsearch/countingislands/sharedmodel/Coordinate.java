package com.kenzie.breadthfirstsearch.countingislands.sharedmodel;

import java.util.Objects;
import java.util.UUID;

import static java.util.UUID.randomUUID;

/**
 * An immutable class that represents coordinates within a 2D array in row:column coordinates.
 */
public class Coordinate {
    private final int row;
    private final int column;
    public UUID islandId;

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setIslandId(UUID uuid) {
        this.islandId = uuid;
    }

    public UUID getIslandId() {
        return islandId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return row == that.row &&
            column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return String.format("Coordinate{row: %s, column: %s}",
            row,
            column);
    }
}
