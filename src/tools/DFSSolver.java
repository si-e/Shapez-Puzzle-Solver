package tools;

import game.buildings.Block;
import game.buildings.Building;
import game.buildings.ConstantProducer;
import game.buildings.GoalAcceptor;
import util.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Depth First Search
public class DFSSolver {

    private List<Solution> solutionList;
    private Set<String> historySet;
    private Building[] allBuildings;
    private Board board;
    private long time;

    private Board createBoard(Puzzle puzzle) {
        Board board = new Board(puzzle.getWidth(), puzzle.getHeight());

        for (ConstantProducer p : puzzle.getConstantProducers()) {
            board.addConstantProducer(p);
        }
        for (GoalAcceptor a : puzzle.getGoalAcceptors()) {
            board.addGoalAcceptor(a);
        }
        for (Block b : puzzle.getBlocks()) {
            board.addBlock(b);
        }
        return board;
    }

    public List<Solution> solve(Puzzle puzzle) {
        time = System.currentTimeMillis();
        allBuildings = puzzle.getAvailableBuildings();
        board = createBoard(puzzle);

        historySet = new HashSet<>();
        solutionList = new ArrayList<>();
        dfs();
        time = System.currentTimeMillis() - time;
        return solutionList;
    }

    public long getTime() {
        return time;
    }

    int i = 0;


    private void dfs() {
        for (Position port : board.getPositions()) {
            for (Building building : allBuildings) {
                boolean success = board.tryPlaceEntity(building, port);
                if (success) {
                    System.out.print("\r" + i++);
                    if (board.achieve()) {
                        System.out.println();
                        System.out.println(board.toString());
                        System.out.println();
                        solutionList.add(new Solution(board.toString()));
                    } else {
                        if (historySet.add(board.toString())) {
                            dfs();
                        }
                    }
                    board.removeLastEntity();
                }
            }
        }
    }
}
