package nl.melvinsnijders.adventofcode2021.days;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.melvinsnijders.adventofcode2021.AdventInputReader;
import nl.melvinsnijders.adventofcode2021.IAdventDay;

import java.util.List;

public class Day2 implements IAdventDay {

    private final List<Movement> input;
    @Getter
    private final int day = 2;

    public Day2() {

        AdventInputReader<Movement> adventInputReader = new AdventInputReader<>(this.day);

        this.input = adventInputReader.read(l -> {
            String[] split = l.split(" ");
            Direction direction = Direction.valueOf(split[0].toUpperCase());
            int amount = Integer.parseInt(split[1]);
            return new Movement(direction, amount);
        });

    }

    @Override
    public int part1() {

        int horizontalPosition = 0;
        int depth = 0;

        for (Movement movement : this.input) {

            switch (movement.getDirection()) {
                case UP -> depth -= movement.getAmount();
                case DOWN -> depth += movement.getAmount();
                case FORWARD -> horizontalPosition += movement.getAmount();
            }

        }

        return horizontalPosition * depth;

    }

    @Override
    public int part2() {

        int horizontalPosition = 0;
        int depth = 0;
        int aim = 0;

        for (Movement movement : this.input) {

            switch (movement.getDirection()) {
                case UP -> aim -= movement.getAmount();
                case DOWN -> aim += movement.getAmount();
                case FORWARD -> {
                    horizontalPosition += movement.getAmount();
                    depth += aim * movement.getAmount();
                }
            }

        }

        return horizontalPosition * depth;

    }

    @Getter
    @AllArgsConstructor
    class Movement {
        private Direction direction;
        private int amount;
    }

    public enum Direction {
        FORWARD,
        DOWN,
        UP
    }

}
