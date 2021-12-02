package nl.melvinsnijders.adventofcode2021;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class AdventInputReader<T> {

    private final String fileName;

    public AdventInputReader(int day) {
        this.fileName = "day_" + day + ".txt";
    }

    public List<T> read(Function<String, T> parse) {

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(this.fileName);
        Scanner scanner = new Scanner(inputStream);

        List<T> list = new LinkedList<>();

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            T parsed = parse.apply(line);
            list.add(parsed);
        }

        return list;

    }


}
