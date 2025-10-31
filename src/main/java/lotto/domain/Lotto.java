package lotto.domain;

import lotto.exception.ErrorCode;

import java.util.List;

public class Lotto {

    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }

    public int countMatchingNumbers(WinningNumbers winningNumbers) {
        return numbers.stream()
                .filter(lottoNumber -> isWinningNumber(winningNumbers, lottoNumber))
                .mapToInt(lottoNumber -> 1)
                .sum();
    }

    public boolean isBonusNumberMatched(BonusNumber bonusNumber) {
        return numbers.stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(bonusNumber.getBonusNumber()));
    }

    private boolean isWinningNumber(WinningNumbers winningNumbers, Integer lottoNumber) {
        return winningNumbers.getWinningNumbers()
                .stream()
                .anyMatch(winningNumber -> winningNumber.equals(lottoNumber));
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateNoDuplicates(numbers);
        numbers.forEach(this::validateRange);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorCode.ERROR_LOTTO_INVALID_SIZE.getMessage());
        }
    }

    private void validateNoDuplicates(List<Integer> numbers) {
        if (numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException(ErrorCode.ERROR_LOTTO_DUPLICATE.getMessage());
        }
    }

    private void validateRange(int lottoNumber) {
        if (lottoNumber < LOTTO_NUMBER_MIN || lottoNumber > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException(ErrorCode.ERROR_LOTTO_INVALID_RANGE.getMessage());
        }
    }
}
