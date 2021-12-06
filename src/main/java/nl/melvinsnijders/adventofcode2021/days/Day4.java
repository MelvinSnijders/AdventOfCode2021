package nl.melvinsnijders.adventofcode2021.days;

import lombok.Getter;
import nl.melvinsnijders.adventofcode2021.AdventInputReader;
import nl.melvinsnijders.adventofcode2021.IAdventDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 implements IAdventDay {

    private final String input;
    @Getter
    private final int day = 4;

    private List<Integer> numbers;
    private List<Integer> drawnNumbers = new ArrayList<>();

    private List<int[][]> bingoCards = new ArrayList<>();

    public Day4() {
        AdventInputReader<String> adventInputReader = new AdventInputReader<>(this.day);
        this.input = adventInputReader.readAsString();
        this.parseBingoCards();
    }

    /**
     * Parse the order of which the bingo numbers should be drawn.
     */

    private void parseDraw() {

        // Get the first line, so split on double enter.
        String line = this.input.split("\\r\\n\\r\\n")[0];
        String[] numbers = line.split(",");

        // Set variables.
        this.numbers = Arrays.stream(numbers).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        this.drawnNumbers = new ArrayList<>();

    }

    /**
     * Parse the bingo cards as a 2d int array.
     */

    private void parseBingoCards() {

        // Split on double newline and remove first element (which is the order, not a card)
        String[] rawCards = this.input.split("\\r\\n\\r\\n");
        rawCards = Arrays.copyOfRange(rawCards, 1, rawCards.length);

        for (String rawCard : rawCards) {

            String[] lines = rawCard.split("\\r\\n");

            int[][] card = new int[5][5];

            // Loop over each line of the bingo card.
            for (int i = 0; i < lines.length; i++) {
                // Split on space (or multiple spaces)
                String[] numbers = lines[i].trim().split("\\s+");
                for (int x = 0; x < numbers.length; x++) {
                    card[i][x] = Integer.parseInt(numbers[x]);
                }
            }

            bingoCards.add(card);

        }

    }

    @Override
    public int part1() {

        this.parseDraw();

        int[][] winnerCard = null;

        // While there is no winner
        while (winnerCard == null) {

            // Loop over all cards and check if they have a bingo.
            for (int[][] card : this.bingoCards) {

                if (this.checkHorizontal(card) || this.checkVertical(card)) {
                    winnerCard = card;
                    break;
                }

            }

            // As long as there is no winner, we can draw a number.

            if (winnerCard == null) {

                this.drawnNumbers.add(this.numbers.get(0));
                this.numbers.remove(0);

            }

        }

        // Get the sum and latest drawn number.
        int sum = this.unmarkedSum(winnerCard);
        int latestDrawn = this.drawnNumbers.get(this.drawnNumbers.size() - 1);

        return sum * latestDrawn;

    }

    @Override
    public int part2() {

        this.parseDraw();

        List<int[][]> cards = new ArrayList<>(this.bingoCards);

        // While there are still cards left.
        while (cards.size() > 1) {

            cards.removeIf(card -> this.checkHorizontal(card) || this.checkVertical(card));

            this.drawnNumbers.add(this.numbers.get(0));
            this.numbers.remove(0);

        }

        // The final (worse) card is the first in the list since the rest of them are removed.
        int[][] finalCard = cards.get(0);

        int sum = this.unmarkedSum(finalCard);
        int latestDrawn = this.drawnNumbers.get(this.drawnNumbers.size() - 1);

        return sum * latestDrawn;

    }

    /**
     * Check if the card has a horizontal bingo.
     * @param card The card to check.
     * @return True if the card has a horizontal bingo.
     */

    public boolean checkHorizontal(int[][] card) {

        for (int[] ints : card) {
            int count = 0;
            for (int x = 0; x < card.length; x++) {
                int toCheck = ints[x];
                if (this.drawnNumbers.contains(toCheck)) {
                    count++;
                }
            }
            if (count == card.length) {
                return true;
            }
        }

        return false;

    }

    /**
     * Check if the card has a vertical bingo.
     * @param card The card to check.
     * @return True if the card has a vertical bingo.
     */

    public boolean checkVertical(int[][] card) {

        for (int i = 0; i < card.length; i++) {
            int count = 0;
            for (int[] ints : card) {
                int toCheck = ints[i];
                if (this.drawnNumbers.contains(toCheck)) {
                    count++;
                }
            }
            if (count == card.length) {
                return true;
            }
        }

        return false;

    }

    /**
     * Get the sum of all unmarked numbers on the card.
     * @param card The card to check.
     * @return The sum of all unmarked numbers on the card.
     */

    public int unmarkedSum(int[][] card) {

        int sum = 0;

        for (int[] ints : card) {
            for (int y = 0; y < card.length; y++) {
                if (!this.drawnNumbers.contains(ints[y])) {
                    sum += ints[y];
                }
            }
        }

        return sum;

    }


}
