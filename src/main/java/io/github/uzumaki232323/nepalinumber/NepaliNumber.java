package io.github.uzumaki232323.nepalinumber;

import java.math.BigDecimal;

/**
 * A fluent API to convert numeric values to Nepali Unicode words and Currency.
 * 
 * Usage:
 * String words = NepaliNumber.of(1234.50).toWords();
 * String currency = NepaliNumber.of(1234.50).toCurrency();
 */
public class NepaliNumber {

    private final BigDecimal value;

    private NepaliNumber(BigDecimal value) {
        this.value = value;
    }

    /**
     * Initializes the converter with a long value.
     */
    public static NepaliNumber of(long value) {
        return new NepaliNumber(BigDecimal.valueOf(value));
    }

    /**
     * Initializes the converter with a double value.
     */
    public static NepaliNumber of(double value) {
        return new NepaliNumber(BigDecimal.valueOf(value));
    }

    /**
     * Initializes the converter with a String value.
     * Helpful to prevent precision loss for very large numbers or high-precision decimals.
     */
    public static NepaliNumber of(String value) {
        return new NepaliNumber(new BigDecimal(value));
    }

    /**
     * Initializes the converter with a BigDecimal value.
     */
    public static NepaliNumber of(BigDecimal value) {
        return new NepaliNumber(value);
    }

    /**
     * Converts the initialized numeric value to Nepali words.
     * Example: 12.34 forms "बाह्र दशमलब तीन चार"
     * 
     * @return equivalent Nepali unicode string for the number.
     */
    public String toWords() {
        return NepaliNumberConverter.convertToNepaliWords(value);
    }

    /**
     * Converts the initialized numeric value to Nepali currency (Rupees and Paisa).
     * Example: 12.34 forms "बाह्र रुपैयाँ चौंतीस पैसा"
     * Note: Decimal parts are rounded up to exactly 2 digits representing Paisa.
     * 
     * @return equivalent Nepali unicode string for standard currency format.
     */
    public String toCurrency() {
        return NepaliNumberConverter.convertToNepaliCurrency(value);
    }
    
    // Quick demonstration method so you can run this file directly and print to your console
    public static void main(String[] args) {
        System.out.println("---------- NEPALI NUMBER CONVERTER ----------");
        System.out.println("Digits 12345 to words     : " + NepaliNumber.of(12345).toWords());
        System.out.println("Decimal 1500.50 to words  : " + NepaliNumber.of(1500.50).toWords());
        System.out.println("Currency 1234.50          : " + NepaliNumber.of(1234.50).toCurrency());
        System.out.println("Huge number 999999999     : " + NepaliNumber.of("999999999").toWords());
        System.out.println("---------------------------------------------");
    }
}
