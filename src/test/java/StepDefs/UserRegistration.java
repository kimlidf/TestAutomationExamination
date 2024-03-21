package StepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertEquals;

public class UserRegistration {
    private WebDriver driver;

    @After
    public void tearDown(){
        if(this.driver != null){
            this.driver.quit();
        }
    }

    @Given("I am on the {string} User Registration Page")
    public void iAmOnTheUserRegistrationPage(String browser) {
        if(browser.equals("Chrome")) {
            driver = new ChromeDriver();

        } else if(browser.equals("Edge")) {
            driver = new EdgeDriver();

        } else if(browser.equals("Firefox")) {
            driver = new FirefoxDriver();

        } else{
            driver = new ChromeDriver(); // use Chrome as default
        }
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @And("I have entered date of birth {string}")
    public void iHaveEnteredDateOfBirth(String dateOfBirth) {
        WebElement datePicker = driver.findElement(By.name("DateOfBirth"));
        datePicker.sendKeys(dateOfBirth);
    }

    @And("I have entered the name {string} and the surname {string}")
    public void iHaveEnteredTheNameAndTheSurname(String firstName, String lastName) {
        WebElement forename = driver.findElement(By.name("Forename"));
        forename.sendKeys(firstName);

        WebElement surname = driver.findElement(By.name("Surname"));
        surname.sendKeys(lastName);
    }

    @And("I have entered email address {string} and confirmed {string}")
    public void iHaveEnteredEmailAddressAndConfirmedEmail(String emailAddress, String confirmedEmailAddress) {
        WebElement email = driver.findElement(By.name("EmailAddress"));
        email.sendKeys(emailAddress);

        WebElement confirmEmail = driver.findElement(By.name("ConfirmEmailAddress"));
        confirmEmail.sendKeys(confirmedEmailAddress);
    }

    @And("I have entered a password {string}")
    public void iHaveEnteredAPassword(String Password) {
        WebElement password = driver.findElement(By.name("Password"));
        password.sendKeys(Password);
    }

    @And("I have confirmed the password {string}")
    public void iHaveConfirmedThePassword(String ConfirmedPassword) {
        WebElement confirmPassword = driver.findElement(By.name("ConfirmPassword"));
        confirmPassword.sendKeys(ConfirmedPassword);
        confirmPassword.click();
    }

    @And("I have accepted the Terms and Conditions")
    public void iHaveAcceptedTheTermsAndConditions() {
        WebElement termsConditionsCheckbox = driver.findElement(By.cssSelector("label[for='sign_up_25']"));
        termsConditionsCheckbox.click();
    }

    @And("I have rejected the Terms and Conditions")
    public void iHaveRejectedTheTermsAndConditions() {
    }

    @And("I have accepted age requirements")
    public void iHaveAcceptedAgeRequirements() {
        WebElement ageVerification = driver.findElement(By.cssSelector("label[for='sign_up_26']"));
        ageVerification.click();
    }

    @And("I have accepted Code of Ethics and Conduct")
    public void iHaveAcceptedCodeOfEthicsAndConduct(){
        WebElement codeConduct = driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']"));
        codeConduct.click();
    }

    @When("I have clicked the button join and confirm")
    public void iHaveClickedTheButtonJoinAndConfirm() {
        clickAndWait(By.name("join"), Duration.ofSeconds(10));
    }

    @Then("my user account is successfully created")
    public void myUserAccountIsSuccessfullyCreated() {
        WebElement signUpConfirmation = driver.findElement(By.cssSelector(".bold.gray.text-center.margin-bottom-40"));

        String actual = signUpConfirmation.getText();
        String expected = "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND";

        assertEquals(expected, actual);

        System.out.println("User account was successfully created.");
    }


    @Then("my user account is missing last name input")
    public void myUserAccountIsMissingLastNameInput() {
        WebElement noSurnameErrorMessage = driver.findElement(By.cssSelector("span.warning.field-validation-error[data-valmsg-for='Surname']"));

        System.out.println(noSurnameErrorMessage.getText());

        String actual = noSurnameErrorMessage.getText();
        String expected = "Last Name is required";

        assertEquals(expected, actual);

        System.out.println("User account was not successfully created due to missing last name input.");
    }


    @Then("my user account is lacking correct password confirmation")
    public void myUserAccountIsLackingCorrectPasswordConfirmation() {
        WebElement incorrectPasswordValidationErrorMessage = driver.findElement(By.cssSelector("span.warning.field-validation-error[data-valmsg-for='ConfirmPassword']"));

        System.out.println(incorrectPasswordValidationErrorMessage.getText());

        String actual = incorrectPasswordValidationErrorMessage.getText();
        String expected = "Password did not match";

        assertEquals(expected, actual);

        System.out.println("User account was not successfully created due to incorrect password validation.");
    }

    @Then("my user account is lacking accepted terms and conditions")
    public void myUserAccountIsLackingAcceptedTermsAndCondition() {
        WebElement rejectionOfTerms = driver.findElement(By.cssSelector("span.warning.field-validation-error[data-valmsg-for='TermsAccept']"));

        System.out.println(rejectionOfTerms.getText());

        String actual = rejectionOfTerms.getText();
        String expected = "You must confirm that you have read and accepted our Terms and Conditions";

        assertEquals(expected, actual);

        System.out.println("User account was not successfully created because terms were not accepted.");
    }

    private void clickAndWait(By by, Duration duration){
        if (this.driver == null) {
            return;
        }
        WebDriverWait wait = new WebDriverWait(driver, duration);

        WebElement joinButton = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        joinButton.click();
    }
}

