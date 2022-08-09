package game.buildings;


import game.components.Acceptor;
import game.components.Ejector;
import game.items.Item;
import game.items.Shape;
import util.Direction;

public class Rotater extends Building {

    protected static Rotater[] variants;

    static {
        Rotater.variants = new Rotater[3];
        Acceptor[] acc = {new Acceptor(0, 0, Direction.BOTTOM, Shape.class)};
        Ejector[] eje = {new Ejector(0, 0, Direction.TOP)};
        {
            Rotater.variants[0] = new Rotater(1, 1, acc, eje, 90);
        }
        {
            Rotater.variants[1] = new Rotater(1, 1, acc, eje, 180);
        }
        {
            Rotater.variants[2] = new Rotater(1, 1, acc, eje, 270);
        }
    }

    private final int degree;

    private Rotater(int w, int h, Acceptor[] acceptors, Ejector[] ejectors, int degree) {
        super(w, h, acceptors, ejectors);
        this.degree = degree;
    }

    @Override
    public Item[] process(Item[] items) {
        return new Shape[]{((Shape) items[0]).rotate(degree)};
    }

    @Override
    public String toString() {
        return "R";
    }
}
