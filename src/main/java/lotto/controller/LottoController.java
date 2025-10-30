package lotto.controller;

import lotto.dto.LottoDto;
import lotto.domain.*;
import lotto.parser.InputParser;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        // [입력] 구매 금액
        PurchaseAmount purchaseAmount = readPurchaseAmount();

        // [기능] 구입 금액에 해당하는 만큼 로또 발행
        List<Lotto> lottos = lottoService.purchase(purchaseAmount);

        // [출력] 구매 개수와 구매한 로또 리스트
        List<LottoDto> lottoDtos = mapToLottoDtos(lottos);
        outputView.showPurchaseCount(lottoDtos.size());
        outputView.showPurchasedLottos(lottoDtos);

        // [입력] 당첨 번호와 보너스 번호
        WinningNumbers winningNumbers = readWinningNumbers();
        BonusNumber bonusNumber = readBonusNumber();
        lottoService.checkDuplicate(winningNumbers, bonusNumber);

        // [기능] 등수 배열 임시 생성
        int[] rawRanks = new int[7];
        lottos.forEach(lotto -> {
            // [기능] 번호 일치 여부에 따라 등수 매기기
            int rank = lottoService.determineRank(lotto, winningNumbers, bonusNumber);
            rawRanks[rank]++;
        });

        // [기능] 등수에 따라 당첨 금액 부여하기
        List<Integer> ranks = Arrays.stream(rawRanks)
                .boxed()
                .toList();

        // [기능] 수익률 계산
        int totalPurchase = purchaseAmount.getPurchaseAmount();
        long totalPrize = 0L;
        for (int i = 1; i <= 5; i++) {
            if (ranks.get(i) != 0) {
                long prize = lottoService.payPrize(i);
                totalPrize += prize;
            }
        }
        String profitRate = lottoService.calculateProfitRate(totalPrize, totalPurchase);

        // [출력] 당첨 통계
        outputView.showWinningStatisticsTitle();
        outputView.showWinningStatistics(ranks);
        outputView.showProfitRate(profitRate);

        inputView.close();
    }

    private PurchaseAmount readPurchaseAmount() {
        String purchaseAmountInput = inputView.readPurchaseAmount();
        int parsedPurchaseAmount = InputParser.parseInteger(purchaseAmountInput);
        return new PurchaseAmount(parsedPurchaseAmount);
    }

    private WinningNumbers readWinningNumbers() {
        String winningNumbersInput = inputView.readWinningNumbers();
        List<Integer> parsedWinningNumbers = InputParser.parseWinningNumbers(winningNumbersInput);
        return new WinningNumbers(parsedWinningNumbers);
    }

    private BonusNumber readBonusNumber() {
        String bonusNumberInput = inputView.readBonusNumber();
        int parsedBonusNumber = InputParser.parseInteger(bonusNumberInput);
        return new BonusNumber(parsedBonusNumber);
    }

    private List<LottoDto> mapToLottoDtos(List<Lotto> lottos) {
        return lottos.stream()
                .map(lotto -> new LottoDto(lotto.getNumbers()))
                .toList();
    }
}
