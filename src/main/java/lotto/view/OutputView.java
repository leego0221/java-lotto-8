package lotto.view;

import lotto.model.Lotto;

import java.util.List;

public class OutputView {

    public void showPurchaseCount(int purchaseCount) {
        System.out.println(purchaseCount + "개를 구매했습니다.");
    }

    public void showPurchasedLottos(List<Lotto> lottos) {
        lottos.forEach(System.out::println);
    }
}
