import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de testes JUnit responsável por validar a User Story 8:
 * Posicionamento dos navios no tabuleiro ao iniciar partida contra o robot.
 *
 * @author 131589
 */
public class UserStory8Test {

    private WebDriver driver;
    private UserStory8 userStory8Page;

    /**
     * Inicializa o WebDriver do Chrome e a respetiva Page Object antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        userStory8Page = new UserStory8(driver);
    }

    /**
     * Encerra a sessão do browser após a conclusão de cada teste.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Testa a abertura do jogo Battleship, o início de uma partida contra o robot
     * e a presença do tabuleiro com os navios posicionados.
     *
     * @throws InterruptedException Caso ocorra interrupção nas esperas de execução.
     */
    @Test
    public void testPosicionamentoNaviosNoTabuleiro() throws InterruptedException {
        userStory8Page.abrirPaginaBattleship();
        userStory8Page.iniciarPartidaContraRobot();
        assertTrue(userStory8Page.isTabuleiroNaviosPresente(),
                "O tabuleiro com os navios posicionados deve estar presente na partida.");
    }
}