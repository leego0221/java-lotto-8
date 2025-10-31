package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LottoRankCounter {

    private static final int INITIAL_COUNT = 0;

    private final Map<LottoRank, Integer> lottoResult = new HashMap<>();

    public LottoRankCounter() {
        Arrays.stream(LottoRank.values())
                .forEach(rank -> lottoResult.put(rank, INITIAL_COUNT));
    }

    public Map<LottoRank, Integer> getLottoResult() {
        return Map.copyOf(lottoResult);
    }

    public void update(int matchingCount, boolean isBonusNumberMatched) {
        LottoRank lottoRank = Arrays.stream(LottoRank.values())
                .filter(rank -> rank.matches(matchingCount, isBonusNumberMatched))
                .findFirst()
                .orElse(LottoRank.NOTHING);

        Integer count = lottoResult.get(lottoRank);
        lottoResult.replace(lottoRank, count + 1);
    }
}
