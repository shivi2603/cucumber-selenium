package stepdefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import pages.LoginPage;
import pages.TestSelenium;

import java.io.File;
import java.time.Duration;

public class LoginStepDefinitions {

    public static ExtentTest extentTest;
    public static ExtentReports extentReports;
    LoginPage loginPage;
    private WebDriver driver;

    @Before
    public void setup(Scenario scenario) {
//        System.setProperty("webdriver.chrome.driver","/home/shivani.k/eclipse-workspace/cucumbercrashcourse/chromedriver-linux64");
//        driver = new ChromeDriver();
        if (extentReports == null) {

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/extent-report.html");
            sparkReporter.config().setReportName("Cucumber Execution Report");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
        }

        extentTest = extentReports.createTest(scenario.getName());
        TestSelenium test = new TestSelenium();
        driver = test.initializeDriver();


    }

    @AfterMethod
    public void getscreenShot(Scenario scenario) {
        if (scenario.isFailed()) {
            File file = new File("target/screenshots");
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            file = screenshot.getScreenshotAs(OutputType.FILE);
            extentTest.fail("Step Failed", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
        }
    }


    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            extentTest.log(Status.FAIL, "Scenario failed :  " + scenario.getName());
        } else {
            extentTest.log(Status.PASS, "scenario passed :  " + scenario.getName());
        }

        if (driver != null) {
            driver.quit();
        }
        extentReports.flush();
    }

    @Given("I am on the opencart login page")
    public void i_am_on_the_opencart_login_page() {

        driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
        extentTest.log(Status.INFO, "Opened the url");
        loginPage = new LoginPage(driver);

    }

    @Given("I entered valid username and password")
    public void i_entered_valid_username_and_password() {

        loginPage.enterUsername("testabc@mail.com");
        extentTest.log(Status.INFO, "entered the username");
    }

    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        loginPage.enterPassword("Test@123");

     extentTest.log(Status.INFO,"entered the password");
}

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        loginPage.clickOnLogin();
        extentTest.log(Status.INFO,"clicked on login");

    }


    @Given("I entered invalid {string} and {string}")
    public void i_entered_invalid_and(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @Then("I should get error message indicating {string}")
    public void i_should_get_error_message_indicating(String errorMsg) {
        if(loginPage.errorMessageDisplayed()){

        }


    }


}
