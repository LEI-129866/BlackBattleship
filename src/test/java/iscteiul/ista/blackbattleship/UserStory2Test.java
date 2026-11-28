package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de testes de aceitação para a User Story 2 (Loja de Avatares).
 */
public class UserStory2Test {

    private WebDriver driver;
    private UserStory2 userStory2;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        userStory2 = new UserStory2(driver);
    }

    @Test
    public void testAccessAvatarsShop() throws InterruptedException {
        userStory2.openAvatarsShop();
        Thread.sleep(2000);
        assertTrue(driver.getCurrentUrl().contains("avatars"));
        assertTrue(userStory2.isShopDisplayed());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}