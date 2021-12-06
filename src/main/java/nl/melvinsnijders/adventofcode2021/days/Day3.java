package nl.melvinsnijders.adventofcode2021.days;

import lombok.Getter;
import nl.melvinsnijders.adventofcode2021.AdventInputReader;
import nl.melvinsnijders.adventofcode2021.IAdventDay;

import java.util.ArrayList;
import java.util.List;

/**
 * A disclaimer:
 * I'm absolutely NOT proud of this code (it's awful), but I wanted to just finish the day and move on.
 */

public class Day3 implements IAdventDay {

    private final List<String> input;
    @Getter private final int day = 3;

    public Day3() {
        AdventInputReader<String> adventInputReader = new AdventInputReader<>(this.day);
        this.input = adventInputReader.read(String::toString);
    }

    @Override
    public int part1() {

        StringBuilder gammaString = new StringBuilder();
        StringBuilder epsilonString = new StringBuilder();

        int diagnosticLength = this.input.get(0).length();

        for(int i = 0; i < diagnosticLength; i++) {

            boolean mostCommon = this.mostCommon(input, i);

            gammaString.append(mostCommon ? 1 : 0);
            epsilonString.append(!mostCommon ? 1 : 0);

        }

        int gamma = Integer.parseInt(gammaString.toString(), 2);
        int epsilon = Integer.parseInt(epsilonString.toString(), 2);

        return gamma * epsilon;

    }

    @Override
    public int part2() {

        List<String> oxygenList = new ArrayList<>(this.input);
        List<String> co2List = new ArrayList<>(this.input);

        int index = 0;

        while(oxygenList.size() > 1) {

            Boolean mostCommon = this.mostCommon(oxygenList, index);
            int finalIndex = index;
            if(mostCommon != null) {
                oxygenList.removeIf(i -> i.charAt(finalIndex) == (mostCommon ? '0' : '1'));
            } else {
                oxygenList.removeIf(i -> i.charAt(finalIndex) == '0');
            }

            if(index + 1 < oxygenList.get(0).length()) {
                index++;
            }

            System.out.println(oxygenList);

        }

        index = 0;

        while(co2List.size() > 1) {

            Boolean mostCommon = this.mostCommon(co2List, index);
            int finalIndex = index;
            if(mostCommon != null) {
                co2List.removeIf(i -> i.charAt(finalIndex) == (!mostCommon ? '0' : '1'));
            } else {
                co2List.removeIf(i -> i.charAt(finalIndex) == '1');
            }

            if(index + 1 < co2List.get(0).length()) {
                index++;
            }

        }

        int oxygen = Integer.parseInt(oxygenList.get(0), 2);
        int co2 = Integer.parseInt(co2List.get(0), 2);

        return oxygen * co2;

    }

    private Boolean mostCommon(List<String> input, int index) {

        int trueCount = 0;
        int falseCount = 0;

        for (String line : input) {
            if (line.charAt(index) == '1') trueCount++;
            else falseCount++;
        }

        if(trueCount == falseCount) {
            return null;
        }

        return trueCount > falseCount;

    }


}
