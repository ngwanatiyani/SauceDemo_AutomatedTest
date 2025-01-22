package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import  org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args)
    {


        // Setting the path to ChromeDriver on my device

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tiyan\\Downloads\\chromedriver-win64\\chromedriver.exe");


        //Launching the browser
        WebDriver driver = new ChromeDriver();

        try {

            //Accessing SauceDemo website
            driver.get("https://www.saucedemo.com/v1/");


            //Maximize browser window
            driver.manage().window().maximize();


            WebElement username = driver.findElement(By.id("user-name"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("login-button"));

            //Enter login credentials

            username.sendKeys("standard_user");
            password.sendKeys("secret_sauce");

            //Click the login button
            loginButton.click();

            if (driver.getCurrentUrl().contains("inventory.html")) {
                System.out.println("Login Successful!");
            }

            else
            {
                System.out.println("Login failed!");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {

            //Closing the browser
            driver.quit();
        }

    }
}