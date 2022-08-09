package game.components;

import game.items.Item;
import util.Direction;
import util.Slot;

public class Acceptor {

    private final Slot slot;
    private final Class<? extends Item> filter;

    private Item item;

    public Acceptor(Slot slot, Class<? extends Item> filter) {
        this.slot = slot;
        this.filter = filter;
    }

    public Acceptor(int x, int y, Direction direction, Class<? extends Item> filter) {
        this(new Slot(x, y, direction), filter);
    }

    public Slot getSlot() {
        return slot;
    }

    public Class<? extends Item> getFilter() {
        return filter;
    }

    public boolean fit(Ejector ejector) {
        return ejector != null &&
                ejector.getSlot().oppositeSlot().equals(this.slot) &&
                this.filter.isInstance(ejector.getItem());
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
