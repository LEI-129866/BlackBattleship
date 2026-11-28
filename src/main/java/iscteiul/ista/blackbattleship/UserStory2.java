package iscteiul.ista.blackbattleship;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object Class para a User Story 2: Aceder à loja virtual de avatares no Papergames.io.
 */
public class UserStory2 {

    private final WebDriver driver;

    @FindBy(css = "a[href*='/shop/avatars'], a[href*='/shop']")
    private WebElement shopLink;

    @FindBy(css = "h1, .shop-title, main")
    private WebElement pageContent;

    /**
     * Construtor que inicializa os elementos da página através do PageFactory.
     *
     * @param driver instância do WebDriver
     */
    public UserStory2(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Abre diretamente a página da loja de avatares.
     */
    public void openAvatarsShop() {
        driver.get("https://papergames.io/en/shop/avatars");
    }

    /**
     * Verifica se o conteúdo da loja está visível.
     *
     * @return true se o conteúdo principal estiver visível
     */
    public boolean isShopDisplayed() {
        return pageContent.isDisplayed();
    }
}