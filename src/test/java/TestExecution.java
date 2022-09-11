import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestExecution {

        private static CheckingBookingOfTheNYHotelTest checkingBookingOfTheNYHotelTest;
        private static CheckNYAttractionQuantity checkNYAttractionQuantity;
        private static WebDriver driver;


        public TestExecution() {

            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.setHeadless(false);
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            driver = new ChromeDriver(options);
            checkingBookingOfTheNYHotelTest = new CheckingBookingOfTheNYHotelTest(driver);
            checkNYAttractionQuantity = new CheckNYAttractionQuantity(driver);
        }

        @Test
        public void CheckNYHotelTest(){
            String cityName = "New York";
            checkingBookingOfTheNYHotelTest.openMainPage();
            checkingBookingOfTheNYHotelTest.changeLanguage();
            checkingBookingOfTheNYHotelTest.changeCountry();
            checkingBookingOfTheNYHotelTest.typeCity(cityName);
            checkingBookingOfTheNYHotelTest.checkInDateChoosing();
            checkingBookingOfTheNYHotelTest.checkOutDateChoosing();
            checkingBookingOfTheNYHotelTest.clickSearch();
            checkingBookingOfTheNYHotelTest.titlesWithNewYork();
        }

        @Test
        public void CheckNYAttractionTest() {
            String cityName = "New York";
            checkNYAttractionQuantity.openMainPage();
            checkNYAttractionQuantity.changeLanguage();
            checkNYAttractionQuantity.changeCountry();
            checkNYAttractionQuantity.attractionClick();
            checkNYAttractionQuantity.searchCityWithAttraction(cityName);
            checkNYAttractionQuantity.clickSearchButton();
            checkNYAttractionQuantity.chooseCategory();
            checkNYAttractionQuantity.chooseCityWithAttractions();
            checkNYAttractionQuantity.checkResultWIthAttractionsInNy();
        }

        @AfterEach
        public void cleanUp() {
            driver.close();
            driver.quit();
        }
    }
