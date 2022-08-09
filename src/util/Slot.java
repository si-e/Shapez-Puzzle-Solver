package util;

import java.util.Objects;

/**
 * The edge of tile.
 * Each tile has 4 slots for different direction.
 * Tile is the cell of grid.
 */
public class Slot implements Computable {

    private final int x, y;
    private final Direction direction;
    private int[][] matrix = null;


    public Slot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public Position nextPosition() {
        int x = this.x + this.direction.dx;
        int y = this.y + this.direction.dy;
        int r = this.direction.rotation;
        return new Position(x, y, r);
    }

    public Slot oppositeSlot() {
        int x = this.x + this.direction.dx;
        int y = this.y + this.direction.dy;
        Direction d = this.direction.invert();
        return new Slot(x, y, d);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot slot = (Slot) o;
        return x == slot.x && y == slot.y && direction == slot.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, direction);
    }

    @Override
    public Slot fromMatrix(int[][] matrix) {
        int x = matrix[0][0];
        int y = matrix[1][0];
        Direction direction = Direction.fromVector(matrix[0][1], matrix[1][1]);
        return new Slot(x, y, direction);
    }

    @Override
    public int[][] toMatrix() {
        if (this.matrix == null) {
            this.matrix = new int[3][2];
            this.matrix[0][0] = x;
            this.matrix[1][0] = y;
            this.matrix[2][0] = 1;
            this.matrix[0][1] = direction.dx;
            this.matrix[1][1] = direction.dy;
            this.matrix[2][1] = 0;
        }
        return matrix;
    }
}
