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
 * Classe de testes de aceitação (JUnit) para a User Story 9,
 * validando os disparos sobre coordenadas do tabuleiro adversário em Batalha Naval.
 */
public class UserStory9Test {

    private WebDriver driver;
    private UserStory9 userStory9Page;

    /**
     * Inicializa o WebDriver do Chrome e instancia o Page Object antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        userStory9Page = new UserStory9(driver);
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
     * Executa o cenário completo da User Story 9:
     * acede ao jogo Battleship, inicia partida contra o robot e efetua três disparos no tabuleiro inimigo.
     *
     * @throws InterruptedException caso ocorra interrupção nas pausas de sincronização com a interface.
     */
    @Test
    public void testFireAtOpponentBoardCoordinates() throws InterruptedException {
        userStory9Page.openHomePage();
        userStory9Page.selectBattleshipGame();
        userStory9Page.clickPlayAgainstRobot();
        userStory9Page.confirmNicknameAndStart("ines");

        // Efetua três disparos em coordenadas distintas do tabuleiro adversário
        userStory9Page.fireAtCoordinate(12);
        userStory9Page.fireAtCoordinate(24);
        userStory9Page.fireAtCoordinate(36);

        Assertions.assertTrue(userStory9Page.isBoardVisible(), "O tabuleiro de Batalha Naval deve permanecer ativo após os disparos.");
    }
}