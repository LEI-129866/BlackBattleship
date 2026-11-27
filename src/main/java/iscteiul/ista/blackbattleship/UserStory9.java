package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Page Object Class correspondente à User Story 9:
 * Permite iniciar uma partida de Batalha Naval contra o robot na plataforma PaperGames
 * e efetuar disparos sobre coordenadas do tabuleiro adversário.
 */
public class UserStory9 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Localizadores baseados no ficheiro TestSuite_129866.side
    private final By cookieConsentButton = By.cssSelector(".fc-cta-consent, .fc-data-preferences-accept-all");
    private final By battleshipGameCard = By.cssSelector(".game-item:nth-child(1) .img-fluid");
    private final By playVsRobotButton = By.cssSelector(".w-100:nth-child(2) > .btn .flex-grow-1");
    private final By nicknameInput = By.cssSelector("input.input-xl");
    private final By confirmNicknameButton = By.cssSelector(".p-3 > .btn");
    private final By opponentBoardCells = By.cssSelector("app-battleship-board:last-of-type td, .hover");

    /**
     * Construtor da classe Page Object iscteiul.ista.blackbattleship.UserStory9.
     *
     * @param driver instância ativa do {@link WebDriver} utilizada para controlar o browser.
     */
    public UserStory9(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Acede à página inicial da plataforma PaperGames e define a dimensão da janela.
     */
    public void openHomePage() {
        driver.get("https://papergames.io/en/");
        driver.manage().window().setSize(new Dimension(1800, 1130));
        acceptCookiesIfPresent();
    }

    /**
     * Aceita o aviso de privacidade e cookies caso seja apresentado pelo navegador.
     */
    public void acceptCookiesIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement consentBtn = shortWait.until(ExpectedConditions.elementToBeClickable(cookieConsentButton));
            consentBtn.click();
        } catch (Exception ignored) {
            // A janela de cookies não apareceu nesta sessão
        }
    }

    /**
     * Seleciona o jogo Batalha Naval (Battleship) no catálogo da página inicial.
     */
    public void selectBattleshipGame() {
        WebElement gameElement = wait.until(ExpectedConditions.elementToBeClickable(battleshipGameCard));
        gameElement.click();
    }

    /**
     * Clica na opção para iniciar uma partida contra o robot.
     */
    public void clickPlayAgainstRobot() {
        WebElement robotBtn = wait.until(ExpectedConditions.elementToBeClickable(playVsRobotButton));
        robotBtn.click();
    }

    /**
     * Preenche o nickname caso o campo esteja vazio e confirma para iniciar a partida no tabuleiro.
     *
     * @param nickname nome de jogador a utilizar na partida.
     * @throws InterruptedException se a pausa de estabilização do tabuleiro for interrompida.
     */
    public void confirmNicknameAndStart(String nickname) throws InterruptedException {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement input = shortWait.until(ExpectedConditions.visibilityOfElementLocated(nicknameInput));
            if (input.getAttribute("value") == null || input.getAttribute("value").isEmpty()) {
                input.sendKeys(nickname);
            }
        } catch (Exception ignored) {
            // O campo de nickname já se encontra preenchido
        }

        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(confirmNicknameButton));
        continueBtn.click();
        Thread.sleep(2500); // Aguarda o carregamento e posicionamento inicial do tabuleiro
    }

    /**
     * Dispara sobre uma coordenada específica do tabuleiro adversário.
     *
     * @param cellIndex índice da célula alvo no tabuleiro inimigo.
     * @throws InterruptedException se a espera entre jogadas for interrompida.
     */
    public void fireAtCoordinate(int cellIndex) throws InterruptedException {
        List<WebElement> cells = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(opponentBoardCells));
        if (cells.size() > cellIndex) {
            wait.until(ExpectedConditions.elementToBeClickable(cells.get(cellIndex))).click();
        } else if (!cells.isEmpty()) {
            cells.get(0).click();
        }
        Thread.sleep(1500); // Tempo para feedback visual e resposta do robot
    }

    /**
     * Verifica se o tabuleiro de jogo se encontra ativo e visível no ecrã.
     *
     * @return {@code true} se existirem células do tabuleiro visíveis; {@code false} caso contrário.
     */
    public boolean isBoardVisible() {
        List<WebElement> cells = driver.findElements(opponentBoardCells);
        return !cells.isEmpty();
    }
}