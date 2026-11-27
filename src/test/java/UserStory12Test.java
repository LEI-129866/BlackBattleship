import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de testes JUnit responsável por validar a User Story 12:
 * Acesso e visualização da página de gestão de amigos.
 *
 * @author 131589
 */
public class UserStory12Test {

    private WebDriver driver;
    private UserStory12 userStory12Page;

    /**
     * Inicializa o WebDriver do Chrome e a respetiva Page Object antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        userStory12Page = new UserStory12(driver);
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
     * Testa a navegação para a página de amigos e verifica se a respetiva secção é apresentada.
     *
     * @throws InterruptedException Caso ocorra interrupção nas esperas de execução.
     */
    @Test
    public void testAcessoGestaoAmigos() throws InterruptedException {
        userStory12Page.abrirPaginaAmigos();
        assertTrue(userStory12Page.isSecaoAmigosPresente(),
                "A secção de gestão de amigos deve estar visível na página.");
    }
}