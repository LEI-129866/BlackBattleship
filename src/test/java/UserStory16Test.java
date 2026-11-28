import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de testes JUnit responsável por validar a User Story 16:
 * Consulta dos planos de subscrição Plus e Gruppo.
 *
 * @author 131589
 */
public class UserStory16Test {

    private WebDriver driver;
    private UserStory16 userStory16Page;

    /**
     * Inicializa o WebDriver do Chrome e a respetiva Page Object antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        userStory16Page = new UserStory16(driver);
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
     * Testa a navegação para as páginas dos planos Plus e Gruppo,
     * validando a presença dos respetivos títulos e benefícios.
     *
     * @throws InterruptedException Caso ocorra interrupção nas esperas de execução.
     */
    @Test
    public void testConsultarPlanosSubscricaoPlusEGruppo() throws InterruptedException {
        userStory16Page.abrirPaginaPlanoPlus();
        assertTrue(userStory16Page.isPlanoPlusVisivel(),
                "Os detalhes do plano Plus devem estar visíveis na página.");

        userStory16Page.abrirPaginaPlanoGruppo();
        assertTrue(userStory16Page.isPlanoGruppoVisivel(),
                "Os detalhes do plano Gruppo devem estar visíveis na página.");
    }
}