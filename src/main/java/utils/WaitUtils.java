package utils;

import config.PropertyReader;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

/**
 * Utility class for handling waits in Appium tests.
 * Provides methods for fluent waits specifically for Appium drivers.
 */
public class WaitUtils {

    /**
     * Default timeout for waits in seconds.
     */
    private static final int DEFAULT_TIMEOUT = PropertyReader.getIntProperty("wait.default.timeout", 30);

    /**
     * Default polling interval for waits in milliseconds.
     */
    private static final int DEFAULT_POLLING_INTERVAL = PropertyReader.getIntProperty("wait.default.polling", 500);

    /**
     * Element visibility timeout in seconds.
     */
    private static final int ELEMENT_VISIBILITY_TIMEOUT
            = PropertyReader.getIntProperty("wait.element.visibility.timeout", 15);

    /**
     * Element clickability timeout in seconds.
     */
    private static final int ELEMENT_CLICKABILITY_TIMEOUT
            = PropertyReader.getIntProperty("wait.element.clickability.timeout", 15);

    /**
     * Driver readiness timeout in seconds.
     */
    private static final int DRIVER_READINESS_TIMEOUT
            = PropertyReader.getIntProperty("wait.driver.readiness.timeout", 10);

    /**
     * Driver readiness polling interval in milliseconds.
     */
    private static final int DRIVER_READINESS_POLLING_INTERVAL
            = PropertyReader.getIntProperty("wait.driver.readiness.polling", 500);

    /**
     * Creates a fluent wait with the default timeout and polling interval.
     *
     * @return a FluentWait instance
     */
    public static <T> FluentWait<T> createFluentWait(T input) {
        return createFluentWait(input, DEFAULT_TIMEOUT, DEFAULT_POLLING_INTERVAL);
    }

