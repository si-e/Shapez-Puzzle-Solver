import game.buildings.Building;
import game.buildings.Buildings;
import tools.DFSSolver;
import tools.Puzzle;
import tools.Solution;

import java.util.List;

public class Test {

    static Puzzle[] puzzles = new Puzzle[10];

    static {
        Puzzle puzzle;
        // belt test
        {
            int width = 3, height = 2;
            Building[][] buildings = {Buildings.BELTS};

            puzzle = new Puzzle(width, height, buildings);
            puzzle.addConstantProducer(0, 0, 90, "----CrCr:CrCrCgCg:--CgCg--");
            puzzle.addGoalAcceptor(2, 1, 270, "----CrCr:CrCrCgCg:--CgCg--");
        }
        puzzles[0] = puzzle;
        // rotater test
        {
            int width = 5, height = 1;
            Building[][] buildings = {Buildings.ROTATERS};

            puzzle = new Puzzle(width, height, buildings);
            puzzle.addConstantProducer(0, 0, 90, "----CrCr:--CgCg--");
            puzzle.addGoalAcceptor(4, 0, 270, "CrCr----:Cg----Cg");
        }
        puzzles[1] = puzzle;
        // cutter test
        {
            int width = 4, height = 2;
            Building[][] buildings = {Buildings.CUTTERS, Buildings.BELTS};

            puzzle = new Puzzle(width, height, buildings);
            puzzle.addConstantProducer(0, 0, 90, "------Cr:Cg------");
            puzzle.addGoalAcceptor(3, 1, 270, "------Cr");
        }
        puzzles[2] = puzzle;
        // stack test
        {
            int width = 5, height = 3;
            Building[][] buildings = {Buildings.STACKERS, Buildings.CUTTERS, Buildings.BELTS};

            puzzle = new Puzzle(width, height, buildings);
            puzzle.addConstantProducer(0, 0, 90, "----Wy--:----Sb--:--Rr----");
            puzzle.addConstantProducer(0, 1, 90, "CgWu----");
            puzzle.addConstantProducer(0, 2, 90, "------Cr:Cg------");
            puzzle.addGoalAcceptor(4, 0, 0, "------Cr:Cg--Wy--:CgWuSb--:--Rr----");
        }
        puzzles[3] = puzzle;
        // test0
        {
            int width = 7, height = 4;
            Building[][] buildings = {Buildings.BELTS, Buildings.CUTTERS, Buildings.STACKERS, Buildings.ROTATERS};

            puzzle = new Puzzle(width, height, buildings);
            puzzle.addConstantProducer(0, 3, 90, "CrCrCrCr");
            puzzle.addConstantProducer(0, 1, 90, "CgCgCgCg");
            puzzle.addGoalAcceptor(6, 3, 270, "----CrCr:CrCrCgCg:--CgCg--");
        }
        puzzles[9] = puzzle;
    }

    public static void main(String[] args) {
        Puzzle puzzle = puzzles[3];

        DFSSolver solver = new DFSSolver();
        List<Solution> solutionList = solver.solve(puzzle);

        System.out.println();
        solutionList.stream().map(Solution::toString).distinct().forEach(System.out::println);
        System.out.println(solver.getTime() / 1000 + "s");
    }



}
