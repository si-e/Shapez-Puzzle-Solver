package game.buildings;

import game.components.Ejector;
import game.items.Color;
import game.items.Item;
import game.items.Shape;
import util.Direction;

public class ConstantProducer {
    public int x, y, rotation;
    public String signal;

    private Ejector ejector;

    public ConstantProducer(int x, int y, int rotation, String shortcode) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.signal = shortcode;
        this.ejector = new Ejector(x, y, Direction.fromRotation(rotation));
        this.ejector.setItem(Item.fromShortCode(shortcode));
    }

    public Ejector getEjector() {
        return ejector;
    }

}
