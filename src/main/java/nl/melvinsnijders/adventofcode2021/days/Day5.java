package nl.melvinsnijders.adventofcode2021.days;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.melvinsnijders.adventofcode2021.AdventInputReader;
import nl.melvinsnijders.adventofcode2021.IAdventDay;

import java.util.Arrays;
import java.util.List;

public class Day5 implements IAdventDay {

    private final List<Line> input;
    @Getter
    private final int day = 5;

    private static final int ARRAY_SIZE = 1000;

    public Day5() {
        AdventInputReader<Line> adventInputReader = new AdventInputReader<>(this.day);
        this.input = adventInputReader.read(line -> {
            String[] segments = line.split(" -> ");
            String[] start = segments[0].split(",");
            String[] end = segments[1].split(",");

            int startX = Integer.parseInt(start[0]);
            int startY = Integer.parseInt(start[1]);
            int endX = Integer.parseInt(end[0]);
            int endY = Integer.parseInt(end[1]);

            return new Line(startX, startY, endX, endY);
        });
    }

    @Override
    public int part1() {

        int[][] grid = new int[ARRAY_SIZE][ARRAY_SIZE];
        this.drawStraightLines(grid);

        return this.calculateOverlaps(grid);

    }

    @Override
    public int part2() {

        int[][] grid = new int[ARRAY_SIZE][ARRAY_SIZE];
        this.drawStraightLines(grid);
        this.drawDiagonalLines(grid);

        return this.calculateOverlaps(grid);

    }

    private void drawDiagonalLines(int[][] grid) {

        for (Line line : this.input) {

            int startX = line.getStartX();
            int startY = line.getStartY();
            int endX = line.getEndX();
            int endY = line.getEndY();

            int deltaX = Math.abs(startX - endX);
            int deltaY = Math.abs(startY - endY);

            int x = startX;
            int y = startY;

            if (deltaX == deltaY) {

                if (startX < endX && startY < endY) {

                    while (x <= endX && y <= endY) {
                        grid[y][x] += 1;
                        x++;
                        y++;
                    }

                } else if (startX > endX && startY > endY) {

                    while (x >= endX && y >= endY) {
                        grid[y][x] += 1;
                        x--;
                        y--;
                    }

                } else if (startX < endX && startY > endY) {

                    while (x <= endX && y >= endY) {
                        grid[y][x] += 1;
                        x++;
                        y--;
                    }

                } else if (startX > endX && startY < endY) {

                    while (x >= endX && y <= endY) {
                        grid[y][x] += 1;
                        x--;
                        y++;
                    }

                }

            }

        }


    }


    private void drawStraightLines(int[][] grid) {

        for (Line line : this.input) {

            if (line.getStartY() == line.getEndY()) {

                if (line.getStartX() < line.getEndX()) {

                    for (int i = line.getStartX(); i <= line.getEndX(); i++) {
                        grid[line.getStartY()][i] += 1;
                    }

                } else {

                    for (int i = line.getStartX(); i >= line.getEndX(); i--) {
                        grid[line.getStartY()][i] += 1;
                    }

                }

            }

            if (line.getStartX() == line.getEndX()) {

                if (line.getStartY() < line.getEndY()) {

                    for (int i = line.getStartY(); i <= line.getEndY(); i++) {
                        grid[i][line.getStartX()] += 1;
                    }

                } else {

                    for (int i = line.getStartY(); i >= line.getEndY(); i--) {
                        grid[i][line.getStartX()] += 1;
                    }

                }

            }

        }

    }

    private int calculateOverlaps(int[][] grid) {

        int count = 0;

        for (int[] row : grid) {
            for (int col : row) {
                if (col > 1) {
                    count++;
                }
            }
        }

        return count;

    }

    @AllArgsConstructor
    @Getter
    private class Line {
        private int startX;
        private int startY;
        private int endX;
        private int endY;
    }

}
