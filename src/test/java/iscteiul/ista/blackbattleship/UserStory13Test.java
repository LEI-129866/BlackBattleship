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
 * Classe de testes de aceitação (JUnit) para a User Story 13,
 * validando o fluxo de navegação para a criação de torneios e submissão do formulário.
 */
public class UserStory13Test {

    private WebDriver driver;
    private UserStory13 userStory13Page;

    /**
     * Inicializa o WebDriver do Chrome e instancia o Page Object antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        userStory13Page = new UserStory13(driver);
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
     * Executa o cenário completo da User Story 13:
     * acede à secção de criação de torneios, submete o pedido de criação,
     * valida a apresentação da janela modal e fecha-a.
     */
    @Test
    public void testCreateTournamentFlow() {
        userStory13Page.openHomePage();
        userStory13Page.navigateToCreateTournament();
        userStory13Page.clickCreateAndShareTournament();

        Assertions.assertTrue(userStory13Page.isSignUpModalDisplayed(),
                "A janela modal de autenticação deve surgir ao tentar criar um torneio sem sessão iniciada.");

        userStory13Page.closeSignUpModal();
    }
}