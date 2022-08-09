package game.buildings;

public class Block {
    public int x, y;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "O";
    }
}
