package io.github.uzumaki232323.nepalinumber;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NepaliNumberTest {

    @Test
    void testSingleDigits() {
        assertEquals("शून्य", NepaliNumber.of(0).toWords());
        assertEquals("पाँच", NepaliNumber.of(5).toWords());
    }

    @Test
    void testTensAndHundreds() {
        assertEquals("बाह्र", NepaliNumber.of(12).toWords());
        assertEquals("उन्नाइस", NepaliNumber.of(19).toWords());
        assertEquals("बयालीस", NepaliNumber.of(42).toWords());
        assertEquals("एघार", NepaliNumber.of(11).toWords());
        assertEquals("एक सय बीस", NepaliNumber.of(120).toWords());
        assertEquals("नौ सय उनान्सय", NepaliNumber.of(999).toWords());
    }

    @Test
    void testThousandsAndLakhs() {
        assertEquals("एक हजार", NepaliNumber.of(1000).toWords());
        assertEquals("एक हजार दुई सय छियानब्बे", NepaliNumber.of(1296).toWords());
        assertEquals("दुई लाख बाउन्न हजार", NepaliNumber.of(252000).toWords());
        assertEquals("एघार लाख एघार हजार एक सय एघार", NepaliNumber.of(1111111).toWords());
    }

    @Test
    void testCroresAndAbove() {
        assertEquals("एक करोड", NepaliNumber.of(10000000).toWords());
        assertEquals("पन्ध्र करोड बीस लाख एकाउन्न हजार तीन", 
                NepaliNumber.of(152051003).toWords());
    }

    @Test
    void testLargeNumbersWords() {
        assertEquals("पन्ध्र करोड बीस लाख एकाउन्न हजार तीन", NepaliNumber.of(152051003).toWords());
        assertEquals("एक अरब दुई करोड तीन लाख चार हजार पाँच सय छ", NepaliNumber.of(1020304506L).toWords());
        assertEquals("दुई खरब पैंतालीस अरब सतहत्तर करोड उनान्नब्बे लाख बाह्र हजार", NepaliNumber.of(245778912000L).toWords());
    }
    
    @Test
    void testDecimalsToWords() {
        assertEquals("एक दशमलब दुई पाँच", NepaliNumber.of(1.25).toWords());
        assertEquals("शून्य दशमलब पाँच शून्य", NepaliNumber.of("0.50").toWords());
        assertEquals("शून्य दशमलब नौ नौ", NepaliNumber.of(0.99).toWords());
    }

    @Test
    void testCurrency() {
        assertEquals("एक रुपैयाँ पच्चीस पैसा", NepaliNumber.of(1.25).toCurrency());
        assertEquals("पचास पैसा", NepaliNumber.of(0.50).toCurrency());
        assertEquals("शून्य रुपैयाँ", NepaliNumber.of(0).toCurrency());
        assertEquals("बाह्र हजार तीन सय रुपैयाँ पच्चीस पैसा", NepaliNumber.of(12300.25).toCurrency());
        assertEquals("एक रुपैयाँ पचास पैसा", NepaliNumber.of(1.5).toCurrency());
        assertEquals("एक रुपैयाँ एक पैसा", NepaliNumber.of(1.01).toCurrency());
        assertEquals("एक रुपैयाँ दश पैसा", NepaliNumber.of(1.10).toCurrency());
        assertEquals("एक रुपैयाँ नब्बे पैसा", NepaliNumber.of(1.90).toCurrency());
        assertEquals("एक रुपैयाँ", NepaliNumber.of(1).toCurrency());
    }

    @Test
    void testCurrencyRounding() {
        // 1.255 -> 1.26
        assertEquals("एक रुपैयाँ छब्बीस पैसा", NepaliNumber.of(1.255).toCurrency());
        // 1.254 -> 1.25
        assertEquals("एक रुपैयाँ पच्चीस पैसा", NepaliNumber.of(1.254).toCurrency());
    }

    @Test
    void testNegativeNumbers() {
        assertEquals("माइनस पाँच", NepaliNumber.of(-5).toWords());
        assertEquals("माइनस एक दशमलब पाँच", NepaliNumber.of(-1.5).toWords());
        assertEquals("माइनस एक रुपैयाँ पचास पैसा", NepaliNumber.of(-1.5).toCurrency());
    }

    @Test
    void testStringInputForLosslessPrecision() {
        assertEquals("एक खरब", NepaliNumber.of("100000000000").toWords());
        assertEquals("नौ खरब उनान्सय अरब उनान्सय करोड उनान्सय लाख उनान्सय हजार नौ सय उनान्सय", NepaliNumber.of("999999999999").toWords());
    }
}
