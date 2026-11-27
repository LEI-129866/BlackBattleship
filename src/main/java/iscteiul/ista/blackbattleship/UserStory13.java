package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Page Object Class correspondente à User Story 13:
 * Permite aceder à secção de criação de torneios na plataforma PaperGames,
 * preencher os dados do torneio, submeter a criação e validar a janela modal resultante.
 */
public class UserStory13 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Localizadores baseados no ficheiro TestSuite_129866.side
    private final By cookieConsentButton = By.cssSelector(".fc-cta-consent, .fc-data-preferences-accept-all");
    private final By createTournamentNavOption = By.xpath("//a[contains(@href, 'create-tournament') or contains(@href, 'tournament')]");
    private final By tournamentNameInput = By.cssSelector("input");
    private final By createAndShareButton = By.xpath("//button[contains(., 'Create and share')]");
    private final By signUpDialogContainer = By.cssSelector("app-sign-up-dialog, mat-dialog-container");
    private final By closeDialogButton = By.cssSelector(".dialog-close button, .dialog-close .mat-mdc-button-touch-target");

    /**
     * Construtor da classe Page Object UserStory13.
     *
     * @param driver instância ativa do {@link WebDriver} utilizada para controlar o browser.
     */
    public UserStory13(WebDriver driver) {
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement consentBtn = shortWait.until(ExpectedConditions.elementToBeClickable(cookieConsentButton));
            consentBtn.click();
        } catch (Exception ignored) {
            // A janela de cookies não apareceu nesta sessão
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }

    /**
     * Navega até à página de criação de torneios através da ligação presente na plataforma.
     */
    public void navigateToCreateTournament() {
        WebElement tournamentLink = wait.until(ExpectedConditions.presenceOfElementLocated(createTournamentNavOption));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tournamentLink);
    }

    /**
     * Garante que o nome do torneio está preenchido para ativar o botão e clica em Create and share.
     */
    public void clickCreateAndShareTournament() {
        wait.until(ExpectedConditions.urlContains("create-tournament"));

        List<WebElement> inputs = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tournamentNameInput));
        for (WebElement input : inputs) {
            if (input.isDisplayed() && input.isEnabled()) {
                input.clear();
                input.sendKeys("Torneio129866");
                break;
            }
        }

        WebElement createBtn = wait.until(ExpectedConditions.elementToBeClickable(createAndShareButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", createBtn);
        createBtn.click();
    }

    /**
     * Verifica se a janela modal de autenticação/registo é apresentada após tentar criar o torneio.
     *
     * @return {@code true} se a janela modal estiver visível; {@code false} caso contrário.
     */
    public boolean isSignUpModalDisplayed() {
        try {
            WebElement dialog = wait.until(ExpectedConditions.visibilityOfElementLocated(signUpDialogContainer));
            return dialog.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Fecha a janela modal apresentada pelo sistema.
     */
    public void closeSignUpModal() {
        WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(closeDialogButton));
        closeBtn.click();
    }
}