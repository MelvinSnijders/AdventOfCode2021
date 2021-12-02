package nl.melvinsnijders.adventofcode2021.day1;

import lombok.Getter;
import nl.melvinsnijders.adventofcode2021.IAdventDay;
import nl.melvinsnijders.adventofcode2021.AdventInputReader;

import java.util.List;

public class Day1 implements IAdventDay {

    private final List<Integer> input;
    @Getter private final int day = 1;

    public Day1() {
        AdventInputReader<Integer> adventInputReader = new AdventInputReader<>(this.day);
        this.input = adventInputReader.read(Integer::parseInt);
    }

    @Override
    public int part1() {

        int increasedCount = 0;
        int lastDepth = this.input.get(0);

        for(int depth : this.input) {

            if(depth > lastDepth) {
                increasedCount++;
            }

            lastDepth = depth;

        }

        return increasedCount;

    }

    @Override
    public int part2() {

        int slidingWindowIncreased = 0;
        int lastWindow = Integer.MIN_VALUE;

        for(int i = 0; i < this.input.size() - 2; i++) {

            int slidingWindow = this.input.get(i) + this.input.get(i + 1) + this.input.get(i + 2);

            if(slidingWindow > lastWindow) {
                slidingWindowIncreased++;
            }

            lastWindow = slidingWindow;

        }

        // Deduct one, because the first increase is always true.
        return slidingWindowIncreased - 1;

    }

}
