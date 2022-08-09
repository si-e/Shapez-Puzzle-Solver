package util;

public enum Direction {

    TOP(0, +1, 0),
    LEFT(-1, 0, 270),
    RIGHT(+1, 0, 90),
    BOTTOM(0, -1, 180);


    public static Direction fromVector(int... vector) {
        if (vector[0] == 0 && vector[1] == 1)
            return Direction.TOP;
        else if (vector[0] == -1 && vector[1] == 0)
            return Direction.LEFT;
        else if (vector[0] == 1 && vector[1] == 0)
            return Direction.RIGHT;
        else if (vector[0] == 0 && vector[1] == -1)
            return Direction.BOTTOM;
        else
            return null;
    }

    public static Direction fromRotation(int rotation) {
        switch (rotation) {
            case 0:
                return Direction.TOP;
            case 90:
                return Direction.RIGHT;
            case 180:
                return Direction.BOTTOM;
            case 270:
                return Direction.LEFT;
            default:
                return null;
        }
    }

    public final int dx, dy, rotation;

    Direction(int dx, int dy, int rotation) {
        this.dx = dx;
        this.dy = dy;
        this.rotation = rotation;
    }

    public Direction invert() {
        return fromVector(-dx, -dy);
    }

    @Override
    public String toString() {
        switch (this) {
            case TOP: return "↑";
            case RIGHT: return "→";
            case BOTTOM: return "↓";
            case LEFT: return "←";
            default: return "·";
        }
    }
}
