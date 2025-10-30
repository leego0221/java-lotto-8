package lotto.parser;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    private static final String DELIMITER = ",";
    private static final int PARSE_ALL = -1;

    public static int parseInteger(String input) {
        return Integer.parseInt(input);
    }

    public static List<Integer> parseWinningNumbers(String input) {
        String[] output = input.split(DELIMITER, PARSE_ALL);
        return Arrays.stream(output)
                .peek(token -> {
                    validateIsBlank(token);
                    validateIsInteger(token);
                })
                .map(Integer::parseInt)
                .toList();
    }

    private static void validateIsBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력값이 비어있습니다.");
        }
    }

    private static void validateIsInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 입력값이 Integer 타입이 아닙니다.");
        }
    }
}
