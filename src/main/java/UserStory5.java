import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object Class para o cenário de teste {@code UserStoryTest5}
 * (ficheiro {@code TestSuite_129846.side}).
 * <p>
 * Encapsula os localizadores semânticos, esperas explícitas e pausas de interação
 * humana para iniciar uma partida de Batalha Naval contra o robot.
 * </p>
 */
public class UserStory5 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cookieConsentButton = By.cssSelector(".fc-cta-consent");
    private final By cookieOverlay = By.cssSelector(".fc-dialog-overlay");
    private final By battleshipGameCard = By.cssSelector("img[alt='Batalha Naval']");
    private final By playAgainstRobotButton = By.xpath("//button[contains(., 'robô') or contains(., 'computador')]");
    private final By nicknameInput = By.cssSelector(".input-xl");
    private final By confirmNicknameButton = By.cssSelector(".p-3 > .btn");
    private final By playerShipsHeader = By.xpath("//*[normalize-space(text())='Seus navios']");
    /**
     * Constrói uma nova instância de {@code UserStory5}.
     *
     * @param driver instância ativa do {@link WebDriver} que controla o navegador
     */
    public UserStory5(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Abre a página inicial do Papergames ({@code /pt-br/}) e fecha o diálogo
     * de consentimento de cookies caso seja apresentado.
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
     * Inicia uma partida contra o robot e preenche o nickname caso o navegador
     * esteja numa sessão limpa sem perfil guardado.
     *
     * @param nickname nome de jogador a utilizar se solicitado
     */
    public void startGameAgainstRobot(String nickname) {
        wait.until(ExpectedConditions.elementToBeClickable(playAgainstRobotButton)).click();
        humanPause(1200);
        enterNicknameIfPrompted(nickname);
    }

    /**
     * Desloca a visualização para o tabuleiro do jogador e obtém o texto
     * do cabeçalho da secção onde os navios são posicionados.
     *
     * @return texto do cabeçalho do tabuleiro do jogador
     */
    public String getPlayerShipsHeaderText() {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(playerShipsHeader));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", header);
        humanPause(2000);
        return header.getText();
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
     * Preenche e confirma o nickname caso a janela de identificação seja apresentada.
     *
     * @param nickname nome de jogador a introduzir
     */
    private void enterNicknameIfPrompted(String nickname) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement input = shortWait.until(ExpectedConditions.visibilityOfElementLocated(nicknameInput));
            input.click();
            humanPause(800);
            input.sendKeys(nickname);
            humanPause(1000);
            List<WebElement> confirmButtons = driver.findElements(confirmNicknameButton);
            if (!confirmButtons.isEmpty() && confirmButtons.get(0).isDisplayed()) {
                confirmButtons.get(0).click();
            } else {
                input.sendKeys(Keys.ENTER);
            }
            humanPause(1500);
        } catch (Exception ignored) {
            // O perfil de visitante já está ativo na sessão
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