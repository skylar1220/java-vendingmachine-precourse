package vendingmachine.view.validator;

import vendingmachine.common.Symbol;

public class InputValidator {
    private static InputValidator inputValidator;
    public static final String SEPARATOR = Symbol.COMMA;

    private InputValidator() {
    }

    public static InputValidator getInstance() {
        if (inputValidator == null) {
            return new InputValidator();
        }
        return inputValidator;
    }

//    public static void validateNumber(String template, String target) {
//        StringValidator.validateBlank(template, target);
//        StringValidator.validateNumeric(template, target);
//        StringValidator.validateIntegerRange(template, target);
//    }
//
//    public void validatList(String template, String target) {
//        StringValidator.validateBlank(template, target);
//        GeneralValidator.validateDuplicateSubstring(SEPARATOR, template, target);
//        GeneralValidator.validateStartSubstring(SEPARATOR, template, target);
//        GeneralValidator.validateEndSubstring(SEPARATOR, template, target);
//        GeneralValidator.validateSplittedCount(SEPARATOR, template, 2, target);
//    }
//
//    public void validateRawList(String raw, String target) {
//        validateFormat(SEPARATOR, raw, target);
//        List<String> list = Converter.splitToList(SEPARATOR, raw);
//        list.forEach(value -> validateEachFormat(VALUE_SEPARATOR, value, target));
//        list.forEach(value -> validateEachValue(VALUE_SEPARATOR, value, target));
//    }
}
