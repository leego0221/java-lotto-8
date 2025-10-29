package lotto.model;

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
        if (purchaseAmount < 1000) {
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 1,000원 이상이어야 합니다.");
        }

        if (purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 1,000원 단위여야 합니다.");
        }
    }
}
