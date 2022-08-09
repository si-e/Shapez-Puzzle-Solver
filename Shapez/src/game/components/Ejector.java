package game.components;

import game.items.Item;
import util.Direction;
import util.Slot;

public class Ejector {

    private final Slot slot;

    private Item item;

    public Ejector(Slot slot) {
        this.slot = slot;
    }

    public Ejector(int x, int y, Direction direction) {
        this(new Slot(x, y, direction));
    }

    public Slot getSlot() {
        return slot;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
