package game;

import game.buildings.Building;
import game.components.Acceptor;
import game.components.Ejector;
import game.items.Item;
import util.Bound;
import util.Position;

public class Entity {
    private final Building building;
    private final Bound bound;
    private final Acceptor[] acceptors;
    private final Ejector[] ejectors;
    private final String s;

    public Entity(Building building, Position position, Acceptor[] acceptors, Ejector[] ejectors) {
        int dx = building.getDx();
        int dy = building.getDy();
        Bound bound;
        switch (position.rotation) {
            case 0:
                bound = new Bound(position.x, position.y, dx, dy);
                break;
            case 90:
                bound = new Bound(position.x, position.y - dx + 1, dy, dx);
                break;
            case 180:
                bound = new Bound(position.x - dx + 1, position.y - dy + 1, dx, dy);
                break;
            case 270:
                bound = new Bound(position.x - dy + 1, position.y, dy, dx);
                break;
            default:
                bound = null;
        }
        this.building = building;
        this.bound = bound;
        this.acceptors = acceptors;
        this.ejectors = ejectors;
        this.s = building + ejectors[0].getSlot().getDirection().toString();
    }

    public Acceptor[] getAcceptors() {
        return acceptors;
    }

    public Ejector[] getEjectors() {
        return ejectors;
    }

    public Bound getBound() {
        return bound;
    }

    public Building getBuilding() {
        return building;
    }

    public void process() {
        Item[] inputs = new Item[acceptors.length];
        for (int i = 0; i < acceptors.length; i++) {
            inputs[i] = acceptors[i].getItem();
        }
        Item[] outputs = building.process(inputs);
        for (int i = 0; i < ejectors.length; i++) {
            ejectors[i].setItem(outputs[i]);
        }
    }

    @Override
    public String toString() {
        return s;
    }
}
