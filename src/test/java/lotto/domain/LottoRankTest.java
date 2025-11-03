package lotto.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {

    @ParameterizedTest
    @MethodSource(value = "generateTestArguments")
    void 로또_순위가_일치_조건과_맞으면_테스트가_성공한다(
            LottoRank lottoRank,
            int matchingCount,
            boolean isBonusNumberMatched
    ) {
        // given by parameter

        // when
        boolean result = lottoRank.matches(matchingCount, isBonusNumberMatched);

        // then
        assertThat(result).isTrue();
    }

    static Stream<Arguments> generateTestArguments() {
        return Stream.of(
                Arguments.of(LottoRank.FIRST, 6, false),
                Arguments.of(LottoRank.SECOND, 5, true),
                Arguments.of(LottoRank.THIRD, 5, false),
                Arguments.of(LottoRank.FOURTH, 4, false),
                Arguments.of(LottoRank.FIFTH, 3, false)
        );
    }
}