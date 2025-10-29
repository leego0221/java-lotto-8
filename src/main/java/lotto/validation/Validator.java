package lotto.validation;

import lotto.model.WinningNumbers;

public class Validator {

    private static final int LOTTO_PRICE = 1000;

    public void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 1,000원 단위여야 합니다.");
        }
    }

    public void validateDuplicate(WinningNumbers winningNumbers, int bonusNumber) {
    }
}
