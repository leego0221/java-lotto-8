package lotto.domain;

public enum LottoRank {

    FIRST(2_000_000_000) {
        @Override
        public boolean matches(int matchingCount, boolean isBonusNumberMatched) {
            return matchingCount == 6;
        }
    },
    SECOND(30_000_000) {
        @Override
        public boolean matches(int matchingCount, boolean isBonusNumberMatched) {
            return matchingCount == 5 && isBonusNumberMatched;
        }
    },
    THIRD(1_500_000) {
        @Override
        public boolean matches(int matchingCount, boolean isBonusNumberMatched) {
            return matchingCount == 5;
        }
    },
    FOURTH(50_000) {
        @Override
        public boolean matches(int matchingCount, boolean isBonusNumberMatched) {
            return matchingCount == 4;
        }
    },
    FIFTH(5_000) {
        @Override
        public boolean matches(int matchingCount, boolean isBonusNumberMatched) {
            return matchingCount == 3;
        }
    },
    NOTHING(0) {
        @Override
        public boolean matches(int matchingCount, boolean isBonusNumberMatched) {
            return false;
        }
    };

    private final long prize;

    LottoRank(long prize) {
        this.prize = prize;
    }

    public long getPrize() {
        return prize;
    }

    public abstract boolean matches(int matchingCount, boolean isBonusNumberMatched);
}
