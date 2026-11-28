import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Page Test Class em JUnit 6 para validar o cenário {@code UserStoryTest4}
 * utilizando o Page Object {@link UserStory4}.
 */
public class UserStory4Test {

    private WebDriver driver;
    private UserStory4 userStory4Page;

    /**
     * Inicializa o navegador Firefox e instancia o Page Object antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        userStory4Page = new UserStory4(driver);
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
     * Valida a apresentação do título "Regras da Batalha Naval" ao selecionar
     * o jogo Battleship no catálogo inicial.
     */
    @Test
    public void testUserStory4() {
        userStory4Page.openHomePage();
        userStory4Page.setWindowSize(1470, 923);
        userStory4Page.selectBattleshipGame();

        assertEquals("Regras da Batalha Naval", userStory4Page.getBattleshipRulesTitle());
    }
}
