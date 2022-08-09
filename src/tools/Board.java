package tools;

import game.Entity;
import game.buildings.Block;
import game.buildings.Building;
import game.buildings.ConstantProducer;
import game.buildings.GoalAcceptor;
import game.components.Acceptor;
import game.components.Ejector;
import util.Position;
import util.Slot;

import java.util.*;

public class Board {

    private boolean[][] grid;
    private Stack<Entity> entityStack;
    private Map<Slot, Ejector> ejectorMap;
    private Map<Acceptor, Ejector> historyMap;
    private List<String> goalList;
    private boolean achieve;


    public Board(int width, int height) {
        grid = new boolean[width][height];
        entityStack = new Stack<>();
        ejectorMap = new HashMap<>();
        historyMap = new HashMap<>();
        goalList = new ArrayList<>();
        achieve = false;
    }

    public void addConstantProducer(ConstantProducer p) {
        Ejector e = p.getEjector();
        ejectorMap.put(e.getSlot(), e);
        grid[p.x][p.y] = true;
    }

    public void addGoalAcceptor(GoalAcceptor a) {
        // No check position
        goalList.add(a.shortcode);
        grid[a.x][a.y] = true;
    }

    public void addBlock(Block b) {
        grid[b.x][b.y] = true;
    }

    public boolean tryPlaceEntity(Building building, Position position) {
        // new entity
        Entity entity = building.createEntity(position);
        // bound no overlap
        for (int[] xy : entity.getBound()) {
            if (xy[0] < 0 ||
                    xy[0] >= this.grid.length ||
                    xy[1] < 0 ||
                    xy[1] >= this.grid[0].length ||
                    this.grid[xy[0]][xy[1]]) {
                return false;
            }
        }
        // fit all acceptors
        for (Acceptor a : entity.getAcceptors()) {
            Ejector e = ejectorMap.get(a.getSlot().oppositeSlot());
            if (e != null && a.fit(e)) {
                a.setItem(e.getItem());
            } else {
                return false;
            }
        }

        // add entity
        entity.process();
        this.entityStack.push(entity);
        // spawn on grid
        for (int[] xy : entity.getBound()) {
            this.grid[xy[0]][xy[1]] = true;
        }
        // del ejectors
        for (Acceptor a : entity.getAcceptors()) {
            Ejector e = ejectorMap.remove(a.getSlot().oppositeSlot());
            historyMap.put(a, e);
        }
        // assign new ejectors
        for (Ejector e : entity.getEjectors()) {
            ejectorMap.put(e.getSlot(), e);
            if (goalList.contains(e.getItem().toString())) {
                achieve = true;
            }
        }
        return true;
    }

    public boolean achieve() {
        boolean r = achieve;
        achieve = false;
        return r;
    }

    public Position[] getPositions() {
        return ejectorMap.keySet().stream().map(Slot::nextPosition).toArray(Position[]::new);
    }

    public Entity[] getEntities() {
        return entityStack.toArray(Entity[]::new);
    }

    public void removeLastEntity() {
        Entity entity = this.entityStack.pop();
        for (int[] xy : entity.getBound()) {
            this.grid[xy[0]][xy[1]] = false;
        }
        for (Acceptor a : entity.getAcceptors()) {
            Ejector e = historyMap.remove(a);
            ejectorMap.put(a.getSlot().oppositeSlot(), e);
        }
        for (Ejector e : entity.getEjectors()) {
            ejectorMap.remove(e.getSlot());
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
//        for (int y = grid[0].length - 1; y >= 0; y--) {
//            for (boolean[] booleans : grid) {
//                s.append((booleans[y]) ? '■' : '□').append(' ');
//            }
//            s.append('\n');
//        }
        String[][] g = new String[grid.length][grid[0].length];
        for (Entity entity : entityStack) {
            String name = entity.toString();
            for (int[] xy : entity.getBound()) {
                g[xy[0]][xy[1]] = name;
            }
        }
        for (int y = g[0].length - 1; y >= 0; y--) {
            for (String[] ns : g) {
                s.append('|').append((ns[y] != null) ? ns[y] : "   ");
            }
            s.append("|\n");
        }
        return s.toString();
    }
}
