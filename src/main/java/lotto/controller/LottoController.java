package lotto.controller;

import lotto.model.BonusNumber;
import lotto.model.PurchaseAmount;
import lotto.model.WinningNumber;
import lotto.parser.WinningNumberParser;
import lotto.validation.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String purchaseAmountInput = inputView.readPurchaseAmount();
        InputValidator.validateIsBlank(purchaseAmountInput);
        InputValidator.validateIsInteger(purchaseAmountInput);
        PurchaseAmount purchaseAmount = new PurchaseAmount(Integer.parseInt(purchaseAmountInput));

        String winningNumbersInput = inputView.readWinningNumbers();
        InputValidator.validateIsBlank(winningNumbersInput);

        List<String> parsedWinningNumbers = WinningNumberParser.parse(winningNumbersInput);
        parsedWinningNumbers.forEach(winningNumber -> {
            InputValidator.validateIsBlank(winningNumber);
            InputValidator.validateIsInteger(winningNumber);
        });
        List<WinningNumber> winningNumbers = parsedWinningNumbers.stream()
                .map(Integer::parseInt)
                .map(WinningNumber::new)
                .toList();

        String bonusNumberInput = inputView.readBonusNumber();
        InputValidator.validateIsBlank(bonusNumberInput);
        InputValidator.validateIsInteger(bonusNumberInput);
        BonusNumber bonusNumber = new BonusNumber(Integer.parseInt(bonusNumberInput));

        System.out.println("purchaseAmount = " + purchaseAmount.getPurchaseAmount());
        winningNumbers.forEach(winningNumber ->
            System.out.println("winningNumber = " + winningNumber.getWinningNumber())
        );
        System.out.println("bonusNumber = " + bonusNumber.getBonusNumber());
    }
}
