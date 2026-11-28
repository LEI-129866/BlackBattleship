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
 * Classe de testes de aceitação (JUnit) para a User Story 15,
 * validando a navegação até à secção de moedas virtuais da loja PaperGames.
 */
public class UserStory15Test {

    private WebDriver driver;
    private UserStory15 userStory15Page;

    /**
     * Inicializa o WebDriver do Chrome e instancia o Page Object antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        userStory15Page = new UserStory15(driver);
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
     * Executa o cenário completo da User Story 15:
     * acede à página inicial, entra na loja virtual (Shop) e seleciona a secção de moedas virtuais (Coins).
     */
    @Test
    public void testNavigateToVirtualCoinsShop() {
        userStory15Page.openHomePage();
        userStory15Page.navigateToShop();
        userStory15Page.selectVirtualCoinsSection();

        Assertions.assertTrue(userStory15Page.getCurrentUrl().contains("/shop/virtual-coins"),
                "O navegador deve redirecionar para a página de moedas virtuais (/shop/virtual-coins).");
    }
}