    /**
     * Creates a fluent wait with custom timeout and polling interval.
     *
     * @param input               the input object to wait on
     * @param timeoutInSeconds    the timeout in seconds
     * @param pollingIntervalInMs the polling interval in milliseconds
     * @return a FluentWait instance
     */
    public static <T> FluentWait<T> createFluentWait(T input, int timeoutInSeconds, int pollingIntervalInMs) {
        return new FluentWait<>(input)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingIntervalInMs))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    /**
     * Waits for an element to be visible.
     *
     * @param driver  the AppiumDriver instance
     * @param locator the element locator
     * @return the WebElement once it is visible
     */
    public static WebElement waitForElementVisible(AppiumDriver driver, By locator) {
        return createFluentWait(driver, ELEMENT_VISIBILITY_TIMEOUT, DEFAULT_POLLING_INTERVAL)
                .until(d -> {
                    try {
                        WebElement element = d.findElement(locator);
                        if (element.isDisplayed()) {
                            return element;
                        }
                        return null;
                    } catch (Exception e) {
                        return null;
                    }
                });
    }

    /**
     * Waits for an element to be clickable.
     *
     * @param driver  the AppiumDriver instance
     * @param locator the element locator
     * @return the WebElement once it is clickable
     */
    public static WebElement waitForElementClickable(AppiumDriver driver, By locator) {
        return createFluentWait(driver, ELEMENT_CLICKABILITY_TIMEOUT, DEFAULT_POLLING_INTERVAL)
                .until(d -> {
                    try {
                        WebElement element = d.findElement(locator);
                        if (element.isDisplayed() && element.isEnabled()) {
                            return element;
                        }
                        return null;
                    } catch (Exception e) {
                        return null;
                    }
                });
    }

    /**
     * Waits for an element to be present in the DOM.
     *
     * @param driver  the AppiumDriver instance
     * @param locator the element locator
     * @return the WebElement once it is present
     */
    public static WebElement waitForElementPresent(AppiumDriver driver, By locator) {
        return createFluentWait(driver)
                .until(d -> {
                    try {
                        return d.findElement(locator);
                    } catch (Exception e) {
                        return null;
                    }
                });
    }

    /**
     * Waits for a custom condition.
     *
     * @param driver    the AppiumDriver instance
     * @param condition the condition to wait for
     * @param <T>       the return type of the condition
     * @return the result of the condition
     */
    public static <T> T waitFor(AppiumDriver driver, Function<AppiumDriver, T> condition) {
        return createFluentWait(driver).until(condition);
    }

    /**
     * Waits for a custom condition with a custom timeout.
     *
     * @param driver           the AppiumDriver instance
     * @param condition        the condition to wait for
     * @param timeoutInSeconds the timeout in seconds
     * @param <T>              the return type of the condition
     * @return the result of the condition
     */
    public static <T> T waitFor(AppiumDriver driver, Function<AppiumDriver, T> condition, int timeoutInSeconds) {
        return createFluentWait(driver, timeoutInSeconds, DEFAULT_POLLING_INTERVAL).until(condition);
    }

    /**
     * Waits for the driver to be ready.
     * This is useful after creating a new driver instance.
     *
     * @param driver the AppiumDriver instance
     */
    public static void waitForDriverReady(AppiumDriver driver) {
        try {
            // Wait for the driver to be ready by checking if it can execute a simple command
            createFluentWait(driver, DRIVER_READINESS_TIMEOUT, DRIVER_READINESS_POLLING_INTERVAL)
                    .until(d -> {
                        try {
                            d.getPageSource();
                            return true;
                        } catch (Exception e) {
                            return false;
                        }
                    });
        } catch (Exception e) {
            System.out.println("Warning: Driver readiness check failed: " + e.getMessage());
        }
    }

    /**
     * Waits for an element to be visible and returns it.
     *
     * @param driver  the AppiumDriver instance
     * @param element the WebElement to wait for
     * @return the WebElement once it is visible
     */
    public static WebElement waitForElementVisible(AppiumDriver driver, WebElement element) {
        return createFluentWait(driver, ELEMENT_VISIBILITY_TIMEOUT, DEFAULT_POLLING_INTERVAL)
                .until(d -> {
                    try {
                        if (element.isDisplayed()) {
                            return element;
                        }
                        return null;
                    } catch (Exception e) {
                        return null;
                    }
                });
    }

    /**
     * Waits for an element to be clickable and returns it.
     *
     * @param driver  the AppiumDriver instance
     * @param element the WebElement to wait for
     * @return the WebElement once it is clickable
     */
    public static WebElement waitForElementClickable(AppiumDriver driver, WebElement element) {
        return createFluentWait(driver, ELEMENT_CLICKABILITY_TIMEOUT, DEFAULT_POLLING_INTERVAL)
                .until(d -> {
                    try {
                        if (element.isDisplayed() && element.isEnabled()) {
                            return element;
                        }
                        return null;
                    } catch (Exception e) {
                        return null;
                    }
                });
    }

    /**
     * Waits for an element to contain specific text.
     *
     * @param driver  the AppiumDriver instance
     * @param locator the element locator
     * @param text    the text to wait for
     * @return the WebElement once it contains the specified text
     */
    public static WebElement waitForElementTextContains(AppiumDriver driver, By locator, String text) {
        WebElement element = waitForElementVisible(driver, locator);
        createFluentWait(driver).until(d -> {
            try {
                return element.getText().contains(text);
            } catch (Exception e) {
                return false;
            }
        });
        return element;
    }

    /**
     * Waits for an element to have specific text.
     *
     * @param driver  the AppiumDriver instance
     * @param locator the element locator
     * @param text    the text to wait for
     * @return the WebElement once it has the specified text
     */
    public static WebElement waitForElementTextToBe(AppiumDriver driver, By locator, String text) {
        WebElement element = waitForElementVisible(driver, locator);
        createFluentWait(driver).until(d -> {
            try {
                return element.getText().equals(text);
            } catch (Exception e) {
                return false;
            }
        });
        return element;
    }

    /**
     * Waits for an element to be invisible.
     *
     * @param driver  the AppiumDriver instance
     * @param locator the element locator
     * @return true once the element is invisible
     */
    public static boolean waitForElementInvisible(AppiumDriver driver, By locator) {
        return createFluentWait(driver, ELEMENT_VISIBILITY_TIMEOUT, DEFAULT_POLLING_INTERVAL)
                .until(d -> {
                    try {
                        WebElement element = d.findElement(locator);
                        return !element.isDisplayed();
                    } catch (NoSuchElementException e) {
                        return true; // Element not found, so it's not visible
                    }
                });
    }

    /**
     * Waits for an element to be enabled.
     *
     * @param driver  the AppiumDriver instance
     * @param locator the element locator
     * @return the WebElement once it is enabled
     */
    public static WebElement waitForElementEnabled(AppiumDriver driver, By locator) {
        WebElement element = waitForElementVisible(driver, locator);
        createFluentWait(driver).until(d -> {
            try {
                return element.isEnabled();
            } catch (Exception e) {
                return false;
            }
        });
        return element;
    }

    /**
     * Waits for an element to be selected.
     *
     * @param driver  the AppiumDriver instance
     * @param locator the element locator
     * @return the WebElement once it is selected
     */
    public static WebElement waitForElementSelected(AppiumDriver driver, By locator) {
        WebElement element = waitForElementVisible(driver, locator);
        createFluentWait(driver).until(d -> {
            try {
                return element.isSelected();
            } catch (Exception e) {
                return false;
            }
        });
        return element;
    }

    /**
     * Waits for an element by accessibility ID.
     *
     * @param driver          the AppiumDriver instance
     * @param accessibilityId the accessibility ID
     * @return the WebElement once it is found
     */
    public static WebElement waitForElementByAccessibilityId(AppiumDriver driver, String accessibilityId) {
        return waitForElementVisible(driver, AppiumBy.accessibilityId(accessibilityId));
    }

    /**
     * Waits for an element by ID.
     *
     * @param driver the AppiumDriver instance
     * @param id     the ID
     * @return the WebElement once it is found
     */
    public static WebElement waitForElementById(AppiumDriver driver, String id) {
        return waitForElementVisible(driver, AppiumBy.id(id));
    }

    /**
     * Waits for an element by XPath.
     *
     * @param driver the AppiumDriver instance
     * @param xpath  the XPath
     * @return the WebElement once it is found
     */
    public static WebElement waitForElementByXPath(AppiumDriver driver, String xpath) {
        return waitForElementVisible(driver, AppiumBy.xpath(xpath));
    }

    /**
     * Waits for an element by class name.
     *
     * @param driver    the AppiumDriver instance
     * @param className the class name
     * @return the WebElement once it is found
     */
    public static WebElement waitForElementByClassName(AppiumDriver driver, String className) {
        return waitForElementVisible(driver, AppiumBy.className(className));
    }

    /**
     * Waits for an element by iOS predicate string.
     *
     * @param driver          the AppiumDriver instance
     * @param predicateString the iOS predicate string
     * @return the WebElement once it is found
     */
    public static WebElement waitForElementByIosPredicateString(AppiumDriver driver, String predicateString) {
        return waitForElementVisible(driver, AppiumBy.iOSNsPredicateString(predicateString));
    }

    /**
     * Waits for an element by Android UI Automator.
     *
     * @param driver          the AppiumDriver instance
     * @param uiAutomatorText the Android UI Automator text
     * @return the WebElement once it is found
     */
    public static WebElement waitForElementByAndroidUIAutomator(AppiumDriver driver, String uiAutomatorText) {
        return waitForElementVisible(driver, AppiumBy.androidUIAutomator(uiAutomatorText));
    }

    /**
     * Waits for an element to be present and returns a list of elements.
     *
     * @param driver  the AppiumDriver instance
     * @param locator the element locator
     * @return a list of WebElements once they are present
     */
    public static List<WebElement> waitForElementsPresent(AppiumDriver driver, By locator) {
        return createFluentWait(driver)
                .until(d -> {
                    List<WebElement> elements = d.findElements(locator);
                    return elements.isEmpty() ? null : elements;
                });
    }
}
