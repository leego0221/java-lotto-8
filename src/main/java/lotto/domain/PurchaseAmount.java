package lotto.domain;

import lotto.exception.ErrorCode;

public class PurchaseAmount {

    private static final int PURCHASE_AMOUNT_UNIT = 1000;
    private static final int ZERO_REMAINDER = 0;

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
        if (purchaseAmount < PURCHASE_AMOUNT_UNIT) {
            throw new IllegalArgumentException(ErrorCode.ERROR_AMOUNT_NOT_ENOUGH.getMessage());
        }
    }

    private void validateUnit(int purchaseAmount) {
        if (purchaseAmount % PURCHASE_AMOUNT_UNIT != ZERO_REMAINDER) {
            throw new IllegalArgumentException(ErrorCode.ERROR_AMOUNT_INVALID_UNIT.getMessage());
        }
    }
}
