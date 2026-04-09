package io.github.uzumaki232323.nepalinumber;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

class NepaliNumberConverter {

    private static final String[] NUMBERS = {
            "शून्य", "एक", "दुई", "तीन", "चार", "पाँच", "छ", "सात", "आठ", "नौ",
            "दश", "एघार", "बाह्र", "तेह्र", "चौध", "पन्ध्र", "सोह्र", "सत्र", "अठार", "उन्नाइस",
            "बीस", "एक्काइस", "बाइस", "तेइस", "चौबीस", "पच्चीस", "छब्बीस", "सत्ताइस", "अट्ठाइस", "उनन्तीस",
            "तीस", "एकतीस", "बत्तीस", "तेत्तीस", "चौंतीस", "पैंतीस", "छत्तीस", "सैंतीस", "अठतीस", "उनन्चालीस",
            "चालीस", "एकचालीस", "बयालीस", "त्रिचालीस", "चवालीस", "पैंतालीस", "छयालीस", "सतचालीस", "अठचालीस", "उनन्चास",
            "पचास", "एकाउन्न", "बाउन्न", "त्रिपन्न", "चौवन्न", "पचपन्न", "छपन्न", "सन्ताउन्न", "अन्ठाउन्न", "उनान्साठी",
            "साठी", "एकसठ्ठी", "बैसठ्ठी", "त्रिसठ्ठी", "चौंसठ्ठी", "पैंसठ्ठी", "छैसठ्ठी", "सतसठ्ठी", "अठसठ्ठी", "उनान्सत्तरी",
            "सत्तरी", "एकहत्तर", "बहत्तर", "त्रिहत्तर", "चौहत्तर", "पचहत्तर", "छयहत्तर", "सतहत्तर", "अठहत्तर", "उनासी",
            "असी", "एकासी", "बयासी", "त्रियासी", "चौरासी", "पचासी", "छयासी", "सतासी", "अठासी", "उनान्नब्बे",
            "नब्बे", "एकानब्बे", "बयानब्बे", "त्रियानब्बे", "चौरानब्बे", "पन्चानब्बे", "छियानब्बे", "सन्तानब्बे", "अन्ठानब्बे", "उनान्सय"
    };

    public static String convertToNepaliWords(BigDecimal number) {
        if (number == null) return "";
        BigInteger integerPart = number.toBigInteger();
        BigDecimal fractionalPart = number.subtract(new BigDecimal(integerPart));

        StringBuilder result = new StringBuilder();
        if (integerPart.compareTo(BigInteger.ZERO) == 0) {
            result.append(NUMBERS[0]);
        } else if (integerPart.compareTo(BigInteger.ZERO) < 0) {
            result.append("माइनस ").append(convertWholeNumber(integerPart.negate()));
        } else {
            result.append(convertWholeNumber(integerPart));
        }

        if (fractionalPart.compareTo(BigDecimal.ZERO) != 0) {
            result.append(" दशमलब ");
            String fractionStr = fractionalPart.abs().toPlainString().substring(2); // Remove "0." or "-0."
            for (char c : fractionStr.toCharArray()) {
                int digit = c - '0';
                result.append(NUMBERS[digit]).append(" ");
            }
            result.setLength(result.length() - 1); // remove trailing space
        }

        return result.toString();
    }

    public static String convertToNepaliCurrency(BigDecimal number) {
        if (number == null) return "";
        
        // Use 2 decimal places for Currency
        BigDecimal rounded = number.setScale(2, RoundingMode.HALF_UP);
        BigInteger rupees = rounded.toBigInteger();
        BigDecimal paisaBigDecimal = rounded.subtract(new BigDecimal(rupees)).multiply(new BigDecimal(100));
        int paisa = paisaBigDecimal.abs().intValue();

        StringBuilder result = new StringBuilder();

        if (rupees.compareTo(BigInteger.ZERO) < 0) {
            rupees = rupees.negate();
            result.append("माइनस ");
        }

        if (rupees.compareTo(BigInteger.ZERO) > 0 || paisa == 0) {
            if (rupees.compareTo(BigInteger.ZERO) == 0) {
                result.append(NUMBERS[0]);
            } else {
                result.append(convertWholeNumber(rupees));
            }
            result.append(" रुपैयाँ");
        }

        if (paisa > 0) {
            if (result.length() > 0 && !result.toString().equals("माइनस ")) {
                result.append(" ");
            }
            result.append(NUMBERS[paisa]).append(" पैसा");
        }

        return result.toString();
    }

    private static String convertWholeNumber(BigInteger number) {
        if (number.compareTo(BigInteger.valueOf(99)) <= 0) {
            return NUMBERS[number.intValue()];
        }

        StringBuilder words = new StringBuilder();
        BigInteger current = number;

        BigInteger[] divRem100 = current.divideAndRemainder(BigInteger.valueOf(100));
        int onesAndTens = divRem100[1].intValue();
        current = divRem100[0];

        BigInteger[] divRem10 = current.divideAndRemainder(BigInteger.TEN);
        int hundreds = divRem10[1].intValue();
        current = divRem10[0];

        int thousands = 0, lakhs = 0, crores = 0, arabs = 0, kharabs = 0, neels = 0, padmas = 0, shankhas = 0;

        divRem100 = current.divideAndRemainder(BigInteger.valueOf(100));
        thousands = divRem100[1].intValue();
        current = divRem100[0];

        divRem100 = current.divideAndRemainder(BigInteger.valueOf(100));
        lakhs = divRem100[1].intValue();
        current = divRem100[0];

        divRem100 = current.divideAndRemainder(BigInteger.valueOf(100));
        crores = divRem100[1].intValue();
        current = divRem100[0];

        divRem100 = current.divideAndRemainder(BigInteger.valueOf(100));
        arabs = divRem100[1].intValue();
        current = divRem100[0];

        divRem100 = current.divideAndRemainder(BigInteger.valueOf(100));
        kharabs = divRem100[1].intValue();
        current = divRem100[0];

        divRem100 = current.divideAndRemainder(BigInteger.valueOf(100));
        neels = divRem100[1].intValue();
        current = divRem100[0];

        divRem100 = current.divideAndRemainder(BigInteger.valueOf(100));
        padmas = divRem100[1].intValue();
        current = divRem100[0];

        divRem100 = current.divideAndRemainder(BigInteger.valueOf(100));
        shankhas = divRem100[1].intValue();
        current = divRem100[0];

        if (shankhas > 0) appendGroup(words, shankhas, "शंख");
        if (padmas > 0) appendGroup(words, padmas, "पद्म");
        if (neels > 0) appendGroup(words, neels, "नील");
        if (kharabs > 0) appendGroup(words, kharabs, "खरब");
        if (arabs > 0) appendGroup(words, arabs, "अरब");
        if (crores > 0) appendGroup(words, crores, "करोड");
        if (lakhs > 0) appendGroup(words, lakhs, "लाख");
        if (thousands > 0) appendGroup(words, thousands, "हजार");
        if (hundreds > 0) appendGroup(words, hundreds, "सय");

        if (onesAndTens > 0) {
            if (words.length() > 0) words.append(" ");
            words.append(NUMBERS[onesAndTens]);
        }

        return words.toString();
    }

    private static void appendGroup(StringBuilder sb, int value, String unit) {
        if (sb.length() > 0) {
            sb.append(" ");
        }
        sb.append(NUMBERS[value]).append(" ").append(unit);
    }
}
