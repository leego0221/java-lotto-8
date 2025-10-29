package lotto.parser;

import java.util.Arrays;
import java.util.List;

public class WinningNumberParser {

    public static List<Integer> parse(String input) {
        String[] output = input.split(",", -1);

        return Arrays.stream(output)
                .map(Integer::parseInt)
                .toList();
    }
}
