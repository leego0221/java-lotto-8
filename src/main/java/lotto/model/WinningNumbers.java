package lotto.model;

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
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
    }

    private void validateNoDuplicates(List<Integer> winningNumbers) {
        if (winningNumbers.size() != winningNumbers.stream().distinct().count()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호 중 중복된 숫자가 있습니다.");
        }
    }

    private void validateRange(int winningNumber) {
        if (winningNumber < 1 || winningNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 1에서 45 사이여야 합니다.");
        }
    }
}
