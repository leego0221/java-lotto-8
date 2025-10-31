package lotto.parser;

import lotto.exception.ErrorCode;

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
                .map(String::strip)
                .peek(InputParser::validateNotBlank)
                .peek(InputParser::validateInteger)
                .map(Integer::parseInt)
                .toList();
    }

    private static void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorCode.ERROR_PARSED_BLANK.getMessage());
        }
    }

    private static void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorCode.ERROR_PARSED_NOT_INTEGER.getMessage());
        }
    }
}
