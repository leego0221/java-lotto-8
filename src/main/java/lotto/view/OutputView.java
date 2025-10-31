package lotto.view;

import lotto.domain.LottoRank;
import lotto.dto.LottoDto;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String OUTPUT_TRY_AGAIN = " 다시 입력해주세요.";

    private static final String OUTPUT_PURCHASE_COUNT = "개를 구매했습니다.";

    private static final String OUTPUT_WINNING_STATISTICS_TITLE = "당첨 통계";
    private static final String OUTPUT_DIVIDER = "---";

    private static final String OUTPUT_FIFTH_PLACE = "3개 일치 (5,000원) - ";
    private static final String OUTPUT_FOURTH_PLACE = "4개 일치 (50,000원) - ";
    private static final String OUTPUT_THIRD_PLACE = "5개 일치 (1,500,000원) - ";
    private static final String OUTPUT_SECOND_PLACE = "5개 일치, 보너스 볼 일치 (30,000,000원) - ";
    private static final String OUTPUT_FIRST_PLACE = "6개 일치 (2,000,000,000원) - ";
    private static final String OUTPUT_COUNT_SUFFIX = "개";

    private static final String OUTPUT_TOTAL_PROFIT = "총 수익률은 ";
    private static final String OUTPUT_PERCENT = "%";
    private static final String OUTPUT_TOTAL_PROFIT_SUFFIX = "입니다.";

    public void showRetryMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage() + OUTPUT_TRY_AGAIN);
    }

    public void showPurchaseCount(int purchaseCount) {
        printNewLine();
        System.out.println(purchaseCount + OUTPUT_PURCHASE_COUNT);
    }

    public void showPurchasedLottos(List<LottoDto> lottoDtos) {
        lottoDtos.forEach(System.out::println);
    }

    public void showWinningStatisticsTitle() {
        printNewLine();
        System.out.println(OUTPUT_WINNING_STATISTICS_TITLE);
        System.out.println(OUTPUT_DIVIDER);
    }

    public void showWinningStatistics(Map<LottoRank, Integer> ranks) {
        System.out.println(OUTPUT_FIFTH_PLACE + ranks.get(LottoRank.FIFTH) + OUTPUT_COUNT_SUFFIX);
        System.out.println(OUTPUT_FOURTH_PLACE + ranks.get(LottoRank.FOURTH) + OUTPUT_COUNT_SUFFIX);
        System.out.println(OUTPUT_THIRD_PLACE + ranks.get(LottoRank.THIRD) + OUTPUT_COUNT_SUFFIX);
        System.out.println(OUTPUT_SECOND_PLACE + ranks.get(LottoRank.SECOND) + OUTPUT_COUNT_SUFFIX);
        System.out.println(OUTPUT_FIRST_PLACE + ranks.get(LottoRank.FIRST) + OUTPUT_COUNT_SUFFIX);
    }

    public void showProfitRate(String profitRate) {
        System.out.println(OUTPUT_TOTAL_PROFIT + profitRate + OUTPUT_PERCENT + OUTPUT_TOTAL_PROFIT_SUFFIX);
    }

    private void printNewLine() {
        System.out.println();
    }
}
