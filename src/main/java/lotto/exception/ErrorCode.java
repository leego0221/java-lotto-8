package lotto.exception;

public enum ErrorCode {

    ERROR_INPUT_BLANK("[ERROR] 입력값이 비어있습니다."),
    ERROR_INPUT_NOT_INTEGER("[ERROR] 입력값이 Integer 타입이 아닙니다."),

    ERROR_PARSED_BLANK("[ERROR] 파싱값이 비어있습니다."),
    ERROR_PARSED_NOT_INTEGER("[ERROR] 파싱값이 Integer 타입이 아닙니다."),

    ERROR_AMOUNT_NOT_ENOUGH("[ERROR] 로또 구입 금액은 1,000원 이상이어야 합니다."),
    ERROR_AMOUNT_INVALID_UNIT("[ERROR] 로또 구입 금액은 1,000원 단위여야 합니다."),

    ERROR_NUMBERS_INVALID_SIZE("[ERROR] 당첨 번호는 6개여야 합니다."),
    ERROR_NUMBERS_DUPLICATE("[ERROR] 당첨 번호 중 중복된 숫자가 있습니다."),
    ERROR_NUMBERS_INVALID_RANGE("[ERROR] 당첨 번호는 1에서 45 사이여야 합니다."),

    ERROR_BONUS_INVALID_RANGE("[ERROR] 보너스 번호는 1에서 45 사이여야 합니다."),

    ERROR_NUMBERS_AND_BONUS_DUPLICATE("[ERROR] 당첨 번호와 보너스 번호가 중복됩니다."),

    ERROR_LOTTO_INVALID_SIZE("[ERROR] 로또 번호는 6개여야 합니다."),
    ERROR_LOTTO_DUPLICATE("[ERROR] 로또 번호 중 중복된 숫자가 있습니다."),
    ERROR_LOTTO_INVALID_RANGE("[ERROR] 로또 번호는 1에서 45 사이여야 합니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
