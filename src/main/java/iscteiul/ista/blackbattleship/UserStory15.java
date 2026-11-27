package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Class correspondente à User Story 15:
 * Permite aceder à loja virtual da plataforma PaperGames e navegar até à secção
 * de moedas virtuais (Virtual Coins) para consultar os pacotes disponíveis.
 */
public class UserStory15 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Localizadores baseados no ficheiro TestSuite_129866.side
    private final By cookieConsentButton = By.cssSelector(".fc-cta-consent, .fc-data-preferences-accept-all");
    private final By shopNavLink = By.xpath("//a[contains(@href, '/en/shop')]");
    private final By coinsSectionLink = By.xpath("//a[contains(@href, '/en/shop/virtual-coins')]");

    /**
     * Construtor da classe Page Object iscteiul.ista.blackbattleship.UserStory15.
     *
     * @param driver instância ativa do {@link WebDriver} utilizada para controlar o browser.
     */
    public UserStory15(WebDriver driver) {
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
     * Clica na opção Shop do menu de navegação para aceder à loja virtual.
     */
    public void navigateToShop() {
        WebElement shopLink = wait.until(ExpectedConditions.elementToBeClickable(shopNavLink));
        shopLink.click();
    }

    /**
     * Seleciona a secção Coins para consultar os pacotes de moedas virtuais disponíveis.
     */
    public void selectVirtualCoinsSection() {
        WebElement coinsLink = wait.until(ExpectedConditions.elementToBeClickable(coinsSectionLink));
        coinsLink.click();
    }

    /**
     * Obtém o URL atual do navegador para validar o acesso à página de moedas virtuais.
     *
     * @return string com o URL atual.
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}