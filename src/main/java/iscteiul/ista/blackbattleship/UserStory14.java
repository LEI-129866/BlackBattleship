package iscteiul.ista.blackbattleship;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object Class para a User Story 14: Consultar torneios no Papergames.io.
 */
public class UserStory14 {

    private final WebDriver driver;

    @FindBy(css = "main")
    private WebElement tournamentsContainer;

    /**
     * Construtor da página de torneios.
     *
     * @param driver instância do WebDriver
     */
    public UserStory14(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Abre a página da lista de torneios.
     */
    public void openMyTournaments() {
        driver.get("https://papergames.io/en/t/my-tournaments");
    }

    /**
     * Abre a página de um campeonato específico.
     */
    public void openSpecificTournament() {
        driver.get("https://papergames.io/en/t/_Tq2lH3X2");
    }

    /**
     * Verifica se a página de torneios está visível.
     *
     * @return true se o contentor estiver visível
     */
    public boolean isTournamentsDisplayed() {
        return tournamentsContainer.isDisplayed();
    }
}
