package lotto.model;

public class LottoService {

    public void checkDuplicate(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        boolean isDuplicate = winningNumbers.getWinningNumbers()
                .stream()
                .anyMatch(winningNumber -> winningNumber.getWinningNumber() == bonusNumber.getBonusNumber());

        if (isDuplicate) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호가 당첨 번호와 중복됩니다.");
        }
    }
}
