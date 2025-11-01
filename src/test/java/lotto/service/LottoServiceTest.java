package lotto.service;

import lotto.domain.*;
import lotto.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoServiceTest {

    private final LottoService lottoService = new LottoService();

    @Test
    void 구입_금액만큼_로또를_발행하면_테스트가_성공한다() {
        // given
        PurchaseAmount purchaseAmount = new PurchaseAmount(3000);

        // when
        List<Lotto> lottos = lottoService.purchase(purchaseAmount);

        // then
        assertThat(lottos).hasSize(3);
    }

    @Test
    void 보너스_번호가_당첨_번호들과_중복되지_않으면_테스트가_성공한다() {
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
                .hasMessageContaining(ErrorCode.ERROR_NUMBERS_AND_BONUS_DUPLICATE.getMessage());
    }
}
