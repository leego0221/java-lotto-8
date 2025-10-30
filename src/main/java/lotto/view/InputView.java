package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBERS = "당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    public String readPurchaseAmount() {
        System.out.println(INPUT_PURCHASE_AMOUNT);
        String input = Console.readLine();

        validateNotBlank(input);
        validateInteger(input);
        return input;
    }

    public String readWinningNumbers() {
        printNewLine();
        System.out.println(INPUT_WINNING_NUMBERS);
        String input = Console.readLine();

        validateNotBlank(input);
        return input;
    }

    public String readBonusNumber() {
        printNewLine();
        System.out.println(INPUT_BONUS_NUMBER);
        String input = Console.readLine();

        validateNotBlank(input);
        validateInteger(input);
        return input;
    }

    public void close() {
        Console.close();
    }

    private void printNewLine() {
        System.out.println();
    }

    private void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력값이 비어있습니다.");
        }
    }

    private void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 입력값이 Integer 타입이 아닙니다.");
        }
    }
}
