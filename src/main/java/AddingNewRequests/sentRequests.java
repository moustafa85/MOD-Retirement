package AddingNewRequests;

import Pages.base.BasePage;
import Pages.utils.AllMethods;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Map;

public class sentRequests extends BasePage
{
    AllMethods allMethods;

    @FindBy(xpath = "//a[@href='/Request/Sent']")
    WebElement SentItems;

    @FindBy(xpath = "//div[@class='col-md-4']//input[@id='search-by-number']")
    WebElement searchByRequest;

    @FindBy(xpath = "//button[@class='btn btn-primary ms-2']")
    WebElement searchBTN;

    @FindBy(xpath = "//tbody//tr//td[3]")
    WebElement identity;

    @FindBy(xpath = "//tbody/tr[1]/td[5]")
    WebElement requestTypelbl;

    @FindBy(xpath = "//tbody/tr[2]/td[8]/a[1]//*[name()='svg']")
    WebElement requestDetailsbtn;

    public sentRequests() throws IOException {
        super();
        PageFactory.initElements(driver, this);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 5);
        allMethods = new AllMethods(driver);
    }

    public Boolean searchByRequestID(Map<String, String> map) throws InterruptedException {

        newScroll(SentItems);
        Wait wait = new WebDriverWait(driver, 500);
        try {
            wait.until(ExpectedConditions.visibilityOf(searchByRequest));

        } catch (NoSuchElementException e) {
            return false;
        }

        searchByRequest.click();
        searchByRequest.sendKeys(map.get("ReqNo"));
        searchBTN.click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(identity));
        String reqIdentity = identity.getText();
        System.out.println("Request Details:"+reqIdentity);
        String requestType = requestTypelbl.getText();

        //wait.until(ExpectedConditions.visibilityOf(requestDetailsbtn));

        if (reqIdentity.equals(map.get("Identity")) && requestType.equals(map.get("Reason")))
            return true;
        else
            return false;
    }

    public Boolean isItemFound(WebElement element) {
        return allMethods.VerifyElementDisplay(element);
    }
}
