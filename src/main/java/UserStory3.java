import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Class responsável pelas interações na página de histórico de partidas
 * de Batalha Naval (User Story 3).
 *
 * @author 131589
 */
public class UserStory3 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final String HOME_URL = "https://papergames.io/en/battleship";
    private static final String MATCH_HISTORY_URL = "https://papergames.io/en/match-history?gameType=Battleships";

    private final By playVsRobotBtnLocator = By.cssSelector(".w-100:nth-child(2) > .btn > .front");
    private final By nicknameInputLocator = By.cssSelector("input[placeholder='Nickname']");
    private final By continueBtnLocator = By.xpath("//button[contains(.,'Continue')]");
    private final By pageHeadingLocator = By.cssSelector("h1, h2, .mb-0");

    /**
     * Construtor da classe Page Object UserStory3.
     *
     * @param driver Instância do Selenium WebDriver utilizada para controlar o browser.
     */
    public UserStory3(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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
     * Inicia uma sessão de visitante preenchendo o nickname no modal "Who are you?"
     * e acede de seguida à página do histórico de partidas de Batalha Naval.
     *
     * @throws InterruptedException Caso a pausa de simulação humana seja interrompida.
     */
    public void abrirPaginaHistorico() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(1400, 838));
        driver.get(HOME_URL);
        aceitarCookiesSeVisivel();
        Thread.sleep(1000);

        try {
            WebElement playBtn = wait.until(ExpectedConditions.elementToBeClickable(playVsRobotBtnLocator));
            playBtn.click();
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
            Thread.sleep(800);

            WebElement visibleContinueBtn = wait.until(d ->
                    d.findElements(continueBtnLocator).stream()
                            .filter(WebElement::isDisplayed)
                            .findFirst()
                            .orElse(null)
            );
            visibleContinueBtn.click();
            Thread.sleep(1500);
        } catch (Exception ignored) {
            // Sessão de visitante já se encontra ativa
        }

        driver.get(MATCH_HISTORY_URL);
        Thread.sleep(1500);
    }

    /**
     * Valida se o elemento principal da página de histórico está presente e visível.
     *
     * @return {@code true} se o cabeçalho estiver visível; {@code false} caso contrário.
     * @throws InterruptedException Caso a pausa de simulação humana seja interrompida.
     */
    public boolean isElementoHistoricoPresente() throws InterruptedException {
        WebElement headerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeadingLocator));
        Thread.sleep(1000);
        return headerElement.isDisplayed();
    }
}