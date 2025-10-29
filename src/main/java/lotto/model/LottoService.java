package lotto.model;

import java.util.List;

public class LottoService {

    private List<Lotto> lottos;

    public List<Lotto> getLottos() {
        return List.of();
    }

    public void checkDuplicate(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        boolean isDuplicate = winningNumbers.getWinningNumbers()
                .stream()
                .anyMatch(winningNumber -> winningNumber.getWinningNumber() == bonusNumber.getBonusNumber());

        if (isDuplicate) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호가 당첨 번호와 중복됩니다.");
        }
    }

    public void purchase(PurchaseAmount purchaseAmount) {
    }
}
