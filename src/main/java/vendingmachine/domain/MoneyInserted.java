package vendingmachine.domain;

public class MoneyInserted {
    private int moneyInserted;

    public MoneyInserted(int moneyInserted) {
        this.moneyInserted = moneyInserted;
    }

    public static MoneyInserted init() {
        return new MoneyInserted(0);
    }

    public static MoneyInserted from(int moneyInserted) {
        validateMoneyInserted(moneyInserted);
        return new MoneyInserted(moneyInserted);
    }

    private static void validateMoneyInserted(int moneyInserted) {
        validateRange(moneyInserted);
        validateUnit(moneyInserted);
    }

    private static void validateRange(int productPrice) {
        if (!isInRange(productPrice)) {
            throw new IllegalArgumentException("투입금액은 100원 이상이어야합니다.");
        }
    }

    private static boolean isInRange(int productPrice) {
        return productPrice >= 100;
    }

    private static void validateUnit(int productPrice) {
        if (!isValidUnit(productPrice)) {
            throw new IllegalArgumentException("투입금액은 최소 10원 단위이어야 합니다.");
        }
    }

    private static boolean isValidUnit(int productPrice) {
        return productPrice % 10 == 0;
    }

    public void apply(MoneyInserted moneyInserted) {
        this.moneyInserted = moneyInserted.moneyInserted;
    }
}
