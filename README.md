# UVATester 🍇
![GitHub License](https://img.shields.io/github/license/foiovituh/uva-tester)

![logo](https://github.com/foiovituh/uva-tester/assets/68431603/869607ca-68bc-4630-9a9a-4d4a0794dd82)


A minimalist Java library for effortless unit test execution. Although functional, my main goal was to put my studies on annotation and reflection in Java into practice. It was also a fun challenge to develop a test library from scratch and without references.

## Summary 📝
- [UVATester 🍇](#uvatester-)
  - [Summary 📝](#summary-)
  - [How does it work? 💡](#how-does-it-work-)
  - [Requirements 🔗](#requirements-)
  - [Quick usage guide 📚](#quick-usage-guide-)
      - [Installation:](#installation)
      - [Mapping process:](#mapping-process)
      - [Execution:](#execution)
      - [Assertor:](#assertor)
  - [Future plans 📌](#future-plans-)
  - [Do you want help me? 👥](#do-you-want-help-me-)
  - [License 🏳️](#license-️)

## How does it work? 💡
UVATester's main process can be summarized as follows:
- Receiving the name of a test class, loading it dynamically via reflection.
- Checking that the class has the `@TestClass` annotation.
- Invoking each method in the class that has the `@TestMethod` annotation and follows certain criteria (for more details, see the "Mapping process" section).
- If any problems occur during the execution of the tests, the details of the error will be logged and then the UVATester will be terminated.

## Requirements 🔗
<b>Mandatory</b>:
- Java 17+
- Maven 4.0.0+

<b>Optional</b>:
- JitPack

## Quick usage guide 📚
#### Installation:
Add and download the following dependencies to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.foiovituh</groupId>
        <artifactId>uva-tester</artifactId>
        <version>v1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

#### Mapping process:
Annotate your test class with `@TestClass`, and annotate your test methods with `@TestMethod`. The test methods should be static, return void, and have no arguments. For example:

```java
@TestClass
public class ShoppingTrolleyTest {
    @TestMethod
    public static void testEmptyShoppingTrolleyGuardingReturnsTrue() {
    	final var shoppingTrolley = new ShoppingTrolley();
    	
    	final var testName = "testEmptyShoppingTrolleyGuardingReturnsTrue";
        final var testResult = shoppingTrolley.guardShoppingTrolley()
        		&& Assertor.isNullOrEmpty(shoppingTrolley.getItems())
        			? "pass"
        			: "failed";
        
        Feedback.showResult(testName, testResult);
    }
}
```

---

#### Execution:
Instantiate a `UVATester` object using the test class name and invoke its `run()` method to execute all defined tests:
```java
public class ShoppingTrolleyTestExecutor {
    public static void main(String[] args) {
        final var shoppingTrolleyTests = new UVATester(
                ShoppingTrolleyTest.class.getName()
        );
        
        shoppingTrolleyTests.run();
    }
}
```

#### Assertor:
You can also use assertion methods that can be useful when developing tests. They are available in the class: `com.github.foiovituh.uvatester.utils.Assertor`. For example, the method:
```java
public static boolean isWithinRange(int value, int min, int max) {
    return value >= min && value <= max;
}
```

## Future plans 📌
- Add more methods to the Assertor class
- Make it optional to close tests if one fails

## Do you want help me? 👥
If you have any ideas or wish to contribute to the project, contact me on X (<a href="https://x.com/ohtoaki" target="_blank">@ohtoaki</a>) or send me a pull request :)

## License 🏳️
```
MIT License

Copyright (c) 2024 Vitu Ohto

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
