import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Page Test Class em JUnit 6 para validar o cenário {@code UserStoryTest5}
 * utilizando o Page Object {@link UserStory5}.
 */
public class UserStory5Test {

    private WebDriver driver;
    private UserStory5 userStory5Page;

    /**
     * Inicializa o navegador Firefox e instancia o Page Object antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        userStory5Page = new UserStory5(driver);
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
     * Valida o início de uma partida contra o robot e o carregamento
     * do tabuleiro de posicionamento com o cabeçalho "Seus navios".
     */
    @Test
    public void testUserStory5() {
        userStory5Page.openHomePage();
        userStory5Page.setWindowSize(1470, 923);
        userStory5Page.selectBattleshipGame();
        userStory5Page.startGameAgainstRobot("lei-129846");

        assertEquals("Seus navios", userStory5Page.getPlayerShipsHeaderText());
    }
}
