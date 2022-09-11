import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;



public class CheckingBookingOfTheNYHotelTest {

    private final WebDriver driver;

    By destinationsField =By.id("ss");
    By changeCountryButton = By.xpath("//button[@data-tooltip-text='Выберите язык']");
    By ChoseLanguageButton = By.xpath("//div[@class='bui-overlay__content']/descendant::a[@hreflang='en-us'][2]");
    By selectorOfMonth = By.xpath("//div[@class='bui-calendar__control bui-calendar__control--next']");
    By searchButton = By.xpath("//span[@class='js-sb-submit-text ']");
    By checkInDates = By.xpath("(//button[@class='d47738b911 fb1847d86a'])[1]");
    By checkInField = By.xpath("(//span[@class='sb-date-field__icon sb-date-field__icon-btn bk-svg-wrapper calendar-restructure-sb'])[1]");
    By titleOfMonthFromCalendar = By.xpath("(//div[@class='bui-calendar__month'])[1]");
    By checkOutDates = By.xpath("(//button[@class='d47738b911 fb1847d86a'])[2]");


    public CheckingBookingOfTheNYHotelTest(WebDriver driver) {
        this.driver = driver;
    }

    public void openMainPage() {
        driver.get("https://www.booking.com/");
    }

    public void changeLanguage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10L));

        wait.until(ExpectedConditions.elementToBeClickable(changeCountryButton)).click();
    }
    public void changeCountry(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));

        wait.until(ExpectedConditions.elementToBeClickable(ChoseLanguageButton)).click();
    }

    public void typeCity(String cityName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000L));

        wait.until(ExpectedConditions.elementToBeClickable(destinationsField)).sendKeys(cityName);
    }

    public void checkInDateChoosing(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
        wait.until(ExpectedConditions.elementToBeClickable(checkInField)).click();
        String checkMonth = "December 2022";
        String checkInDate = "1";
        while (true){
            String month = driver.findElement(titleOfMonthFromCalendar).getText();
            if(month.equals(checkMonth)){
                break;
            }
            else {
                wait.until(ExpectedConditions.elementToBeClickable(selectorOfMonth)).click();
            }
        }
        List<WebElement> listOfDaysOfMonth = driver.findElements(By.xpath("(//tbody)[1]//tr//td/span"));
        for(WebElement list:listOfDaysOfMonth){
            String currentDate = list.getText();
            if(currentDate.equals(checkInDate)){
                list.click();
                break;
            }
            Assertions.assertTrue(currentDate.contains("December 1, 2022"));
        }
    }

    public void checkOutDateChoosing(){
        String checkMonth = "December 2022";
        String checkOutDate = "31";
        while (true){
            String month = driver.findElement(titleOfMonthFromCalendar).getText();
            if(month.equals(checkMonth)){
                break;
            }
            else {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
                wait.until(ExpectedConditions.elementToBeClickable(selectorOfMonth)).click();
            }
        }
        List<WebElement> listOfDaysOfMonth = driver.findElements(By.xpath("(//tbody)[1]//tr//td/span"));
        for(WebElement list:listOfDaysOfMonth){
            String currentDate = list.getText();
            if(currentDate.equals(checkOutDate)){
                list.click();
                break;
            }
        }
    }

    public void clickSearch() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void titlesWithNewYork () {
        String firstDate = "December 1, 2022";
        String secondDate = "December 31, 2022";
        List<WebElement> hotelLists = driver.findElements(By.xpath("//span[@data-testid='address']"));

        List<String> totalResultByCity = new ArrayList<>();
        for (WebElement elements : hotelLists) {
            String e = elements.getText();
            if (e.contains("New York")) {
                totalResultByCity.add(e);
            }
        }

        List<String> totalResultOnWholeFirstPage = new ArrayList<>();
        for (WebElement attractionList : hotelLists) {
            String text = attractionList.getText();
            totalResultOnWholeFirstPage.add(text);
        }

        Assertions.assertEquals(totalResultByCity.size(),totalResultOnWholeFirstPage.size());

        boolean firstCheckingDate = driver.findElement(checkInDates).getText().contains(firstDate);
        Assertions.assertTrue(firstCheckingDate);

        boolean secondCheckDate = driver.findElement(checkOutDates).getText().contains(secondDate);
        Assertions.assertTrue(secondCheckDate);

    }
}

