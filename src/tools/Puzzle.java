package tools;

import game.buildings.Block;
import game.buildings.Building;
import game.buildings.ConstantProducer;
import game.buildings.GoalAcceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle {


    private int width, height;

    private Building[] availableBuildings;

    private List<ConstantProducer> constantProducerList;
    private List<GoalAcceptor> goalAcceptorList;
    private List<Block> blockList;


    public Puzzle(int width, int height,
                  Building[][] availableBuildings) {
        this.width = width;
        this.height = height;
        this.availableBuildings =
                Arrays.stream(availableBuildings).flatMap(Arrays::stream).toArray(Building[]::new);

        this.constantProducerList = new ArrayList<>();
        this.goalAcceptorList = new ArrayList<>();
        this.blockList = new ArrayList<>();
    }

    public void addConstantProducer(int x, int y, int rotation, String shortcode) {
        constantProducerList.add(new ConstantProducer(x, y, rotation, shortcode));
    }

    public void addGoalAcceptor(int x, int y, int rotation, String shortcode) {
        goalAcceptorList.add(new GoalAcceptor(x, y, rotation, shortcode));
    }

    public void addBlock(int x, int y) {
        blockList.add(new Block(x, y));
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Building[] getAvailableBuildings() {
        return availableBuildings.clone();
    }

    public ConstantProducer[] getConstantProducers() {
        return constantProducerList.toArray(ConstantProducer[]::new);
    }

    public GoalAcceptor[] getGoalAcceptors() {
        return goalAcceptorList.toArray(GoalAcceptor[]::new);
    }

    public Block[] getBlocks() {
        return blockList.toArray(Block[]::new);
    }
}
