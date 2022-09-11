import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class CheckNYAttractionQuantity {

    private final WebDriver driver;

    By changeCountryButton = By.xpath("//button[@data-tooltip-text='Выберите язык']");
    By ChoseLanguageButton = By.xpath("//div[@class='bui-overlay__content']/descendant::a[@hreflang='en-us'][2]");
    By attractionsButton = By.xpath("(//a[@class='bui-tab__link'])[3]");
    By searchField = By.xpath("//input[@name='query']");
    By searchButton = By.xpath("//button[@type='submit']");
    By tableOfCategory = By.xpath("(//fieldset[@class = 'db29ecfbe2 c072c8cf10']//span[text()='Category'])[1]");
    By tableOfCities = By.xpath("(//fieldset[@class='db29ecfbe2 c072c8cf10']//span[text()='City'])[1]");



    public CheckNYAttractionQuantity(WebDriver driver) {
        this.driver = driver;
    }

    public void openMainPage() {
        driver.get("https://www.booking.com/");
    }

    public void changeLanguage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
        wait.until(ExpectedConditions.elementToBeClickable(changeCountryButton)).click();
    }

    public void changeCountry(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000L));
        wait.until(ExpectedConditions.elementToBeClickable(ChoseLanguageButton)).click();
    }

    public void attractionClick(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000L));
        wait.until(ExpectedConditions.elementToBeClickable(attractionsButton)).click();
    }

    public void searchCityWithAttraction(String cityName){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1500L));
        wait.until(ExpectedConditions.elementToBeClickable(searchField)).sendKeys(cityName);
    }

    public void clickSearchButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1500L));
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void chooseCategory(){

        List<WebElement> categoryCheckboxes =  driver.findElements(tableOfCategory);
         for(WebElement listCategories:categoryCheckboxes ){
             if (listCategories.getText().contains("Attractions")){
                 listCategories.click();
                 break;
             }
         }
    }

    public void chooseCityWithAttractions(){

        List<WebElement> citiesCheckboxes =  driver.findElements(tableOfCities);
        for(WebElement listCities:citiesCheckboxes){
            if (listCities.getText().contains("New York")){
                listCities.click();
                break;
            }
        }
    }

    public void checkResultWIthAttractionsInNy() {

        List<WebElement> attractionLists = driver.findElements(By.xpath("//div[@class='css-1rrebqu']"));

        List<String> totalResultByCity = new ArrayList<>();
        for (WebElement elements : attractionLists) {
            String e = elements.getText();
            if (e.contains("New York")) {
                totalResultByCity.add(e);
            }
        }

        List<String> totalResultOnWholeFirstPage = new ArrayList<>();
        for (WebElement attractionList : attractionLists) {
            String text = attractionList.getText();
            totalResultOnWholeFirstPage.add(text);
        }

        Assertions.assertEquals(totalResultByCity.size(),totalResultOnWholeFirstPage.size());

        }
    }

