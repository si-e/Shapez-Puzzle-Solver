package game.buildings;


import game.components.Acceptor;
import game.components.Ejector;
import game.items.Item;
import util.Direction;

public class Belt extends Building {

    protected static Belt[] variants;

    static {
        Belt.variants = new Belt[3];
        Acceptor[] acc = {new Acceptor(0, 0, Direction.BOTTOM, Item.class)};
        {
            Ejector[] eje = {new Ejector(0, 0, Direction.TOP)};
            Belt.variants[0] = new Belt(1, 1, acc, eje);
        }
        {
            Ejector[] eje = {new Ejector(0, 0, Direction.LEFT)};
            Belt.variants[1] = new Belt(1, 1, acc, eje);
        }
        {
            Ejector[] eje = {new Ejector(0, 0, Direction.RIGHT)};
            Belt.variants[2] = new Belt(1, 1, acc, eje);
        }
    }

    private static int i = 0;

    private Belt(int w, int h, Acceptor[] acceptors, Ejector[] ejectors) {
        super(w, h, acceptors, ejectors);
        super.id = "B" + i++;
    }

    @Override
    public Item[] process(Item[] items) {
        return items;
    }

}
