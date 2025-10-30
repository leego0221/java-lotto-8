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
                    validateNotBlank(token);
                    validateInteger(token);
                })
                .map(Integer::parseInt)
                .toList();
    }

    private static void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 파싱값이 비어있습니다.");
        }
    }

    private static void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 파싱값이 Integer 타입이 아닙니다.");
        }
    }
}
