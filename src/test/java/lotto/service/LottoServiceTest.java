package lotto.service;

import lotto.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class LottoServiceTest {

    private static final String ERROR_MESSAGE = "[ERROR]";
    private final LottoService lottoService = new LottoService();

    @Test
    void 보너스_번호가_당첨_번호들과_중복되지_않으면_테스트에_성공한다() {
        // given
        BonusNumber bonusNumber = new BonusNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

        // when & then
        assertThatCode(() -> lottoService.checkDuplicate(winningNumbers, bonusNumber))
                .doesNotThrowAnyException();
    }

    @Test
    void 보너스_번호가_당첨_번호들과_중복되면_예외가_발생한다() {
        // given
        BonusNumber bonusNumber = new BonusNumber(3);
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

        // when & then
        assertThatThrownBy(() -> lottoService.checkDuplicate(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }

    @Test
    void 구입_금액에_해당하는_만큼_로또를_발행한다() {
        // given
        PurchaseAmount purchaseAmount = new PurchaseAmount(3000);

        // when
        List<Lotto> lottos = lottoService.purchase(purchaseAmount);

        // then
        assertThat(lottos).hasSize(3);
    }
}
