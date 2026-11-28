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
 * Classe de testes de aceitação para a User Story 18 (Changelog).
 */
public class UserStory18Test {

    private WebDriver driver;
    private UserStory18 userStory18;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        userStory18 = new UserStory18(driver);
    }

    @Test
    public void testConsultChangelog() throws InterruptedException {
        userStory18.openChangelog();
        Thread.sleep(2000);
        assertTrue(driver.getCurrentUrl().contains("changelog"));
        assertTrue(userStory18.isChangelogDisplayed());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}