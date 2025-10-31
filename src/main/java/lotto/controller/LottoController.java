package lotto.controller;

import lotto.dto.LottoDto;
import lotto.domain.*;
import lotto.parser.InputParser;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;

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
        PurchaseAmount purchaseAmount = readPurchaseAmount();

        List<Lotto> lottos = lottoService.purchase(purchaseAmount);

        List<LottoDto> lottoDtos = mapToLottoDtos(lottos);
        outputView.showPurchaseCount(lottoDtos.size());
        outputView.showPurchasedLottos(lottoDtos);

        WinningNumbers winningNumbers = readWinningNumbers();
        BonusNumber bonusNumber = readBonusNumber();
        checkDuplicate(winningNumbers, bonusNumber);

        lottos.forEach(lotto -> lottoService.determineRank(lotto, winningNumbers, bonusNumber));

        int totalPurchase = purchaseAmount.getPurchaseAmount();
        double profitRate = lottoService.calculateProfitRate(totalPurchase);

        outputView.showWinningStatisticsTitle();
        Map<LottoRank, Integer> ranks = lottoService.getRanks();
        outputView.showWinningStatistics(ranks);
        outputView.showProfitRate(profitRate);

        inputView.close();
    }

    private PurchaseAmount readPurchaseAmount() {
        try {
            String purchaseAmountInput = inputView.readPurchaseAmount();
            int parsedPurchaseAmount = InputParser.parseInteger(purchaseAmountInput);
            return new PurchaseAmount(parsedPurchaseAmount);
        } catch (IllegalArgumentException e) {
            outputView.showRetryMessage(e);
            return readPurchaseAmount(); // 재귀 호출
        }
    }

    private WinningNumbers readWinningNumbers() {
        try {
            String winningNumbersInput = inputView.readWinningNumbers();
            List<Integer> parsedWinningNumbers = InputParser.parseWinningNumbers(winningNumbersInput);
            return new WinningNumbers(parsedWinningNumbers);
        } catch (IllegalArgumentException e) {
            outputView.showRetryMessage(e);
            return readWinningNumbers(); // 재귀 호출
        }
    }

    private BonusNumber readBonusNumber() {
        try {
            String bonusNumberInput = inputView.readBonusNumber();
            int parsedBonusNumber = InputParser.parseInteger(bonusNumberInput);
            return new BonusNumber(parsedBonusNumber);
        } catch (IllegalArgumentException e) {
            outputView.showRetryMessage(e);
            return readBonusNumber(); // 재귀 호출
        }
    }

    private void checkDuplicate(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        try {
            lottoService.checkDuplicate(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            outputView.showRetryMessage(e);
            checkDuplicate(winningNumbers, readBonusNumber()); // 재귀 호출
        }
    }

    private List<LottoDto> mapToLottoDtos(List<Lotto> lottos) {
        return lottos.stream()
                .map(lotto -> new LottoDto(lotto.getNumbers()))
                .toList();
    }
}
