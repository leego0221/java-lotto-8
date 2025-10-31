package lotto.domain;

import lotto.exception.ErrorCode;

import java.util.List;

public class WinningNumbers {

    private final List<Integer> winningNumbers;

    public WinningNumbers(List<Integer> winningNumbers) {
        validate(winningNumbers);
        this.winningNumbers = winningNumbers;
    }

    public List<Integer> getWinningNumbers() {
        return List.copyOf(winningNumbers);
    }

    private void validate(List<Integer> winningNumbers) {
        validateSize(winningNumbers);
        validateNoDuplicates(winningNumbers);
        winningNumbers.forEach(this::validateRange);
    }

    private void validateSize(List<Integer> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException(ErrorCode.ERROR_NUMBERS_INVALID_SIZE.getMessage());
        }
    }

    private void validateNoDuplicates(List<Integer> winningNumbers) {
        if (winningNumbers.size() != winningNumbers.stream().distinct().count()) {
            throw new IllegalArgumentException(ErrorCode.ERROR_NUMBERS_DUPLICATE.getMessage());
        }
    }

    private void validateRange(int winningNumber) {
        if (winningNumber < 1 || winningNumber > 45) {
            throw new IllegalArgumentException(ErrorCode.ERROR_NUMBERS_INVALID_RANGE.getMessage());
        }
    }
}
