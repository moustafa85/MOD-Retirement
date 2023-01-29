package users.Pages;

import java.io.IOException;

import Pages.utils.AllMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Pages.base.BasePage;

public class DeactivateUserPage extends BasePage {
	AllMethods allMethods;

	public DeactivateUserPage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
		allMethods = new AllMethods(driver);
	}
	
	@FindBy(xpath = "(//button[contains(@role,'switch')])[1]")
	WebElement deactivate;
	
	public void Deactivate() {
		allMethods.VerifyElementDisplay(deactivate);
		deactivate.click();
	}
	
}
