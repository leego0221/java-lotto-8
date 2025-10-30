package lotto.controller;

import lotto.dto.LottoDto;
import lotto.domain.*;
import lotto.parser.InputParser;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Arrays;
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
        lottoService.checkDuplicate(winningNumbers, bonusNumber);

        lottos.forEach(lotto -> lottoService.determineRank(lotto, winningNumbers, bonusNumber));

        int totalPurchase = purchaseAmount.getPurchaseAmount();
        String profitRate = lottoService.calculateProfitRate(totalPurchase);

        outputView.showWinningStatisticsTitle();
        Map<LottoRank, Integer> ranks = lottoService.getRanks();
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
