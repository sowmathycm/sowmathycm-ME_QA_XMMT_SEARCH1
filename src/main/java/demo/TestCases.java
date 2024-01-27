package demo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.devtools.v116.webaudio.WebAudio;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
        ChromeDriver driver;
        private WebDriverWait wait;

        public TestCases() {
                System.out.println("Constructor: TestCases");

                WebDriverManager.chromedriver().timeout(30).setup();
                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                // Set log level and type
                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);

                // Set path for log file
                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

                driver = new ChromeDriver(options);
                wait = new WebDriverWait(driver, Duration.ofSeconds(30));

                // Set browser to maximize and wait
                driver.manage().window().maximize();
                // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        }

        public void endTest() {
                System.out.println("End Test: TestCases");
                driver.close();
                driver.quit();

        }

        public void testCase01() {
                System.out.println("Start Test case: testCase01");
                driver.get("https://www.makemytrip.com/");
                String currentURL = driver.getCurrentUrl();
                String expectedTitle = "makemytrip.";
                if (currentURL.contains(expectedTitle)) {
                        System.out.println("The URL contains the expected title" + " " + expectedTitle);
                } else {
                        System.out.println("The URL does not contain the expected title" + " " + expectedTitle);
                }
                System.out.println("end Test case: testCase01");
        }

        /**
         * @throws InterruptedException
         */
        public void testCase02() throws InterruptedException, TimeoutException {
                System.out.println("Start Test case: testCase02");
                driver.get("https://www.makemytrip.com/");
                WebElement from = wait.until(ExpectedConditions
                                .elementToBeClickable(By
                                                .xpath("//div[@class='flt_fsw_inputBox searchCity inactiveWidget ']")));
                from.click();

                WebElement fromInput = driver.findElement(By.xpath("//input[@placeholder='From']"));
                fromInput.sendKeys("blr");

                WebElement dropdWebElement = wait.until(ExpectedConditions
                                .visibilityOfElementLocated(
                                                By.xpath("//div[@class='calc60']//p[text()='Bengaluru, India']")));
                dropdWebElement.click();

                WebElement dest = wait.until(ExpectedConditions
                                .elementToBeClickable(By.xpath(
                                                "//div[@class='flt_fsw_inputBox searchToCity inactiveWidget ']")));
                dest.click();

                WebElement destInput = driver.findElement(By.xpath("//input[@placeholder='To']"));
                destInput.sendKeys("New Delhi");

                WebElement dropElement = wait.until(ExpectedConditions
                                .visibilityOfElementLocated(
                                                By.xpath("//div[@class='calc60']//p[text()='New Delhi, India']")));
                dropElement.click();

                WebElement departure = wait.until(
                                ExpectedConditions.presenceOfElementLocated(
                                                By.xpath("//span[contains(text(), \"Departure\")]")));

                wait.until(ExpectedConditions.elementToBeClickable(departure)).click();

                // Wait for the date picker to be visible
                WebElement datePicker = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(
                                                By.xpath("//div[contains(@class,'DayPicker')]")));

                // Click on a specific date (e.g., 29th January 2024)
                WebElement specificDate = datePicker.findElement(By.xpath("//div[@aria-label='Thu Feb 29 2024']"));
                specificDate.click();

                WebElement search = wait
                                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Search']")));
                search.click();

                WebElement flightPrice = wait.until(ExpectedConditions
                                .visibilityOfElementLocated(
                                                By.xpath("//div[@id='listing-id']//div[@class='textRight flexOne']")));
                String pricePerAdult = flightPrice.getText();
                System.out.println("Flight price per adult: " + pricePerAdult);
                System.out.println("End Test case: testCase02");

        }

        public void testCase03() {

                System.out.println("Start Test case: testCase03");
                driver.get("https://www.makemytrip.com/");
                WebElement trains = wait.until(ExpectedConditions
                                .elementToBeClickable(By
                                                .xpath("//span[@class='chNavIcon appendBottom2 chSprite chTrains']")));
                trains.click();
                WebElement from = wait
                                .until(ExpectedConditions.elementToBeClickable(
                                                By.xpath("//div[@data-cy='RailSearchWidget_338']")));
                from.click();
                WebElement fromInputField = wait
                                .until(ExpectedConditions
                                                .elementToBeClickable(By.xpath("//input[@placeholder='From']")));
                fromInputField.sendKeys("ypr");
                WebElement dropWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//span[@class='sr_city blackText' and text()='Bangalore']")));
                dropWebElement.click();
                WebElement dest = wait
                                .until(ExpectedConditions.elementToBeClickable(
                                                By.xpath("//div[@data-cy='RailSearchWidget_339']")));
                dest.click();
                WebElement destInput = wait
                                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='To']")));
                destInput.sendKeys("ndls");
                WebElement dropElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//span[@class='sr_city blackText' and text()='New Delhi Railway Station']")));
                dropElement.click();
                WebElement departure = wait
                                .until(ExpectedConditions.presenceOfElementLocated(
                                                By.xpath("//div[@data-cy='RailSearchWidget_340']")));
                wait.until(ExpectedConditions.elementToBeClickable(departure)).click();
                WebElement datePicker = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(
                                                By.xpath("//div[contains(@class,'DayPicker')]")));
                WebElement specificDate = datePicker.findElement(By.xpath("//div[@aria-label='Thu Feb 29 2024']"));
                specificDate.click();
                WebElement Class = wait
                                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-cy='class']")));
                Class.click();
                WebElement Classdrop = wait.until(ExpectedConditions
                                .visibilityOfElementLocated(
                                                By.xpath("//ul[@class='travelForPopup']//li[text()='Third AC']")));
                Classdrop.click();
                WebElement search = wait
                                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Search']")));
                search.click();
                List<WebElement> ticketPriceElements = driver.findElements(By.xpath(
                                "//div[@class='right-side-container']//div[@class='flex-column flex m-r-15']//div[@class='ticket-price justify-flex-end']"));

                if (!ticketPriceElements.isEmpty()) {
                        List<String> ticketPrices = new ArrayList<>();

                        for (WebElement element : ticketPriceElements) {
                                ticketPrices.add(element.getText());
                        }

                        if (!ticketPrices.isEmpty()) {
                                String trainPriceFor3AC = ticketPrices.get(0);

                                System.out.println("Train price for 3AC: " + trainPriceFor3AC);
                        } else {
                                System.out.println("No train prices found for 3AC.");
                        }
                } else {
                        System.out.println("No ticket price elements found on the page.");
                }

        }

        public void testCase04() {
                System.out.println("Start Test case: testCase04");
                driver.get("https://www.makemytrip.com/");
                WebElement bus = wait.until(ExpectedConditions
                                .elementToBeClickable(
                                                By.xpath("//span[@class='chNavIcon appendBottom2 chSprite chBuses']")));
                bus.click();
                WebElement from = wait
                                .until(ExpectedConditions.elementToBeClickable(
                                                By.xpath("//div[@data-cy='BusSearchWidget_22']")));
                from.click();
                WebElement fromInputField = wait
                                .until(ExpectedConditions
                                                .elementToBeClickable(By.xpath("//input[@placeholder='From']")));
                fromInputField.sendKeys("bangl");
                WebElement dropWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//span[@class='sr_city blackText' and text()='Bangalore, Karnataka']")));
                dropWebElement.click();
                WebElement dest = wait
                                .until(ExpectedConditions.elementToBeClickable(
                                                By.xpath("//div[@data-cy='BusSearchWidget_23']")));
                dest.click();
                WebElement destInput = wait
                                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='To']")));
                destInput.sendKeys("del");
                WebElement dropElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//span[@class='sr_city blackText' and text()='Delhi']")));
                dropElement.click();
                WebElement departure = wait
                                .until(ExpectedConditions.presenceOfElementLocated(
                                                By.xpath("//div[@data-cy='BusSearchWidget_24']")));
                wait.until(ExpectedConditions.elementToBeClickable(departure)).click();
                WebElement datePicker = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(
                                                By.xpath("//div[contains(@class,'DayPicker')]")));
                WebElement specificDate = datePicker.findElement(By.xpath("//div[@aria-label='Thu Feb 29 2024']"));
                specificDate.click();
                WebElement search = wait
                                .until(ExpectedConditions
                                                .elementToBeClickable(By.xpath("//button[@id='search_button']")));
                search.click();
                // Find the element containing the message
                WebElement messageElement = wait.until(
                                ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='error-title']")));
                // Get the text content of the element
                String actualMessage = messageElement.getText();

                // Define the expected message
                String expectedMessage = "No buses found for 29 Feb";

                // Verify if the actual message matches the expected message
                if (actualMessage.equals(expectedMessage)) {
                        System.out.println(
                                        "Verification passed: The message displayed is equal to 'No buses found for 29 Feb.'");
                } else {
                        System.out
                                        .println("Verification failed: The message displayed is not equal to 'No buses found for 29 Feb.'");
                }

        }

}
