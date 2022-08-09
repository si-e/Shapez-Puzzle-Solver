package util;

import java.util.function.Function;

public class Util {

    public static Function<Computable, Computable> calTransform(Position p0, Position p1) {
        int[][] matrix = new int[3][3];
        int theta = -(p1.rotation - p0.rotation);
        matrix[0][0] = cos(theta);
        matrix[0][1] = -sin(theta);
        matrix[0][2] = p1.x - p0.x;
        matrix[1][0] = sin(theta);
        matrix[1][1] = cos(theta);
        matrix[1][2] = p1.y - p0.y;
        matrix[2][0] = 0;
        matrix[2][1] = 0;
        matrix[2][2] = 1;
        return p -> {
            int[][] vectors = p.toMatrix();
            int[][] r = multiply(matrix, vectors);
            return p.fromMatrix(r);
        };
    }

    private static int sin(int theta) {
        theta = (theta + 360) % 360;
        switch (theta) {
            case 0:
            case 180: return 0;
            case 90: return 1;
            case 270: return -1;
            default: return Integer.MIN_VALUE;
        }
    }

    private static int cos(int theta) {
        return sin(theta + 90);
    }

    private static int[][] multiply(int[][] a, int[][] b) {
        assert a[0].length == b.length;
        int[][] c = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }
    
    

}
