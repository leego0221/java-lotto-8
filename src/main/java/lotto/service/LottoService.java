package lotto.service;

import lotto.domain.*;
import lotto.exception.ErrorCode;

import java.util.List;
import java.util.stream.IntStream;

public class LottoService {

    private static final int LOTTO_PRIZE = 1000;
    private static final int LOOP_START_INDEX = 0;

    private final NumberGenerator numberGenerator;

    public LottoService(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public List<Lotto> purchase(PurchaseAmount purchaseAmount) {
        int purchaseCount = purchaseAmount.getPurchaseAmount() / LOTTO_PRIZE;

        return IntStream.range(LOOP_START_INDEX, purchaseCount)
                .mapToObj(i -> generateLotto())
                .toList();
    }

    public void checkDuplicate(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        boolean isDuplicate = winningNumbers.getWinningNumbers()
                .stream()
                .anyMatch(winningNumber -> winningNumber == bonusNumber.getBonusNumber());

        if (isDuplicate) {
            throw new IllegalArgumentException(ErrorCode.ERROR_NUMBERS_AND_BONUS_DUPLICATE.getMessage());
        }
    }

    private Lotto generateLotto() {
        List<Integer> numbers = numberGenerator.generate();
        return new Lotto(numbers);
    }
}
