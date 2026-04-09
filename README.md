# 🍜 Nepali Number Converter (Dattebayo!)

A Hokage-level, lightweight, fluent Java 17 library for converting numeric values into Nepali Unicode strings and Standard Nepali Currency formats. 

No more hand-weaving seals to translate numbers! If you're building software for Nepal and want to handle massive digits, this library has enough chakra to handle limit-breaking numbers without breaking a sweat. Believe it!

## 🍥 Features
- **Numbers to Words Jutsu**: Accurately maps integers, long scalars, and extreme bounds (like *Shankha*, *Padma*, *Neel*, *Arabs*) straight into robust Nepali strings. It summons the correct word every time!
- **Decimal Support**: Elegantly shifts fractions (e.g. 1.25) to representations like "एक दशमलब दुई पाँच".
- **Currency Support**: Formats numbers neatly into Rupees and Paisa faster than TenTen throws kunais.
- **Fluent API**: Initialize flexibly with Strings, Longs, Doubles, or BigDecimals avoiding precision loss via the `NepaliNumber` construct.
- **Lossless Size Bounds (Sage Mode)**: Accepts standard Java primitives but scales effortlessly for numbers well beyond bounds through raw string initializers. Even the Tailed Beasts can't count this high!

## 📜 Summoning Contract (Installation / Setup)

You can integrate this local project by using some standard Maven jutsu.

1. Ensure [Apache Maven](https://maven.apache.org/) is installed on your machine.
2. Under the project root directory, run the following to install the artifact into your local `.m2` repository:
   ```bash
   mvn clean install
   ```
3. Once built and installed locally, you can use it in your other projects under the following coordinates:
   ```xml
   <dependency>
       <groupId>io.github.uzumaki232323</groupId>
       <artifactId>nepali-number-converter</artifactId>
       <version>1.0.0</version>
   </dependency>
   ```

## 🥷 Usage Example

```java
import io.github.uzumaki232323.nepalinumber.NepaliNumber;

public class Main {
    public static void main(String[] args) {
        // Basic Transformation Jutsu
        String words = NepaliNumber.of(1234).toWords();
        System.out.println("Digits: " + words); // "एक हजार दुई सय चौंतीस"
        
        // Currency Translation Jutsu
        String currency = NepaliNumber.of(1234.50).toCurrency();
        System.out.println("Currency: " + currency); // "एक हजार दुई सय चौंतीस रुपैयाँ पचास पैसा"
        
        // Sage Art: String handling for bounds beyond standard numeric types
        String massive = NepaliNumber.of("10000000000000").toWords();
        System.out.println("Tailed Beast Level: " + massive); // "दश नील"
    }
}
```

## ⚔️ Running the Chunin Exams (Tests)
To put this library through the Chunin Exams (built-in JUnit 5 suite) locally, run:
```bash
mvn test
```

## 🤝 The Shinobi Alliance (Contributing)
Pull requests are welcome. For major changes, please open an issue first to discuss what new Jutsu you would like to invent.

## 👑 The Hokage (Author) 
Developed by **Gaurav Suwal** ([@uzumaki232323](https://github.com/uzumaki232323)) - *Because protecting the bonds with my fellow developers is my nindo, my ninja way!*
