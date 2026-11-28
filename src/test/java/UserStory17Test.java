import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Page Test Class em JUnit 6 para validar o cenário {@code UserStoryTest17}
 * utilizando o Page Object {@link UserStory17}.
 */
public class UserStory17Test {

    private WebDriver driver;
    private UserStory17 userStory17Page;

    /**
     * Inicializa o navegador Firefox e instancia o Page Object antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        userStory17Page = new UserStory17(driver);
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
     * Valida a navegação no catálogo da página inicial, entrando no Battleship,
     * regressando à página inicial e abrindo o Jogo da Velha (Tic Tac Toe).
     */
    @Test
    public void testUserStory17() {
        userStory17Page.openHomePage();
        userStory17Page.setWindowSize(1470, 923);
        userStory17Page.selectBattleshipGame();
        userStory17Page.returnToHomeCatalog();
        userStory17Page.selectTicTacToeGame();

        assertEquals("Jogar Jogo da Velha online (Tic Tac Toe)", userStory17Page.getSelectedGameHeading());
    }
}
