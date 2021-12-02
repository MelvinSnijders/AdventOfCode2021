package nl.melvinsnijders.adventofcode2021;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AdventInputReader {

    private final String fileName;

    public AdventInputReader(int day) {
        this.fileName = "day_" + day + ".txt";
    }

    public List<Integer> readNumbers() {

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(this.fileName);
        Scanner scanner = new Scanner(inputStream);

        List<Integer> lineList = new LinkedList<>();

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lineList.add(Integer.parseInt(line));
        }

        return lineList;

    }


}
