import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Class responsável pelas interações na página de gestão de amigos
 * da plataforma Papergames (User Story 12).
 *
 * @author 131589
 */
public class UserStory12 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final String HOME_URL = "https://papergames.io/en/battleship";
    private static final String FRIENDS_URL = "https://papergames.io/en/friends";

    private final By playVsRobotBtnLocator = By.cssSelector(".w-100:nth-child(2) > .btn > .front");
    private final By nicknameInputLocator = By.cssSelector("input[placeholder='Nickname']");
    private final By continueBtnLocator = By.xpath("//button[contains(.,'Continue')]");
    private final By pageHeadingLocator = By.cssSelector("h1, h2, .mb-0");

    /**
     * Construtor da classe Page Object UserStory12.
     *
     * @param driver Instância do Selenium WebDriver utilizada para controlar o browser.
     */
    public UserStory12(WebDriver driver) {
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
     * e acede de seguida à página de gestão de amigos.
     *
     * @throws InterruptedException Caso a pausa de simulação humana seja interrompida.
     */
    public void abrirPaginaAmigos() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(1295, 760));
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

        driver.get(FRIENDS_URL);
        Thread.sleep(1500);
    }

    /**
     * Valida que o cabeçalho da secção de amigos está presente e visível.
     *
     * @return {@code true} se a secção de amigos estiver visível; {@code false} caso contrário.
     * @throws InterruptedException Caso a pausa de simulação humana seja interrompida.
     */
    public boolean isSecaoAmigosPresente() throws InterruptedException {
        WebElement headerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeadingLocator));
        Thread.sleep(1000);
        return headerElement.isDisplayed();
    }
}