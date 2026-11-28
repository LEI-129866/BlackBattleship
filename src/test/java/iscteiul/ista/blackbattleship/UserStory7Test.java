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
 * Classe de testes de aceitação para a User Story 7 (Convite Privado Battleship).
 */
public class UserStory7Test {

    private WebDriver driver;
    private UserStory7 userStory7;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        userStory7 = new UserStory7(driver);
    }

    @Test
    public void testCreatePrivateRoomInvite() throws InterruptedException {
        userStory7.openBattleshipPage();
        Thread.sleep(2000);
        userStory7.clickPlayWithFriend();
        Thread.sleep(2000);
        assertTrue(userStory7.isGameAreaVisible());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
