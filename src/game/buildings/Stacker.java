package game.buildings;


import game.components.Acceptor;
import game.components.Ejector;
import game.items.Item;
import game.items.Shape;
import util.Direction;

public class Stacker extends Building {

    protected static Stacker[] variants;

    static {
        Stacker.variants = new Stacker[1];
        Acceptor[] acc = {
                new Acceptor(0, 0, Direction.BOTTOM, Shape.class),
                new Acceptor(1, 0, Direction.BOTTOM, Shape.class)};
        Ejector[] eje = {new Ejector(0, 0, Direction.TOP)};
        Stacker.variants[0] = new Stacker(2, 1, acc, eje);
    }


    private static int i = 0;
    private Stacker(int w, int h, Acceptor[] acceptors, Ejector[] ejectors) {
        super(w, h, acceptors, ejectors);
        super.id = "S" + i++;
    }

    @Override
    public Item[] process(Item[] items) {
        return new Shape[]{((Shape) items[0]).stackWith((Shape) items[1])};
    }

}
