package iscteiul.ista.blackbattleship;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object Class para a User Story 7: Gerar convite privado numa sala de Batalha Naval.
 */
public class UserStory7 {

    private final WebDriver driver;

    @FindBy(xpath = "//button[contains(., 'friend') or contains(., 'Friend') or contains(., 'amigo') or contains(@class, 'friend')]")
    private WebElement playWithFriendButton;

    @FindBy(css = "main")
    private WebElement gameContainer;

    /**
     * Construtor da página de Batalha Naval.
     *
     * @param driver instância do WebDriver
     */
    public UserStory7(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Navega para a página principal do jogo Battleship.
     */
    public void openBattleshipPage() {
        driver.get("https://papergames.io/en/battleship");
    }

    /**
     * Clica na opção de jogar com um amigo para gerar sala/convite.
     */
    public void clickPlayWithFriend() {
        playWithFriendButton.click();
    }

    /**
     * Verifica se a área do jogo/sala carregou.
     *
     * @return true se o contentor estiver visível
     */
    public boolean isGameAreaVisible() {
        return gameContainer.isDisplayed();
    }
}
