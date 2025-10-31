package lotto.view;

import lotto.domain.LottoRank;
import lotto.dto.LottoDto;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void showRetryMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage() + " 다시 입력해주세요.");
    }

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

    public void showWinningStatistics(Map<LottoRank, Integer> ranks) {
        System.out.println("3개 일치 (5,000원) - " + ranks.get(LottoRank.FIFTH) + "개");
        System.out.println("4개 일치 (50,000원) - " + ranks.get(LottoRank.FOURTH) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + ranks.get(LottoRank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + ranks.get(LottoRank.SECOND) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + ranks.get(LottoRank.FIRST) + "개");
    }

    public void showProfitRate(String profitRate) {
        System.out.println("총 수익률은 " + profitRate + "%입니다.");
    }

    private void printNewLine() {
        System.out.println();
    }
}
