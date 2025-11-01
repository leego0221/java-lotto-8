package lotto.config;

import lotto.controller.LottoController;
import lotto.domain.LottoNumberGenerator;
import lotto.domain.LottoRankCounter;
import lotto.domain.NumberGenerator;
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
        return new LottoService(numberGenerator());
    }

    public LottoRankService lottoRankService() {
        return new LottoRankService(lottoRankCounter());
    }

    public NumberGenerator numberGenerator() {
        return new LottoNumberGenerator();
    }

    public LottoRankCounter lottoRankCounter() {
        return new LottoRankCounter();
    }
}
