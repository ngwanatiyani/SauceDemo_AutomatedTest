package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

//Author: Tiyani Ngwana
//Date: 25 January 2025


public class Main {


    //Constants for element locators
    private  static final String DRIVER_PATH = "C:\\Users\\Tiyan\\Downloads\\chromedriver-win64\\chromedriver.exe";
    private static final String BASE_URL = "https://saucedemo.com";
    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";


    public static void main(String[] args)
    {


        // Set up ChromeDriver
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        try {

            // Perform actions
            driver.get(BASE_URL);

            login(driver);
            addProductToCart(driver);
            verifyCart(driver);
            logout(driver);

        }
        catch (Exception e)
        {
            System.err.println("Test encountered an error: " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            // Close browser
            driver.quit();
        }
    }

    private static void login(WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys(USERNAME);
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.id("login-button")).click();
        System.out.println("Login successful");
    }

    private static void addProductToCart(WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack")));
        addToCartButton.click();
        System.out.println("Product added to cart");
    }

    private static void verifyCart(WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));
        String cartText = cartBadge.getText();

        if ("1".equals(cartText))
        {
            System.out.println("Test passed: 1 product added to cart");
        }
        else
        {
            System.out.println("Test failed: Cart contains " + cartText + " items");
        }
    }

    private static void logout(WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))).click();
        System.out.println("Logout successful");


    }
}