package lotto.view;

import lotto.dto.LottoDto;

import java.util.List;

public class OutputView {

    public void showPurchaseCount(int purchaseCount) {
        printNewLine();
        System.out.println(purchaseCount + "개를 구매했습니다.");
    }

    public void showPurchasedLottos(List<LottoDto> lottoDtos) {
        lottoDtos.forEach(System.out::println);
    }

    public void showWinningStatisticsTitle() {
        printNewLine();
        System.out.println("당첨 통계");
        System.out.println("---");
    }

    public void showWinningStatistics(List<Integer> ranks) {
        System.out.println("3개 일치 (5,000원) - " + ranks.get(5) + "개");
        System.out.println("4개 일치 (50,000원) - " + ranks.get(4) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + ranks.get(3) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + ranks.get(2) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + ranks.get(1) + "개");
    }

    public void showProfitRate(String profitRate) {
        System.out.println("총 수익률은 " + profitRate + "%입니다.");
    }

    private void printNewLine() {
        System.out.println();
    }
}
