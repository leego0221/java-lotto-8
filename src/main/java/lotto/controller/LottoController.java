package lotto.controller;

import lotto.model.*;
import lotto.parser.WinningNumberParser;
import lotto.validation.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

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
        // 구매 금액 입력
        String purchaseAmountInput = inputView.readPurchaseAmount();
        InputValidator.validateIsBlank(purchaseAmountInput);
        InputValidator.validateIsInteger(purchaseAmountInput);
        PurchaseAmount purchaseAmount = new PurchaseAmount(Integer.parseInt(purchaseAmountInput));

        lottoService.purchase(purchaseAmount);
        List<Lotto> lottos = lottoService.getLottos();

        // 구매 개수 출력
        outputView.showPurchaseCount(lottos.size());

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

        System.out.println("purchaseAmount = " + purchaseAmount.getPurchaseAmount());
        winningNumbers.getWinningNumbers().forEach(winningNumber ->
                System.out.println("winningNumber = " + winningNumber)
        );
        System.out.println("bonusNumber = " + bonusNumber.getBonusNumber());
    }
}
