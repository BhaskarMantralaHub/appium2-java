# Wait Utilities

This package provides utilities for handling waits in Appium tests.

## WaitUtils

The `WaitUtils` class provides methods for explicit and fluent waits, making it easy to wait for elements or conditions in your tests.

### Features

- **Explicit waits**: Wait for specific conditions with a timeout
- **Fluent waits**: Wait with custom polling intervals and ignored exceptions
- **Element waits**: Wait for elements to be visible, clickable, or present
- **Custom condition waits**: Wait for custom conditions
- **Driver readiness waits**: Wait for a driver to be ready after creation

### Usage Examples

#### Waiting for Elements

```java
// Wait for an element to be visible
WebElement element = WaitUtils.waitForElementVisible(driver, By.id("elementId"));

// Wait for an element to be clickable
WebElement button = WaitUtils.waitForElementClickable(driver, By.id("buttonId"));

// Wait for an element to be present in the DOM
WebElement element = WaitUtils.waitForElementPresent(driver, By.id("elementId"));
```

#### Waiting for Custom Conditions

```java
// Wait for a custom condition
String text = WaitUtils.waitFor(driver, d -> {
    String value = d.findElement(By.id("elementId")).getText();
    return value.contains("expected") ? value : null;
});

// Wait for a custom condition with a custom timeout
Boolean result = WaitUtils.waitFor(driver, d -> {
    return d.findElement(By.id("elementId")).isEnabled();
}, 10);
```

#### Waiting for Driver Readiness

```java
// Wait for the driver to be ready after creation
AndroidDriver driver = new AndroidDriver(new URL(serverUrl), options);
WaitUtils.waitForDriverReady(driver);
```

#### Creating Custom Waits

```java
// Create an explicit wait with the default timeout
WebDriverWait wait = WaitUtils.getWait(driver);

// Create an explicit wait with a custom timeout
WebDriverWait wait = WaitUtils.getWait(driver, 15);

// Create a fluent wait with the default timeout and polling interval
Wait<WebDriver> wait = WaitUtils.getFluentWait(driver);

// Create a fluent wait with custom timeout and polling interval
Wait<WebDriver> wait = WaitUtils.getFluentWait(driver, 15, 200);
```

### Configuration

The wait utilities are configured through the `WaitConfig` class, which reads configuration values from properties files. You can customize the following settings:

- `wait.default.timeout`: Default timeout for waits in seconds (default: 30)
- `wait.default.polling`: Default polling interval for waits in milliseconds (default: 500)
- `wait.driver.readiness.timeout`: Timeout for driver readiness check in seconds (default: 10)
- `wait.driver.readiness.polling`: Polling interval for driver readiness check in milliseconds (default: 500)
- `wait.element.visibility.timeout`: Timeout for element visibility in seconds (default: 15)
- `wait.element.clickability.timeout`: Timeout for element clickability in seconds (default: 15)

You can override these settings in your `application.properties` file or through environment variables.
