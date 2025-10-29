package lotto.model;

public class WinningNumber {

    private final int winningNumber;

    public WinningNumber(int winningNumber) {
        validate(winningNumber);
        this.winningNumber = winningNumber;
    }

    public int getWinningNumber() {
        return winningNumber;
    }

    private void validate(int winningNumber) {
        if (winningNumber < 1 || winningNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1에서 45 사이여야 합니다.");
        }
    }
}
