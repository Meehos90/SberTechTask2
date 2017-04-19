import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

class OpenPage {

    private WebDriver driver;

    OpenPage(WebDriver driver) { this.driver = driver; }

    @Step("Открываем страницу \"{0}\"")
    void checkLink(String s) {
        WebElement link =  driver.findElement(By.linkText(s));
        Assert.assertEquals("Error", s, link.getText());
        link.click();
    }

}
