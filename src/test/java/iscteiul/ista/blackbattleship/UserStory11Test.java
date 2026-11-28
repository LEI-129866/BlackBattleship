package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de testes de aceitação (JUnit) para a User Story 11,
 * validando o acesso à loja virtual de emojis e a seleção de um item para pré-visualização.
 */
public class UserStory11Test {

    private WebDriver driver;
    private UserStory11 userStory11Page;

    /**
     * Inicializa o WebDriver do Chrome e instancia o Page Object antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        userStory11Page = new UserStory11(driver);
    }

    /**
     * Encerra a sessão do navegador após a execução de cada teste.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Executa o cenário completo da User Story 11:
     * acede à loja de emojis, seleciona um emoji e confirma a interação na janela modal.
     *
     * @throws InterruptedException caso ocorra interrupção nas pausas de sincronização com a interface.
     */
    @Test
    public void testBrowseAndSelectEmojiInShop() throws InterruptedException {
        userStory11Page.openEmojisShopPage();
        Assertions.assertTrue(userStory11Page.getCurrentUrl().contains("/shop/emojis"),
                "O navegador deve estar na página da loja de emojis.");

        userStory11Page.selectEmojiProduct();
        Assertions.assertTrue(userStory11Page.isConfirmationDialogDisplayed(),
                "A janela de pré-visualização/confirmação do emoji deve ser apresentada.");

        userStory11Page.clickConfirmSecondaryOption();
    }
}