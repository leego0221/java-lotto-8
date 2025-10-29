package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class LottoService {

    private final List<Lotto> lottos = new ArrayList<>();

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
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
        int purchaseCount = purchaseAmount.getPurchaseAmount() / 1000;
        for (int i = 0; i < purchaseCount; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Lotto lotto = new Lotto(numbers);
            lottos.add(lotto);
        }
    }
}
