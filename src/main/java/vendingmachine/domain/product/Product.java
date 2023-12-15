package vendingmachine.domain.product;

import vendingmachine.domain.MoneyInserted;
import vendingmachine.util.converter.Converter;
import vendingmachine.view.validator.InputValidator;

public class Product {
    private final ProductName productName;
    private final ProductPrice productPrice;
    private final ProductCount productCount;

    public Product(ProductName productName, ProductPrice productPrice, ProductCount productCount) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCount = productCount;
    }

    public static Product from(String product) {
        String productName = Converter.splitValue(InputValidator.PRODUCT_DETAILS_SEPARATOR, 0, product);
        String productPrice = Converter.splitValue(InputValidator.PRODUCT_DETAILS_SEPARATOR, 1, product);
        String productCount = Converter.splitValue(InputValidator.PRODUCT_DETAILS_SEPARATOR, 2, product);
        return new Product(new ProductName(productName), new ProductPrice(Converter.convertToInt(productPrice)),
                new ProductCount(Converter.convertToInt(productCount)));
    }

    public boolean isSoldOut() {
        return productCount.isEmpty();
    }

    public boolean isPriceUnderOrEqual(MoneyInserted moneyInserted) {
        return productPrice.isUnderOrEqual(moneyInserted);
    }

    public boolean isSameName(ProductName productName) {
        return this.productName.equals(productName);
    }


    public void sold() {
        productCount.decrease();
    }

    public int calculateMoneyAfterBuying(int moneyInserted) {
        return productPrice.calculateMoneyAfterBuying(moneyInserted);
    }
}
