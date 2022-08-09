package util;

import java.util.Iterator;

public class Bound implements Iterable<int[]> {

    private final int x, y;
    private final int dx, dy;

    public Bound(int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public Iterator<int[]> iterator() {
        return new Iterator<>() {
            int i = 0, j = 0;

            @Override
            public boolean hasNext() {
                return j < dy;
            }

            @Override
            public int[] next() {
                int xi = x + i++;
                int yj = y + j;
                if (i == dx) {
                    i = 0;
                    j++;
                }
                return new int[]{xi, yj};
            }
        };
    }

}
