package lotto.dto;

import java.util.List;

public class LottoDto {

    private static final String DELIMITER = ", ";
    private static final String LEFT_BRACKET = "[";
    private static final String RIGHT_BRACKET = "]";

    private final List<Integer> lottoNumbers;

    public LottoDto(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    @Override
    public String toString() {
        List<String> numberValues = lottoNumbers.stream()
                .sorted()
                .map(String::valueOf)
                .toList();

        String result = String.join(DELIMITER, numberValues);
        return LEFT_BRACKET + result + RIGHT_BRACKET;
    }
}
