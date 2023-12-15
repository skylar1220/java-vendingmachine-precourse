package vendingmachine.view.validator;

import java.util.List;
import vendingmachine.common.Symbol;
import vendingmachine.util.converter.Converter;
import vendingmachine.util.validator.GeneralValidator;
import vendingmachine.util.validator.StringValidator;

public class InputValidator {
    public static final String PRODUCT_DETAILS_SEPARATOR = Symbol.COMMA;
    public static final String PRODUCT_SEPARATOR = Symbol.SEMI_COLON;
    private static InputValidator inputValidator;

    private InputValidator() {
    }

    public static InputValidator getInstance() {
        if (inputValidator == null) {
            return new InputValidator();
        }
        return inputValidator;
    }

    public void validateCointStorage(String inputMoney, String target) {
        StringValidator.validateBlank(inputMoney, target);
        StringValidator.validateNumeric(inputMoney, target);
        StringValidator.validateIntegerRange(inputMoney, target);
    }

    public void validateProducts(String rawProducts, String target) {
        validateFormat(PRODUCT_SEPARATOR, rawProducts, target);
        List<String> products = Converter.splitToList(PRODUCT_SEPARATOR, rawProducts);
        products.forEach(product -> validateProductFormat(PRODUCT_DETAILS_SEPARATOR, product, target));
        products.forEach(product -> validateProductName(PRODUCT_DETAILS_SEPARATOR, product, target));
        products.forEach(product -> validateProductPrice(PRODUCT_DETAILS_SEPARATOR, product, target));
        products.forEach(product -> validateProductCount(PRODUCT_DETAILS_SEPARATOR, product, target));
    }

    private void validateProductName(String separator, String product, String target) {
        String productName = Converter.splitValue(separator, 0, product);
        StringValidator.validateBlank(productName, target);
    }

    private void validateProductPrice(String separator, String product, String target) {
        String productPrice = Converter.splitValue(separator, 1, product);
        StringValidator.validateBlank(productPrice, target);
        StringValidator.validateNumeric(productPrice, target);
        StringValidator.validateIntegerRange(productPrice, target);
    }

    private void validateProductCount(String separator, String product, String target) {
        String productCount = Converter.splitValue(separator, 2, product);
        StringValidator.validateBlank(productCount, target);
        StringValidator.validateNumeric(productCount, target);
        StringValidator.validateIntegerRange(productCount, target);
    }

    private void validateFormat(String separator, String products, String target) {
        StringValidator.validateBlank(products, target);
        GeneralValidator.validateSingleSeparator(separator, products, target);
        GeneralValidator.validateNotStartsWith(separator, products, target);
        GeneralValidator.validateNotEndsWith(separator, products, target);
    }

    private void validateProductFormat(String separator, String rawProduct, String target) {
        StringValidator.validateBlank(rawProduct, target);
        validateBracket(rawProduct, "각 상품명, 가격, 수량");
        String product = rawProduct.substring(1, rawProduct.length() - 1);
        GeneralValidator.validateSingleSeparator(separator, product, target);
        GeneralValidator.validateNotStartsWith(separator, product, target);
        GeneralValidator.validateNotEndsWith(separator, product, target);
        GeneralValidator.validateSplitCount(separator, product, 3, target);
    }

    private void validateBracket(String rawProduct, String target) {
        GeneralValidator.validateStartsWith(Symbol.LEFT_BRACKET, rawProduct, target);
        GeneralValidator.validateEndsWith(Symbol.RIGHT_BRACKET, rawProduct, target);
    }

    public void validateMoneyInserted(String moneyInserted, String target) {
        StringValidator.validateBlank(moneyInserted, target);
        StringValidator.validateNumeric(moneyInserted, target);
        StringValidator.validateIntegerRange(moneyInserted, target);
    }

    public void validateSelectedProduct(String selectedProduct, String target) {
        StringValidator.validateBlank(selectedProduct, target);
    }

//    public static void validateNumber(String template, String target) {
//        StringValidator.validateBlank(template, target);
//        StringValidator.validateNumeric(template, target);
//        StringValidator.validateIntegerRange(template, target);
//    }
//
//    public void validatList(String template, String target) {
//        StringValidator.validateBlank(template, target);
//        GeneralValidator.validateSingleSeparator(SEPARATOR, template, target);
//        GeneralValidator.validateStartsWith(SEPARATOR, template, target);
//        GeneralValidator.validateEndsWith(SEPARATOR, template, target);
//        GeneralValidator.validateSplitCount(SEPARATOR, template, 2, target);
//    }
//
//    public void validateRawList(String raw, String target) {
//        validateFormat(SEPARATOR, raw, target);
//        List<String> list = Converter.splitToList(SEPARATOR, raw);
//        list.forEach(value -> validateEachFormat(VALUE_SEPARATOR, value, target));
//        list.forEach(value -> validateEachValue(VALUE_SEPARATOR, value, target));
//    }
}
