package game.buildings;


import game.components.Acceptor;
import game.components.Ejector;
import game.items.Item;
import game.items.Shape;
import util.Direction;

public class Cutter extends Building {

    protected static Cutter[] variants;

    static {
        Cutter.variants = new Cutter[2];
        Acceptor[] acc = {new Acceptor(0, 0, Direction.BOTTOM, Shape.class)};
        {
            Ejector[] eje = {
                    new Ejector(0, 0, Direction.TOP),
                    new Ejector(1, 0, Direction.TOP)};
            Cutter.variants[0] = new Cutter(2, 1, acc, eje);
        }
        {
            Ejector[] eje = {
                    new Ejector(0, 0, Direction.TOP),
                    new Ejector(1, 0, Direction.TOP),
                    new Ejector(2, 0, Direction.TOP),
                    new Ejector(3, 0, Direction.TOP)};
            Cutter.variants[1] = new Cutter(4, 1, acc, eje);
        }
    }

    private Cutter(int w, int h, Acceptor[] acceptors, Ejector[] ejectors) {
        super(w, h, acceptors, ejectors);
    }

    @Override
    public Item[] process(Item[] items) {
        if (this.ejectors.length == 2) {
            return new Shape[]{
                    ((Shape) items[0]).getQuadrants(3, 4),
                    ((Shape) items[0]).getQuadrants(1, 2)};
        } else {
            return new Shape[]{
                    ((Shape) items[0]).getQuadrants(1),
                    ((Shape) items[0]).getQuadrants(2),
                    ((Shape) items[0]).getQuadrants(3),
                    ((Shape) items[0]).getQuadrants(4)
            };
        }
    }

    @Override
    public String toString() {
        return "C";
    }
}
