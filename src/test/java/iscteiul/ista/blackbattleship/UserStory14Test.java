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
 * Classe de testes de aceitação para a User Story 14 (Torneios).
 */
public class UserStory14Test {

    private WebDriver driver;
    private UserStory14 userStory14;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        userStory14 = new UserStory14(driver);
    }

    @Test
    public void testConsultTournaments() throws InterruptedException {
        userStory14.openMyTournaments();
        Thread.sleep(2000);
        assertTrue(userStory14.isTournamentsDisplayed());

        userStory14.openSpecificTournament();
        Thread.sleep(2000);
        assertTrue(driver.getCurrentUrl().contains("/en/t/"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}