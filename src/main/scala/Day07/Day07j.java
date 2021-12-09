package Day07;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day07j {

    public static void main(String[] args) {
        var values = (ArrayList<Integer>) getInput();
        Collections.sort(values);

        System.out.println(Part1(values) + " (correct: 356958)");
        System.out.println(Part2(values) + " (correct: 105461913)");
    }

    private static int Part1(List<Integer> crabPositions) {
        final var median = crabPositions.get(crabPositions.size() / 2);
        return crabPositions.stream().mapToInt(crab -> Math.abs(crab - median)).sum();
    }

    private static int Part2(ArrayList<Integer> crabPositions) {
        int minPos = Collections.min(crabPositions);
        int maxPos = Collections.max(crabPositions);

        return IntStream.range(minPos, maxPos + 1)
                .parallel()
                .map(pos -> cost2(pos, crabPositions)
                ).min()
                .orElse(0);
    }

    private static List<Integer> getInput() {
        try {
            final List<String> numbersAsStrings = Files.readAllLines(Path.of("src/main/scala/Day07/input"));
            return numbersAsStrings.stream()
                    .map(line -> Arrays.stream(line.split(",")).collect(Collectors.toList()))
                    .flatMap(Collection::stream)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("File not found! " + e.getMessage());
        }
        return new ArrayList<>();
    }


    private static int cost2(int pos, ArrayList<Integer> crabs) {
        int fuel = 0;
        for (int crab : crabs) {
            int moves = Math.abs(pos - crab);
            fuel += (moves * (moves + 1)) / 2;
        }
        return fuel;
    }
}
