package lotto.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class LottoRankCounterTest {

    private final LottoRankCounter lottoRankCounter = new LottoRankCounter();

    @Test
    void 로또_순위_카운터가_정상적으로_초기화되면_테스트가_성공한다() {
        // given
        Map<LottoRank, Integer> expectedResult = Map.of(
                LottoRank.FIRST, 0,
                LottoRank.SECOND, 0,
                LottoRank.THIRD, 0,
                LottoRank.FOURTH, 0,
                LottoRank.FIFTH, 0,
                LottoRank.NOTHING, 0
        );

        // when
        Map<LottoRank, Integer> lottoResult = lottoRankCounter.getLottoResult();

        // then
        assertThat(lottoResult).containsAllEntriesOf(expectedResult);
    }

    @Test
    void 로또_순위_카운터_객체는_외부에서_변경을_시도하면_예외가_발생한다() {
        // given
        Map<LottoRank, Integer> lottoResult = lottoRankCounter.getLottoResult();

        // when & then
        assertThatThrownBy(() -> lottoResult.replace(LottoRank.FIRST, 1))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @ParameterizedTest
    @MethodSource("generateTestArguments")
    void 로또_순위_카운터_갱신_조건이_맞으면_테스트가_성공한다(
            int matchingCount,
            boolean isBonusNumberMatched,
            LottoRank lottoRank
    ) {
        // given by parameter

        // when
        lottoRankCounter.update(matchingCount, isBonusNumberMatched);
        Map<LottoRank, Integer> lottoResult = lottoRankCounter.getLottoResult();

        // then
        assertThat(lottoResult).contains(entry(lottoRank, 1));
    }

    @ParameterizedTest
    @CsvSource(value = {"2,false", "7,true", "-3,false"})
    void 로또_순위_카운터에_예상치_못한_조건으로_무효_처리되면_테스트가_성공한다(
            int matchingCount,
            boolean isBonusNumberMatched
    ) {
        // given by parameter

        // when
        lottoRankCounter.update(matchingCount, isBonusNumberMatched);
        Map<LottoRank, Integer> lottoResult = lottoRankCounter.getLottoResult();

        // then
        assertThat(lottoResult).contains(entry(LottoRank.NOTHING, 1));
    }

    static Stream<Arguments> generateTestArguments() {
        return Stream.of(
                Arguments.of(6, false, LottoRank.FIRST),
                Arguments.of(5, true, LottoRank.SECOND),
                Arguments.of(5, false, LottoRank.THIRD),
                Arguments.of(4, false, LottoRank.FOURTH),
                Arguments.of(3, false, LottoRank.FIFTH)
        );
    }
}