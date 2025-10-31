package lotto.service;

import lotto.domain.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class LottoRankServiceTest {

    private static final String ERROR_MESSAGE = "[ERROR]";
    private final LottoRankCounter lottoRankCounter = new LottoRankCounter();
    private final LottoRankService lottoRankService = new LottoRankService(lottoRankCounter);

    @Test
    void 로또_번호가_당첨_번호_6개와_일치하면_1등이다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);

        // when
        lottoRankService.determineRank(lotto, winningNumbers, bonusNumber);
        Map<LottoRank, Integer> lottoResult = lottoRankCounter.getLottoResult();

        // then
        assertThat(lottoResult).contains(entry(LottoRank.FIRST, 1));
    }

    @Test
    void 로또_번호가_당청_번호_5개와_보너스_번호와_일치하면_2등이다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);

        // when
        lottoRankService.determineRank(lotto, winningNumbers, bonusNumber);
        Map<LottoRank, Integer> lottoResult = lottoRankCounter.getLottoResult();

        // then
        assertThat(lottoResult).contains(entry(LottoRank.SECOND, 1));
    }

    @Test
    void 로또_번호가_당첨_번호_5개와_일치하면_3등이다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 11));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);

        // when
        lottoRankService.determineRank(lotto, winningNumbers, bonusNumber);
        Map<LottoRank, Integer> lottoResult = lottoRankCounter.getLottoResult();

        // then
        assertThat(lottoResult).contains(entry(LottoRank.THIRD, 1));
    }

    @Test
    void 로또_번호가_당첨_번호_4개와_일치하면_4등이다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 11, 12));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);

        // when
        lottoRankService.determineRank(lotto, winningNumbers, bonusNumber);
        Map<LottoRank, Integer> lottoResult = lottoRankCounter.getLottoResult();

        // then
        assertThat(lottoResult).contains(entry(LottoRank.FOURTH, 1));
    }

    @Test
    void 로또_번호가_당첨_번호_3개와_일치하면_5등이다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 11, 12, 13));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);

        // when
        lottoRankService.determineRank(lotto, winningNumbers, bonusNumber);
        Map<LottoRank, Integer> lottoResult = lottoRankCounter.getLottoResult();

        // then
        assertThat(lottoResult).contains(entry(LottoRank.FIFTH, 1));
    }
}