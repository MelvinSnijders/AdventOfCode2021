package nl.melvinsnijders.adventofcode2021;

import nl.melvinsnijders.adventofcode2021.days.Day1;
import nl.melvinsnijders.adventofcode2021.days.Day2;

public class Main {

    public static void main(String[] args) {

        runDay(new Day1());
        runDay(new Day2());

    }

    private static void runDay(IAdventDay adventDay) {
        System.out.println(" ");
        System.out.println("Day " + adventDay.getDay() + ", Part 1: " + adventDay.part1());
        System.out.println("Day " + adventDay.getDay() + ", Part 2: " + adventDay.part2());
    }

}
