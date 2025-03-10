package wrapper;

import Common.common.Log;
import Common.common.Utilities;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static Common.constant.Constant.Timeout;

public class Element {
    //properties
    private By by;

    public Element(String locator) {
        by = By.xpath(locator);
    }

    public final Actions action = new Actions(Driver.getDriver());

    public Element(String path, String key) {
        by = By.xpath(String.format(path, key));
    }

    public Element(String path, String key1, String key2) {
        by = By.xpath(String.format(path, key1, key2));
    }

    //method
    //get
    public WebElement getElement() {
        return Driver.getDriver().findElement(this.by);
    }

    public List<WebElement> getElements() {
        return Driver.getDriver().findElements(this.by);
    }

    public Select getSelectObject() {
        Select select = new Select(getElement());
        return select;
    }

    //is
    public boolean isDisplayed() {
        try {
            return getElement().isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    public boolean isEnable() {
        try {
            return getElement().isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    public boolean isSelected() {
        try {
            return getElement().isSelected();
        } catch (NoSuchElementException e) {
            return false;
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    public String getAttribute(String name) {
        return this.getElement().getAttribute(name);
    }

    public Boolean isSelectAlphabetOrder() {
        waitForElementVisible();
        List<WebElement> lstOption = getSelectObject().getOptions();
        Boolean result = true;
        for (int i = 1; i < lstOption.size(); i++) {
            if (lstOption.get(i).getText().compareToIgnoreCase(lstOption.get(i - 1).getText()) < 0) {
                result = false;
            }
        }
        return result;
    }

    public Boolean isSelectOptionExist(String optionName) {
        waitForElementVisible();
        List<WebElement> lstOption = getSelectObject().getOptions();
        Boolean result = false;
        for (int i = 0; i < lstOption.size(); i++) {
            if (lstOption.get(i).getText().equals(optionName)) {
                result = true;
            }
        }
        return result;
    }

    //gesture
    public void enter(String value) {
        Log.info(String.format("Enter %s into %s", value, getElement().toString()));
        clear();
        type(value);
    }

    public void click() {
        try {
            waitForElementExist();
        } catch (Exception e) {
        }
        Log.info(String.format("Click on %s", getElement().toString()));
        try {
            getElement().click();
        } catch (ElementClickInterceptedException e) {
            waitUntilElementNotCovered();
            getElement().click();
        } catch (ElementNotInteractableException e) {
            waitForElementEnable();
            getElement().click();
        }
    }

    public void type(String value) {
        Log.info(String.format("Type %s into %s", value, getElement().toString()));
        getElement().sendKeys(value);
    }

    public void clear() {
        Log.info(String.format("Clear field %s", getElement().toString()));
        getElement().clear();
    }

    public void setCheckBox(Boolean status) {
        Log.info(String.format("Checkbox %s, set selected is %s", getElement().toString(), status.toString()));
        if (getElement().isSelected() != status) {
            getElement().click();
        }
    }

    public void hover() {
        Actions action = new Actions(Driver.getDriver());
        action.moveToElement(getElement()).perform();
    }

    public void selectRandomOption(int min, int max) {
        Log.info(String.format("Select random option %s", getElement().toString()));
        getSelectObject().selectByIndex(Utilities.ramdomInt(min, max));
    }

    //wait
    public Element hardWait(int milisecond) {
        try {
            Thread.sleep(milisecond);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public void waitUntilElementNotCovered() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Timeout));
        ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    Point location = getElement().getLocation();
                    Dimension size = getElement().getSize();
                    Rectangle rect = new Rectangle(location.getX(), location.getY(), size.getWidth(), size.getHeight());
                    if (!(driver instanceof TakesScreenshot)) {
                        return false;
                    }
                    BufferedImage screenshot = ImageIO.read(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
                    for (int x = rect.x; x < rect.x + rect.width; x++) {
                        for (int y = rect.y; y < rect.y + rect.height; y++) {
                            Color color = new Color(screenshot.getRGB(x, y));
                            if (color.getAlpha() != 0) {
                                return false;
                            }
                        }
                    }
                    return true;
                } catch (StaleElementReferenceException e) {
                    return null;
                } catch (IOException e) {
                    return false;
                }
            }
        };
        try {
            wait.until(condition);
        } catch (Exception e) {
            Log.error(e);
        }
    }

    public Element waitForElementVisible() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return this;
    }

    public Element waitForElementExist() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Timeout));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return this;
    }

    public Element waitForElementNotExist() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        return this;
    }

    public Element waitForElementEnable() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Timeout));
        wait.until(ExpectedConditions.elementToBeClickable(by));
        return this;
    }

    public Element waitForElementStaleness() {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Timeout));
            wait.until(ExpectedConditions.stalenessOf(getElement()));
        } catch (Exception e) {
            System.out.println(e);
        }
        return this;
    }

    public void selectListOption(String text) {
        Log.info(String.format("Select %s in list", text));
        try {
            Select select = new Select(this.getElement());
            List<WebElement> options = select.getOptions().stream()
                    .filter(o -> o.getText().contains(text))
                    .collect(Collectors.toList());
            options.get(0).click();
        } catch (Exception e) {
            Log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String getText() {
        Log.info(String.format("Get text %s", getElement().toString()));
        return this.getElement().getText();
    }

    public void moveTo() {
        try {
            this.waitForElementVisible();
            action.moveToElement(this.getElement()).perform();
        } catch (Exception e) {
            Log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void selectOptionValue(String value) {
        Log.info(String.format("%s, Select option %s", getElement().toString(), value));
        try {
            Select select = new Select(this.getElement());
            List<WebElement> options = select.getOptions().stream()
                    .filter(o -> o.getAttribute("value").equals(value))
                    .collect(Collectors.toList());
            options.get(0).click();
        } catch (Exception e) {
            Log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
