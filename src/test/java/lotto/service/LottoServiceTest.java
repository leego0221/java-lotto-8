package lotto.service;

import lotto.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoServiceTest {

    private static final String ERROR_MESSAGE = "[ERROR]";
    private final LottoRankCounter lottoRankCounter = new LottoRankCounter();
    private final LottoService lottoService = new LottoService(lottoRankCounter);

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

    @Test
    void 로또_번호가_당첨_번호_6개와_일치하면_1등이다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);

        // when
        int rank = lottoService.determineRank(lotto, winningNumbers, bonusNumber);

        // then
        assertThat(rank).isEqualTo(1);
    }

    @Test
    void 로또_번호가_당청_번호_5개와_보너스_번호와_일치하면_2등이다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);

        // when
        int rank = lottoService.determineRank(lotto, winningNumbers, bonusNumber);

        // then
        assertThat(rank).isEqualTo(2);
    }

    @Test
    void 로또_번호가_당첨_번호_5개와_일치하면_3등이다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 11));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);

        // when
        int rank = lottoService.determineRank(lotto, winningNumbers, bonusNumber);

        // then
        assertThat(rank).isEqualTo(3);
    }

    @Test
    void 로또_번호가_당첨_번호_4개와_일치하면_4등이다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 11, 12));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);

        // when
        int rank = lottoService.determineRank(lotto, winningNumbers, bonusNumber);

        // then
        assertThat(rank).isEqualTo(4);
    }

    @Test
    void 로또_번호가_당첨_번호_3개와_일치하면_5등이다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 11, 12, 13));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);

        // when
        int rank = lottoService.determineRank(lotto, winningNumbers, bonusNumber);

        // then
        assertThat(rank).isEqualTo(5);
    }

    @ParameterizedTest
    @EnumSource(value = LottoRank.class)
    void 등수에_따라_당첨_금액을_부여한다(LottoRank lottoRank) {
        // given by parameter

        // when
        long prize = lottoService.payPrize(lottoRank);

        // then
        assertThat(prize).isEqualTo(lottoRank.getPrize());
    }
}
