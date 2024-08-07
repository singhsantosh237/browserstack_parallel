package browserstack_localbrowser;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class firstSessionLocalBrowserChrome {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            driver.get("https://www.flipkart.com");
            Thread.sleep(5000);

            // Enter the keyword for searching the device
            WebElement searchBar = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title='Search for Products, Brands and More']")));
            searchBar.sendKeys("Samsung Galaxy S10");
            searchBar.sendKeys(Keys.RETURN);
            Thread.sleep(2000);

            // Wait for Results
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'results for')]//following::span[text()='Samsung Galaxy S10']")));

            // Select the Mobiles in categories
            WebElement mobileBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[text()='Mobiles'])[1]")));
            mobileBtn.click();

            // Select the Brand Filter
            WebElement brandFilter = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Brand']//following::div[text()='SAMSUNG']//preceding::input[@type='checkbox']//following::div[1]")));
            brandFilter.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='SAMSUNG']")));

            Thread.sleep(2000);

            // Select the F-assured Filter
            WebElement fassuredBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[text()='Brand']//following::img[1]//preceding::input[@type='checkbox'])[2]//following::div[1]")));
            fassuredBtn.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Plus (FAssured)']")));

            Thread.sleep(2000);

            // Select High to Low
            WebElement highToLowBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Price -- High to Low']")));
            highToLowBtn.click();

            Thread.sleep(2000);

            List<WebElement> links = driver.findElements(By.xpath("(//div[text()='Newest First']//following::a[@rel='noopener noreferrer' and contains(@href,'samsung')])"));
            List<WebElement> prices = driver.findElements(By.xpath("(//div[text()='Newest First']//following::div[@class='Nx9bqj _4b5DiR' and contains(text(),'₹')])"));
            List<WebElement> modelNames = driver.findElements(By.xpath("//div[text()='Newest First']//following::div[@class='KzDlHZ' and contains(text(),'SAMSUNG') or contains(text(),'Samsung')]"));

            System.out.println("Links found: " + links.size());
            System.out.println("Prices found: " + prices.size());
            System.out.println("Model names found: " + modelNames.size());

            if (links.size() == prices.size() && links.size() == modelNames.size()) {
                for (int i = 0; i < links.size(); i++) {
                    System.out.println("The Model Name is: " + modelNames.get(i).getText() +
                            ", \n Price of model is: " + prices.get(i).getText() +
                            ", \n Link: " + links.get(i).getAttribute("href") + "\n");
                }
            } else {
                System.out.println("Mismatch in the number of elements found.");
            }
        }

        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        finally
        {
            driver.quit();
        }
    }
}
