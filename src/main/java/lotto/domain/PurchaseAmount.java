package lotto.domain;

import lotto.exception.ErrorCode;

public class PurchaseAmount {

    private final int purchaseAmount;

    public PurchaseAmount(int purchaseAmount) {
        validate(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    private void validate(int purchaseAmount) {
        validateMinimum(purchaseAmount);
        validateUnit(purchaseAmount);
    }

    private void validateMinimum(int purchaseAmount) {
        if (purchaseAmount < 1000) {
            throw new IllegalArgumentException(ErrorCode.ERROR_AMOUNT_NOT_ENOUGH.getMessage());
        }
    }

    private void validateUnit(int purchaseAmount) {
        if (purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException(ErrorCode.ERROR_AMOUNT_INVALID_UNIT.getMessage());
        }
    }
}
