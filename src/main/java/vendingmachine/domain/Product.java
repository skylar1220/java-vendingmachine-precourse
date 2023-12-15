package vendingmachine.domain;

import vendingmachine.util.converter.Converter;
import vendingmachine.view.validator.InputValidator;

public class Product {
    private final String productName;
    private final ProductPrice productPrice;
    private final ProductCount productCount;

    public Product(String productName, ProductPrice productPrice, ProductCount productCount) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCount = productCount;
    }

    public static Product from(String product) {
        String productName = Converter.splitValue(InputValidator.PRODUCT_DETAILS_SEPARATOR, 0, product);
        String productPrice = Converter.splitValue(InputValidator.PRODUCT_DETAILS_SEPARATOR, 1, product);
        String productCount = Converter.splitValue(InputValidator.PRODUCT_DETAILS_SEPARATOR, 2, product);
        return new Product(productName, new ProductPrice(Converter.convertToInt(productPrice)), new ProductCount(Converter.convertToInt(productCount)));
    }
}
