package lotto.config;

import lotto.controller.LottoController;
import lotto.domain.LottoRankCounter;
import lotto.service.LottoRankService;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {

    public LottoController lottoController() {
        return new LottoController(inputView(), outputView(), lottoService(), lottoRankService());
    }

    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }

    public LottoService lottoService() {
        return new LottoService();
    }

    public LottoRankService lottoRankService() {
        return new LottoRankService(lottoRankCounter());
    }

    public LottoRankCounter lottoRankCounter() {
        return new LottoRankCounter();
    }
}
