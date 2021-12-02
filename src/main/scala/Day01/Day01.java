package Day01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Day01J {
    public static void main(String[] args) throws FileNotFoundException {

        final List<Integer> values = new ArrayList<Integer>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/scala/Day01/input"));) {
            values.addAll(
                    br.lines()
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Part1(values));
        System.out.println(Part2(values));
    }

    private static int Part1(final List<Integer> xs) {
        int sum = 0;
        for (int i = 0; i < xs.size() - 2; i++) {
            if (xs.get(i) < xs.get(i + 1)) {
                sum++;
            }
        }
        return sum;
    }

    private static int Part2(List<Integer> xs) {
        int sum = 0;
        for (int i = 0; i < xs.size() - 3; i++) {
            int sum1 = xs.subList(i, i+2 +1).stream().reduce(Integer::sum).orElse(0);
            int sum2 = xs.subList(i+1, i+3 +1).stream().reduce(Integer::sum).orElse(0);
            if (sum1 < sum2) {
                sum++;
            }
        }
        return sum;
    }
}
