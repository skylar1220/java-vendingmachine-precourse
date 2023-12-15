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
}
