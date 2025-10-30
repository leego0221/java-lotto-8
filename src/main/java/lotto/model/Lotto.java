package lotto.model;

import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public int countMatching(WinningNumbers winningNumbers) {
        return (int) numbers.stream()
                .filter(number -> isWinningNumber(winningNumbers, number))
                .count();
    }
    
    public boolean isBonusNumberMatched(BonusNumber bonusNumber) {
        return numbers.stream()
                .anyMatch(number -> number.equals(bonusNumber.getBonusNumber()));
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }

        if (numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호 중 중복된 숫자가 있습니다.");
        }
    }

    private boolean isWinningNumber(WinningNumbers winningNumbers, Integer number) {
        return winningNumbers.getWinningNumbers()
                .stream()
                .map(WinningNumber::getWinningNumber)
                .anyMatch(winningNumber -> winningNumber.equals(number));
    }

    @Override
    public String toString() {
        List<String> numberValue = numbers.stream()
                .map(number -> Integer.toString(number))
                .toList();
        String result = String.join(", ", numberValue);
        return "[" + result + "]";
    }
}
