package iscteiul.ista.blackbattleship;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object Class para a User Story 18: Consultar o registo de atualizações (Changelog).
 */
public class UserStory18 {

    private final WebDriver driver;

    @FindBy(css = "main, h1")
    private WebElement changelogHeader;

    /**
     * Construtor da página de Changelog.
     *
     * @param driver instância do WebDriver
     */
    public UserStory18(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Abre a página de registo de atualizações.
     */
    public void openChangelog() {
        driver.get("https://papergames.io/en/changelog");
    }

    /**
     * Verifica se o conteúdo do changelog está visível.
     *
     * @return true se o cabeçalho/conteúdo estiver visível
     */
    public boolean isChangelogDisplayed() {
        return changelogHeader.isDisplayed();
    }
}