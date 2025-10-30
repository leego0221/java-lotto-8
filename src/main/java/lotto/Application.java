package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoRankCounter;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        LottoRankCounter lottoRankCounter = new LottoRankCounter();
        LottoService lottoService = new LottoService(lottoRankCounter);

        LottoController lottoController = new LottoController(inputView, outputView, lottoService);

        lottoController.run();
    }
}
