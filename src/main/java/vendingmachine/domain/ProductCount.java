package vendingmachine.domain;

public class ProductCount {
    private int productCount;

    public ProductCount(int productCount) {
        validateRange(productCount);
        this.productCount = productCount;
    }

    private void validateRange(int productPrice) {
        if (!isInRange(productPrice)) {
            throw new IllegalArgumentException("수량은 1 이상이어야합니다.");
        }
    }

    private boolean isInRange(int productPrice) {
        return productPrice >= 1;
    }

    public boolean isEmpty() {
        return productCount == 0;
    }

    public void decrease() {
        productCount--;
    }
}
