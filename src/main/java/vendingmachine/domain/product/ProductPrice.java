package vendingmachine.domain.product;

import vendingmachine.domain.MoneyInserted;

public class ProductPrice {
    public static final int MIN_PRODUCT_PRICE = 100;
    public static final int PRODUCT_PRICE_UNIT = 10;
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
        return productPrice >= MIN_PRODUCT_PRICE;
    }

    private void validateUnit(int productPrice) {
        if (!isValidUnit(productPrice)) {
            throw new IllegalArgumentException("가격은 최소 10원 단위이어야 합니다.");
        }
    }

    private boolean isValidUnit(int productPrice) {
        return productPrice % PRODUCT_PRICE_UNIT == 0;
    }

    public boolean isUnderOrEqual(MoneyInserted moneyInserted) {
        return moneyInserted.isOverOrEqual(productPrice);
    }

    public int calculateMoneyAfterBuying(int moneyInserted) {
        return moneyInserted - productPrice;
    }
}
