package Day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day07j {

    public static void main(String[] args) {
        var values = (ArrayList<Integer>) getInput();
        Collections.sort(values);

        System.out.println(Part1(values));
        System.out.println(Part2(values));
    }

    private static int Part1(List<Integer> crabPositions) {
        final var median = crabPositions.get(crabPositions.size() / 2);
        return crabPositions.stream().mapToInt(crab -> Math.abs(crab - median)).sum();
    }

    private static int Part2(ArrayList<Integer> crabPositions) {
        final double average = (int) Math.round(
                crabPositions.stream()
                .mapToInt(i -> i)
                .average()
                .orElse(0.0)
        );

        return crabPositions.stream().mapToInt(crab -> {
            var dist = (int) Math.abs(crab -average);
            return dist * (dist+1) / 2;
        }).sum();
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
}
