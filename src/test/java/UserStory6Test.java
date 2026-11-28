import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Page Test Class em JUnit 6 para validar o cenário {@code UserStoryTest6}
 * utilizando o Page Object {@link UserStory6}.
 */
public class UserStory6Test {

    private WebDriver driver;
    private UserStory6 userStory6Page;

    /**
     * Inicializa o navegador Firefox e instancia o Page Object antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        userStory6Page = new UserStory6(driver);
    }

    /**
     * Fecha o navegador e liberta os recursos após cada execução de teste.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Valida a apresentação da opção "com jogador aleatório" e inicia
     * a procura por uma partida online em tempo real.
     */
    @Test
    public void testUserStory6() {
        userStory6Page.openHomePage();
        userStory6Page.setWindowSize(1470, 923);
        userStory6Page.selectBattleshipGame();

        assertEquals("com jogador aleatório", userStory6Page.getRandomPlayerOptionText());
        userStory6Page.startOnlineMatchAgainstRandomPlayer("lei-129846");
    }
}