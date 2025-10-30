package lotto.domain;

import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }

    public int countMatching(WinningNumbers winningNumbers) {
        return (int) numbers.stream()
                .filter(lottoNumber -> isWinningNumber(winningNumbers, lottoNumber))
                .count();
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
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateNoDuplicates(List<Integer> numbers) {
        if (numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호 중 중복된 숫자가 있습니다.");
        }
    }

    private void validateRange(int lottoNumber) {
        if (lottoNumber < 1 || lottoNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1에서 45 사이여야 합니다.");
        }
    }
}
