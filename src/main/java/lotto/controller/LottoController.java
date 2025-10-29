package lotto.controller;

import lotto.validation.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

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

        String winningNumbersInput = inputView.readWinningNumbers();
        InputValidator.validateIsBlank(winningNumbersInput);

        String bonusNumberInput = inputView.readBonusNumber();
        InputValidator.validateIsBlank(bonusNumberInput);
        InputValidator.validateIsInteger(bonusNumberInput);

        System.out.println("purchaseAmountInput = " + purchaseAmountInput);
        System.out.println("winningNumbersInput = " + winningNumbersInput);
        System.out.println("bonusNumberInput = " + bonusNumberInput);
    }
}
