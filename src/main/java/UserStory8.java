import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Class responsável pelas interações na página de partida de Batalha Naval,
 * definição de nickname no modal de visitante e validação do tabuleiro de navios (User Story 8).
 *
 * @author 131589
 */
public class UserStory8 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final String BATTLESHIP_URL = "https://papergames.io/en/battleship";
    private final By playVsRobotButtonLocator = By.cssSelector(".w-100:nth-child(2) > .btn > .front");
    private final By nicknameInputLocator = By.cssSelector("input[placeholder='Nickname']");
    private final By continueButtonLocator = By.xpath("//button[contains(.,'Continue')]");
    private final By gameBoardLocator = By.cssSelector("app-board, .board, table");

    /**
     * Construtor da classe Page Object UserStory8.
     *
     * @param driver Instância do Selenium WebDriver utilizada para controlar o browser.
     */
    public UserStory8(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /**
     * Aceita o aviso de consentimento de cookies (RGPD) caso esteja visível no ecrã.
     */
    private void aceitarCookiesSeVisivel() {
        try {
            By consentBtnLocator = By.cssSelector("button.fc-cta-consent, button[aria-label='Consent']");
            WebElement consentBtn = new WebDriverWait(driver, Duration.ofSeconds(4))
                    .until(ExpectedConditions.elementToBeClickable(consentBtnLocator));
            consentBtn.click();
            Thread.sleep(800);
        } catch (Exception ignored) {
            // O pop-up de consentimento não apareceu
        }
    }

    /**
     * Acede à página principal do jogo Battleship e define a dimensão da janela.
     *
     * @throws InterruptedException Caso a pausa de simulação humana seja interrompida.
     */
    public void abrirPaginaBattleship() throws InterruptedException {
        driver.get(BATTLESHIP_URL);
        driver.manage().window().setSize(new Dimension(1400, 838));
        aceitarCookiesSeVisivel();
        Thread.sleep(1000);
    }

    /**
     * Clica no botão para jogar contra o robot, localiza o campo de nickname visível
     * no modal "Who are you?", preenche o nome, clica em "Continue" e aguarda o início da partida.
     *
     * @throws InterruptedException Caso a pausa de carregamento seja interrompida.
     */
    public void iniciarPartidaContraRobot() throws InterruptedException {
        WebElement playRobotBtn = wait.until(ExpectedConditions.elementToBeClickable(playVsRobotButtonLocator));
        playRobotBtn.click();
        Thread.sleep(1500);

        WebElement visibleNicknameInput = wait.until(d ->
                d.findElements(nicknameInputLocator).stream()
                        .filter(WebElement::isDisplayed)
                        .findFirst()
                        .orElse(null)
        );

        visibleNicknameInput.click();
        visibleNicknameInput.clear();
        visibleNicknameInput.sendKeys("mambogamer13");
        Thread.sleep(1000);

        WebElement visibleContinueBtn = wait.until(d ->
                d.findElements(continueButtonLocator).stream()
                        .filter(WebElement::isDisplayed)
                        .findFirst()
                        .orElse(null)
        );
        visibleContinueBtn.click();
        Thread.sleep(3000);
    }

    /**
     * Valida que o tabuleiro com os navios posicionados do jogador está presente.
     *
     * @return {@code true} se o tabuleiro estiver visível; {@code false} caso contrário.
     * @throws InterruptedException Caso a pausa de simulação humana seja interrompida.
     */
    public boolean isTabuleiroNaviosPresente() throws InterruptedException {
        WebElement boardElement = wait.until(ExpectedConditions.visibilityOfElementLocated(gameBoardLocator));
        Thread.sleep(1000);
        return boardElement.isDisplayed();
    }
}