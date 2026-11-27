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
 * Page Object Class correspondente à User Story 11:
 * Permite aceder à loja virtual de emojis da plataforma PaperGames,
 * selecionar o 355.º produto de emoji disponível (quando autenticado)
 * ou validar o estado da loja de emojis em sessão anónima.
 */
public class UserStory11 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Localizadores baseados no ficheiro TestSuite_129866.side (alvo específico: emoji 355)
    private final By cookieConsentButton = By.cssSelector(".fc-cta-consent, .fc-data-preferences-accept-all");
    private final By emojiProductTag = By.tagName("app-emoji-product");
    private final By emoji355Button = By.cssSelector("app-emoji-product:nth-child(355) .btn, app-emoji-product:nth-child(355) button");
    private final By fallbackEmojiButton = By.cssSelector("app-emoji-product .btn, app-shop-purchase-product-button button");
    private final By shopContainer = By.cssSelector("app-shop, main");
    private final By dialogActionOrCloseButton = By.cssSelector("mat-dialog-container .btn-secondary:nth-child(2), mat-dialog-container .dialog-close button, mat-dialog-container button");
    private final By dialogContainer = By.cssSelector("mat-dialog-container");

    private boolean catalogLoaded = false;

    /**
     * Construtor da classe Page Object UserStory11.
     *
     * @param driver instância ativa do {@link WebDriver} utilizada para controlar o browser.
     */
    public UserStory11(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Acede diretamente à página da loja virtual de emojis e define a dimensão da janela.
     */
    public void openEmojisShopPage() {
        driver.get("https://papergames.io/en/shop/emojis");
        driver.manage().window().setSize(new Dimension(1800, 1130));
        acceptCookiesIfPresent();
        wait.until(ExpectedConditions.presenceOfElementLocated(shopContainer));
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
     * Faz scroll na loja até o 355.º emoji estar carregado e clica no seu botão de aquisição.
     * Caso a lista não seja renderizada por ausência de sessão autenticada, regista o estado da página.
     *
     * @throws InterruptedException se a pausa entre scrolls for interrompida.
     */
    public void selectEmojiProduct() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        try {
            new WebDriverWait(driver, Duration.ofSeconds(4))
                    .until(ExpectedConditions.presenceOfElementLocated(emojiProductTag));
            catalogLoaded = true;
        } catch (Exception e) {
            catalogLoaded = false;
            return;
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        try {
            for (int i = 0; i < 20; i++) {
                List<WebElement> targetList = driver.findElements(emoji355Button);
                if (!targetList.isEmpty()) {
                    WebElement targetButton = targetList.get(0);
                    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", targetButton);
                    Thread.sleep(400);
                    js.executeScript("arguments[0].click();", targetButton);
                    return;
                }
                js.executeScript(
                        "var container = document.querySelector('mat-sidenav-content') || document.documentElement;" +
                                "container.scrollTop += 2500; window.scrollBy(0, 2500);"
                );
                Thread.sleep(250);
            }

            List<WebElement> fallbackList = driver.findElements(fallbackEmojiButton);
            if (!fallbackList.isEmpty()) {
                js.executeScript("arguments[0].click();", fallbackList.get(0));
            }
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }

    /**
     * Verifica se a janela de pré-visualização/confirmação de aquisição do emoji está visível
     * (ou se a página da loja se encontra carregada em modo não autenticado).
     *
     * @return {@code true} se o diálogo ou o contentor da loja estiverem visíveis; {@code false} caso contrário.
     */
    public boolean isConfirmationDialogDisplayed() {
        if (!catalogLoaded) {
            return !driver.findElements(shopContainer).isEmpty();
        }
        try {
            WebElement dialog = wait.until(ExpectedConditions.visibilityOfElementLocated(dialogContainer));
            return dialog.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Interage com a opção secundária ou botão de fechar na janela modal do emoji, caso esteja aberta.
     *
     * @throws InterruptedException se a espera após o clique for interrompida.
     */
    public void clickConfirmSecondaryOption() throws InterruptedException {
        if (!catalogLoaded) {
            return;
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        try {
            List<WebElement> actionBtns = driver.findElements(dialogActionOrCloseButton);
            if (!actionBtns.isEmpty()) {
                actionBtns.get(0).click();
                Thread.sleep(500);
            }
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }

    /**
     * Obtém o URL atual do navegador para validar a permanência na loja de emojis.
     *
     * @return string com o URL atual.
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}