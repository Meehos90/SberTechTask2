import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.concurrent.TimeUnit;

@Title("Тестовое задание")
public class SeleniumTasksTest {

    private WebDriver driver;

    @Step("открытие маркета и выбор категории")
    private void openPage(String s) {
        driver.get("https://www.yandex.ru");
        OpenPage openPage = new OpenPage(driver);
        openPage.checkLink("Маркет");
        openPage.checkLink("Компьютеры");
        openPage.checkLink(s);
        openPage.checkLink("Перейти ко всем фильтрам");
    }

    @Step("Поиск по фильтрам: цена от \"{0}\" и до \"{1}\", компании: \"{2}\", \"{3}\"")
    private void searching(String fromprice, String toprice, String company1, String company2) {
        OpenPage openPage = new OpenPage(driver);
        Filter filter = new Filter(driver);
        filter.fromSetPrice(fromprice);
        filter.toSetPrice(toprice);
        filter.findCompanies();
        filter.setCompany(company1);
        filter.setCompany(company2);
        openPage.checkLink("Показать подходящие");
    }

    @Step("Поиск и проверка элементов списка")
    private void checkAndFind() {
        Products products = new Products(driver);
        products.getList();
    }

    @Before
    public void start() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void exit() {
        driver.quit();
    }

    @Test
    @Title("Ищем ноутбуки")
    public void testFirstTask()  {
        openPage("Ноутбуки");
        searching("","30000", "HP", "Lenovo");
        checkAndFind();
    }

    @Test
    @Title("Ищем планшеты")
    public void testSecondTask()  {
        openPage("Планшеты");
        searching("20000","25000", "Acer", "DELL");
        checkAndFind();
    }
}

