import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Class responsável pelas interações nas páginas de planos de subscrição
 * Plus e Gruppo da plataforma Papergames (User Story 16).
 *
 * @author 131589
 */
public class UserStory16 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final String PLUS_URL = "https://papergames.io/en/pricing/plus";
    private static final String GRUPPO_URL = "https://papergames.io/en/pricing/gruppo";

    private final By plusHeaderLocator = By.cssSelector(".h2");
    private final By gruppoHeaderLocator = By.cssSelector(".text-center > .h2");

    /**
     * Construtor da classe Page Object UserStory16.
     *
     * @param driver Instância do Selenium WebDriver utilizada para controlar o browser.
     */
    public UserStory16(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
     * Acede à página de subscrição do plano Plus e ajusta a dimensão da janela do browser.
     *
     * @throws InterruptedException Caso a pausa de simulação humana seja interrompida.
     */
    public void abrirPaginaPlanoPlus() throws InterruptedException {
        driver.get(PLUS_URL);
        driver.manage().window().setSize(new Dimension(1295, 760));
        aceitarCookiesSeVisivel();
        Thread.sleep(1000);
    }

    /**
     * Valida que os detalhes e benefícios do plano Plus estão visíveis na página.
     *
     * @return {@code true} se o cabeçalho do plano Plus estiver visível; {@code false} caso contrário.
     * @throws InterruptedException Caso a pausa de simulação humana seja interrompida.
     */
    public boolean isPlanoPlusVisivel() throws InterruptedException {
        WebElement plusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(plusHeaderLocator));
        Thread.sleep(1000);
        return plusElement.isDisplayed();
    }

    /**
     * Acede à página de subscrição do plano Gruppo.
     *
     * @throws InterruptedException Caso a pausa de simulação humana seja interrompida.
     */
    public void abrirPaginaPlanoGruppo() throws InterruptedException {
        driver.get(GRUPPO_URL);
        aceitarCookiesSeVisivel();
        Thread.sleep(1000);
    }

    /**
     * Valida que os detalhes e benefícios do plano Gruppo estão visíveis na página.
     *
     * @return {@code true} se o cabeçalho do plano Gruppo estiver visível; {@code false} caso contrário.
     * @throws InterruptedException Caso a pausa de simulação humana seja interrompida.
     */
    public boolean isPlanoGruppoVisivel() throws InterruptedException {
        WebElement gruppoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(gruppoHeaderLocator));
        Thread.sleep(1000);
        return gruppoElement.isDisplayed();
    }
}