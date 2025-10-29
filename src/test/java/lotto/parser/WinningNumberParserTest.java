package lotto.parser;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningNumberParserTest {

    @Test
    void 당첨_번호가_쉼표_기준으로_구분되면_테스트에_성공한다() {
        // given
        String input = "1,2,3,4,5,6";

        // when
        List<Integer> result = WinningNumberParser.parse(input);

        // then
        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }
}
