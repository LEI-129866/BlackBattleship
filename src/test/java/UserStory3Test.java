import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de testes JUnit responsável por validar a User Story 3:
 * Consulta do histórico de partidas de Batalha Naval.
 *
 * @author 131589
 */
public class UserStory3Test {

    private WebDriver driver;
    private UserStory3 userStory3Page;

    /**
     * Inicializa o WebDriver do Chrome e a respetiva Page Object antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        userStory3Page = new UserStory3(driver);
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
     * Testa a navegação direta para o histórico de partidas de Batalha Naval
     * e verifica a presença do título da página.
     *
     * @throws InterruptedException Caso ocorra interrupção nas esperas de execução.
     */
    @Test
    public void testConsultarHistoricoPartidas() throws InterruptedException {
        userStory3Page.abrirPaginaHistorico();
        assertTrue(userStory3Page.isElementoHistoricoPresente(),
                "O elemento principal da página de histórico deve estar visível.");
    }
}