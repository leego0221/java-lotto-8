package lotto.model;

import java.util.List;

public class WinningNumbers {

    private final List<WinningNumber> winningNumbers;

    public WinningNumbers(List<String> winningNumbers) {
        validate(winningNumbers);
        this.winningNumbers = winningNumbers.stream()
                .map(Integer::parseInt)
                .map(WinningNumber::new)
                .toList();;
    }

    public List<WinningNumber> getWinningNumbers() {
        return List.copyOf(winningNumbers);
    }

    private void validate(List<String> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }

        if (winningNumbers.size() != winningNumbers.stream().distinct().count()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호 중 중복된 숫자가 있습니다.");
        }
    }
}
