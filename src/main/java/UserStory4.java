import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object Class para o cenário de teste {@code UserStoryTest4}
 * (ficheiro {@code TestSuite_129846.side}).
 * <p>
 * Encapsula os localizadores semânticos, esperas explícitas, deslocamento visual
 * e pausas de interação humana para consultar as regras da Batalha Naval.
 * </p>
 */
public class UserStory4 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cookieConsentButton = By.cssSelector(".fc-cta-consent");
    private final By cookieOverlay = By.cssSelector(".fc-dialog-overlay");
    private final By battleshipGameCard = By.cssSelector("img[alt='Batalha Naval']");
    private final By rulesHeading = By.xpath("//h2[contains(., 'Regras da Batalha Naval')]");

    /**
     * Constrói uma nova instância de {@code UserStory4}.
     *
     * @param driver instância ativa do {@link WebDriver} que controla o navegador
     */
    public UserStory4(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Abre a página inicial do Papergames ({@code /pt-br/}) e fecha o diálogo
     * de consentimento de cookies assim que este surgir no ecrã.
     */
    public void openHomePage() {
        driver.get("https://papergames.io/pt-br/");
        acceptCookiesIfPresent();
        humanPause(1000);
    }

    /**
     * Define o tamanho da janela do navegador.
     *
     * @param width  largura da janela em píxeis
     * @param height altura da janela em píxeis
     */
    public void setWindowSize(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
        humanPause(1000);
    }

    /**
     * Seleciona o jogo Battleship (Batalha Naval) no catálogo da página inicial.
     */
    public void selectBattleshipGame() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(battleshipGameCard)).click();
        } catch (ElementClickInterceptedException e) {
            acceptCookiesIfPresent();
            wait.until(ExpectedConditions.elementToBeClickable(battleshipGameCard)).click();
        }
        humanPause(1500);
    }

    /**
     * Desloca a página até à secção de regras da Batalha Naval e obtém o respetivo título.
     *
     * @return texto do cabeçalho das regras do jogo
     */
    public String getBattleshipRulesTitle() {
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(rulesHeading));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", heading);
        humanPause(2000);
        return heading.getText();
    }

    /**
     * Aguarda até 5 segundos pelo diálogo de consentimento de cookies, aceita-o
     * e espera que a sobreposição desapareça por completo.
     */
    private void acceptCookiesIfPresent() {
        try {
            WebDriverWait cookieWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement consentBtn = cookieWait.until(ExpectedConditions.elementToBeClickable(cookieConsentButton));
            humanPause(800);
            consentBtn.click();
            cookieWait.until(ExpectedConditions.invisibilityOfElementLocated(cookieOverlay));
        } catch (TimeoutException ignored) {
            // O diálogo de cookies não foi apresentado nesta sessão
        }
    }

    /**
     * Introduz uma pausa controlada entre interações para simular o ritmo
     * de navegação e leitura de um utilizador humano na interface gráfica.
     *
     * @param millis duração da pausa em milissegundos
     */
    private void humanPause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}