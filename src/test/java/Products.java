import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class Products {

    private WebDriver driver;

    Products(WebDriver driver) { this.driver = driver; }

    void getList() {
        List<WebElement> list = driver.findElements(By.className("n-snippet-card"));
        assertThat(12, equalTo(list.size()));

        WebElement firstNote = driver.findElement(By.xpath(".//div[1]/div[1]/div[3]//h3//span"));
        String firstNoteText = firstNote.getText();

        driver.findElement(By.xpath("//*[@id=\"header-search\"]")).sendKeys(firstNoteText);
        checkProducts(firstNoteText);

    }

    @Step("Проверка результата поиска по названию: \"{0}\"")
    private void checkProducts(String firstNoteText) {
        driver.findElement(By.xpath(".//span[text()='Найти']/..")).click();
        WebElement sameNote = driver.findElement(By.xpath(".//h1"));
        String sameNoteText = sameNote.getText();
        assertThat(firstNoteText, equalTo(sameNoteText));
    }

}
