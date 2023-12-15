package vendingmachine.view;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import vendingmachine.domain.CoinStorage;
import vendingmachine.domain.MoneyInserted;
import vendingmachine.domain.ProductName;
import vendingmachine.domain.Products;
import vendingmachine.util.converter.Converter;
import vendingmachine.view.printer.Printer;
import vendingmachine.view.reader.Reader;
import vendingmachine.view.validator.InputValidator;

public class InputView {
    private final Reader reader;
    private final Printer printer;
    private final InputValidator validator;

    private InputView(Reader reader, Printer printer, InputValidator validator) {
        this.reader = reader;
        this.printer = printer;
        this.validator = validator;
    }

    public static InputView of(Reader reader, Printer printer) {
        return new InputView(reader, printer, InputValidator.getInstance());
    }

    public CoinStorage inputCointStorage() {
        printer.printLine("자판기가 보유하고 있는 금액을 입력해 주세요.");
        String inputMoney = reader.readLine();
        validator.validateCointStorage(inputMoney, "자판기 보유 금액");
        return CoinStorage.fromMoney(Converter.convertToInt(inputMoney));
    }

    public Products inputProducts() {
        printer.printLine("상품명과 가격, 수량을 입력해 주세요.");
        String rawProducts = reader.readLine();
        validator.validateProducts(rawProducts, "상품명과 가격, 수량");
        List<String> products = Arrays.stream(rawProducts.split(";"))
                .map(String::trim)
                .map(product -> product.substring(1, product.length() - 1))
                .collect(Collectors.toList());
        return Products.of(products);
    }

    public MoneyInserted inputMoneyInserted() {
        printer.printLine("투입 금액을 입력해 주세요.");
        String moneyInserted = reader.readLine();
        validator.validateMoneyInserted(moneyInserted, "투입 금액");
        return MoneyInserted.from(Converter.convertToInt(moneyInserted));
    }

    public ProductName inputSelectedProduct() {
        printer.printLine("구매할 상품명을 입력해 주세요.");
        String selectedProduct = reader.readLine();
        validator.validateSelectedProduct(selectedProduct, "구매할 상품명");
        return new ProductName(selectedProduct);
    }
}
