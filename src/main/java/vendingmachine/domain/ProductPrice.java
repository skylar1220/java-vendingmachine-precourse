package vendingmachine.domain;

public class ProductPrice {
    private final int productPrice;

    public ProductPrice(int productPrice) {
        validateProductPrice(productPrice);
        this.productPrice = productPrice;
    }

    private void validateProductPrice(int productPrice) {
        validateRange(productPrice);
        validateUnit(productPrice);
    }

    private void validateRange(int productPrice) {
        if (!isInRange(productPrice)) {
            throw new IllegalArgumentException("가격은 100원 이상이어야합니다.");
        }
    }

    private boolean isInRange(int productPrice) {
        return productPrice >= 100;
    }

    private void validateUnit(int productPrice) {
        if (!isValidUnit(productPrice)) {
            throw new IllegalArgumentException("가격은 최소 10원 단위이어야 합니다.");
        }
    }

    private boolean isValidUnit(int productPrice) {
        return productPrice % 10 == 0;
    }

    public boolean isUnderOrEqual(MoneyInserted moneyInserted) {
        return moneyInserted.isOverOrEqual(productPrice);
    }

    public int calculateMoneyAfterBuying(int moneyInserted) {
        return moneyInserted - productPrice;
    }
}
