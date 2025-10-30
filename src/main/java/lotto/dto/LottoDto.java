package lotto.dto;

import java.util.List;

public class LottoDto {

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

        String result = String.join(", ", numberValues);
        return "[" + result + "]";
    }
}
