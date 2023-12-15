package vendingmachine.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Products {
    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    public static Products of(List<String> rawProducts) {
        List<Product> products = rawProducts.stream()
                .map(Product::from)
                .collect(Collectors.toList());
        return new Products(products);
    }

    public boolean isEmpty() {
        return products.stream()
                .allMatch(Product::isSoldOut);
    }

    public boolean hasProductofPriceOver(MoneyInserted moneyInserted) {
        return products.stream()
                .filter(product -> !product.isSoldOut())
                .anyMatch(product -> product.isPriceUnderOrEqual(moneyInserted));
    }

    public void checkAvailableProduct(ProductName productName) {
        if (!isRegisteredProduct(productName)) {
            throw new IllegalArgumentException("등록되지 않은 상품입니다.");
        }
        if (isSoldOutProduct(productName)) {
            throw new IllegalArgumentException("품절 상품입니다.");
        }
    }

    private boolean isSoldOutProduct(ProductName productName) {
        return products.stream()
                .filter(product -> product.isSameName(productName))
                .allMatch(Product::isSoldOut);
    }

    private boolean isRegisteredProduct(ProductName productName) {
        return products.stream()
                .anyMatch(product -> product.isSameName(productName));
    }
}
