package lotto.service;

import lotto.domain.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class LottoRankServiceTest {

    private final LottoRankCounter lottoRankCounter = new LottoRankCounter();
    private final LottoRankService lottoRankService = new LottoRankService(lottoRankCounter);

    @Test
    void 로또가_1등에_당첨된_후_순위표에서_확인이_되면_테스트가_성공한다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);

        // when
        lottoRankService.determineRank(lotto, winningNumbers, bonusNumber);
        Map<LottoRank, Integer> lottoRanks = lottoRankService.getRanks();

        // then
        assertThat(lottoRanks).contains(entry(LottoRank.FIRST, 1));
    }

    @Test
    void 로또가_2등에_당첨된_후_순위표에서_확인이_되면_테스트가_성공한다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 7));
        BonusNumber bonusNumber = new BonusNumber(6);

        // when
        lottoRankService.determineRank(lotto, winningNumbers, bonusNumber);
        Map<LottoRank, Integer> lottoRanks = lottoRankService.getRanks();

        // then
        assertThat(lottoRanks).contains(entry(LottoRank.SECOND, 1));
    }

    @Test
    void 로또_1000원어치_구매_후_3등에_당첨되어_수익률이_500퍼센트면_테스트가_성공한다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 11, 12, 13));
        BonusNumber bonusNumber = new BonusNumber(7);

        lottoRankService.determineRank(lotto, winningNumbers, bonusNumber); // 3등: 5000원

        // when
        double profitRate = lottoRankService.calculateProfitRate(1000);

        // then
        assertThat(profitRate).isEqualTo(500);
    }
}