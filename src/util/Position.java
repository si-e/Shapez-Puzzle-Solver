package util;

/**
 * Position is coordinate and orientation.
 */
public class Position implements Computable {

    public static final Position ORIGIN = new Position(0, 0, 0);


    public final int x, y;
    public final int rotation;
    private int[][] matrix = null;

    public Position(int x, int y, int rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    @Override
    public Position fromMatrix(int[][] matrix) {
        int x = matrix[0][0];
        int y = matrix[1][0];
        int rotation = Direction.fromVector(matrix[0][1], matrix[1][1]).rotation;
        return new Position(x, y, rotation);
    }

    @Override
    public int[][] toMatrix() {
        if (this.matrix == null) {
            this.matrix = new int[3][2];
            this.matrix[0][0] = x;
            this.matrix[1][0] = y;
            this.matrix[2][0] = 1;
            this.matrix[0][1] = Direction.fromRotation(rotation).dx;
            this.matrix[1][1] = Direction.fromRotation(rotation).dy;
            this.matrix[2][1] = 0;
        }
        return matrix;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + rotation + ")";
    }
}
