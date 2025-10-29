package lotto.controller;

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
        String winningNumbersInput = inputView.readWinningNumbers();

        System.out.println("purchaseAmountInput = " + purchaseAmountInput);
        System.out.println("winningNumbersInput = " + winningNumbersInput);
    }
}
