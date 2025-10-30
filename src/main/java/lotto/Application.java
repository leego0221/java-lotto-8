package lotto;

import lotto.controller.LottoController;
import lotto.model.LottoRankingService;
import lotto.model.LottoService;
import lotto.model.PrizeService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoService lottoService = new LottoService();
        LottoRankingService lottoRankingService = new LottoRankingService();
        PrizeService prizeService = new PrizeService();
        LottoController lottoController = new LottoController(inputView, outputView, lottoService, lottoRankingService, prizeService);

        lottoController.run();
    }
}
