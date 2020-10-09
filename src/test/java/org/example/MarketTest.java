package org.example;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class MarketTest {
    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver",
                "/Users/glebsolovev/Downloads/chromedriver-2");
    }
    //Проверка отображения яндекс маркета на перовой позиции в рез-те поиска через Google
    @Test
    public void checkMarketSearchResult() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://www.google.com");
        driver.findElement(By.xpath("//input[@title='Поиск']")).sendKeys("яндекс маркет");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        WebElement links = driver.findElement(By.xpath("//div[@class='rc']"));
        Assert.assertTrue(links.getText().contains("market.yandex.ru"));
        driver.close();
    }

    //Проверка отображения выбранных фильтров Яндекс Маркета
    @Test
    public void checkFilterIsDisplayed(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://market.yandex.ru/");
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("пылесосы");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.cssSelector("a[class='_1HzO2DwHfE cia-cs']")).click();
        driver.findElement(By.cssSelector("div[class='_2Wdscr8iIC']")).click();
        driver.findElement(By.cssSelector("input[class='_34OG20yGDA']")).sendKeys("vitek");
        driver.findElement(By.xpath("//label[@id='152837']")).click();
        driver.findElement(By.cssSelector("input[class='_34OG20yGDA']")).clear();
        driver.findElement(By.cssSelector("input[class='_34OG20yGDA']")).sendKeys("polaris");
        driver.findElement(By.xpath("//label[@id='288426']")).click();
        driver.findElement(By.xpath("//input[@placeholder='65 990']")).sendKeys("6000");
        driver.findElement(By.cssSelector("a[class='_2qvOOvezty _3qN-vKmEan _1Rc6LxE3Tr']")).click();
        String afterPrice = driver.findElement(By.cssSelector("input[class='_2yK7W3SWQ- _1f2usTwyAs']")).getAttribute("value");

        WebElement vitekCheckbox = driver.findElement(By.cssSelector("input[name='Производитель VITEK']"));
        WebElement polarisCheckbox = driver.findElement(By.cssSelector("input[name='Производитель Polaris']"));
        Boolean bosh = vitekCheckbox.isSelected();
        Boolean polaris = polarisCheckbox.isSelected();
        Assert.assertTrue(vitekCheckbox.isSelected());
        Assert.assertTrue(polarisCheckbox.isSelected());
        Assert.assertTrue(afterPrice.equals("6000"));

    }

}




