package game.buildings;

import game.Entity;
import game.components.Acceptor;
import game.components.Ejector;
import game.items.Item;
import util.Computable;
import util.Position;
import util.Slot;
import util.Util;

import java.util.Arrays;
import java.util.function.Function;

public abstract class Building {

    protected int dx, dy;

    // temp related acc and eje
    protected Acceptor[] acceptors;
    protected Ejector[] ejectors;

    protected Building(int width, int height, Acceptor[] acceptors, Ejector[] ejectors) {
        this.dx = width;
        this.dy = height;
        this.acceptors = acceptors;
        this.ejectors = ejectors;
    }

    public abstract Item[] process(Item[] items);

    public Entity createEntity(Position position) {
        Function<Computable, Computable> f = Util.calTransform(Position.ORIGIN, position);

        Acceptor[] acceptors1 = Arrays.stream(this.acceptors)
                .map(acceptor -> new Acceptor((Slot) f.apply(acceptor.getSlot()), acceptor.getFilter()))
                .toArray(Acceptor[]::new);
        Ejector[] ejectors1 = Arrays.stream(this.ejectors)
                .map(ejector -> new Ejector((Slot) f.apply(ejector.getSlot())))
                .toArray(Ejector[]::new);

        return new Entity(this, position, acceptors1, ejectors1);
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

}
