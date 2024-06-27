package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends TestSelenium{

    @FindBy(id = "input-email")
    WebElement email;

    @FindBy(id = "input-password")
    WebElement password;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement loginbutton;

    @FindBy(xpath = "//div[contains(text(),'Warning: No match for E-Mail Address')]")
    WebElement errorMsg;

    WebDriver driver = initializeDriver();

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String uname) {
        email.sendKeys(uname);
    }

    public void enterPassword(String pwd) {
        password.sendKeys(pwd);
    }

    public void clickOnLogin() {
        loginbutton.click();
       // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    }

    public boolean errorMessageDisplayed() {
        return errorMsg.isDisplayed();
    }


}
