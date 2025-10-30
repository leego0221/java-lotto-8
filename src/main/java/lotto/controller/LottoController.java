package lotto.controller;

import lotto.model.*;
import lotto.parser.WinningNumberParser;
import lotto.validation.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;
    private final LottoRankingService lottoRankingService;
    private final PrizeService prizeService;

    public LottoController(
            InputView inputView,
            OutputView outputView,
            LottoService lottoService,
            LottoRankingService lottoRankingService,
            PrizeService prizeService
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
        this.lottoRankingService = lottoRankingService;
        this.prizeService = prizeService;
    }

    public void run() {
        // 구매 금액 입력
        String purchaseAmountInput = inputView.readPurchaseAmount();
        InputValidator.validateIsBlank(purchaseAmountInput);
        InputValidator.validateIsInteger(purchaseAmountInput);
        PurchaseAmount purchaseAmount = new PurchaseAmount(Integer.parseInt(purchaseAmountInput));

        // 구입 금액에 해당하는 만큼 로또 발행하기
        lottoService.purchase(purchaseAmount);
        List<Lotto> lottos = lottoService.getLottos();

        // 구매 개수와 구매한 로또 리스트 출력
        outputView.showPurchaseCount(lottos.size());
        outputView.showPurchasedLottos(lottos);

        // 당첨 번호 입력
        String winningNumbersInput = inputView.readWinningNumbers();
        InputValidator.validateIsBlank(winningNumbersInput);

        // 당첨 번호 입력 파싱
        List<String> parsedWinningNumbers = WinningNumberParser.parse(winningNumbersInput);
        parsedWinningNumbers.forEach(winningNumber -> {
            InputValidator.validateIsBlank(winningNumber);
            InputValidator.validateIsInteger(winningNumber);
        });
        WinningNumbers winningNumbers = new WinningNumbers(parsedWinningNumbers);

        // 보너스 번호 입력
        String bonusNumberInput = inputView.readBonusNumber();
        InputValidator.validateIsBlank(bonusNumberInput);
        InputValidator.validateIsInteger(bonusNumberInput);
        BonusNumber bonusNumber = new BonusNumber(Integer.parseInt(bonusNumberInput));

        // 등수 배열 임시 생성
        int[] rawRanks = new int[7];
        lottos.forEach(lotto -> {
            // 번호 일치 여부에 따라 등수 매기기
            int rank = lottoRankingService.determineRank(lotto, winningNumbers, bonusNumber);
            rawRanks[rank]++;
        });

        // 등수에 따라 당첨 금액 부여하기
        List<Integer> ranks = Arrays.stream(rawRanks)
                .boxed()
                .toList();

        // 수익률 계산
        int totalPurchase = purchaseAmount.getPurchaseAmount();
        long totalPrize = 0L;
        for (int i = 1; i <= 5; i++) {
            if (ranks.get(i) != 0) {
                long prize = prizeService.payPrize(i);
                totalPrize += prize;
            }
        }
        String profitRate = lottoRankingService.calculateProfitRate(totalPrize, totalPurchase);

        // 당첨 통계 출력
        outputView.showWinningStatisticsTitle();
        outputView.showWinningStatistics(ranks);
        outputView.showProfitRate(profitRate);

        inputView.close();
    }
}
