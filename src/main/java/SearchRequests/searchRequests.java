package SearchRequests;

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

public class searchRequests extends BasePage
{
    AllMethods allMethods;


    @FindBy(xpath = "//input[@id='search-by-number'][@placeholder='البحث برقم الهوية']")
    WebElement searchByRequest;

    @FindBy(xpath = "//input[@id='search-by-number'][@placeholder='البحث برقم الطلب']")
    WebElement searchByID;

    @FindBy(xpath = "(//select[@name='type']")
    WebElement searchByType;

    @FindBy(xpath = "//button[contains(text(),'بحث')]")
    WebElement searchBTN;

    @FindBy(xpath = "//tbody//tr//td[1]")
    WebElement requestNo;

    @FindBy(xpath = "//tbody//tr//td[3]")
    WebElement identity;

    @FindBy(xpath = "//tbody//tr//td[5]")
    WebElement requestTypelbl;

    @FindBy(xpath = "//tbody//tr//td//a")
    WebElement requestDetailsbtn;

    public searchRequests() throws IOException {
        super();
        PageFactory.initElements(driver, this);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 5);
        allMethods = new AllMethods(driver);
    }

    public Boolean searchByRequestID(Map<String, String> map) throws InterruptedException {

        newScroll(searchBTN);
        Wait wait = new WebDriverWait(driver, 500);
        try {
            wait.until(ExpectedConditions.visibilityOf(searchByRequest));

        } catch (NoSuchElementException e) {
            return false;
        }

        searchByID.click();
        searchByID.sendKeys(map.get("ReqNo"));
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

    public Boolean searchByIdentity(Map<String, String> map) throws InterruptedException {

        newScroll(searchBTN);
        Wait wait = new WebDriverWait(driver, 500);
        try {
            wait.until(ExpectedConditions.visibilityOf(searchByID));

        } catch (NoSuchElementException e) {
            return false;
        }

        searchByRequest.click();
        searchByRequest.sendKeys(map.get("Identity"));
        searchBTN.click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(requestNo));
        String reqNo = requestNo.getText();
        System.out.println("Request Details:"+requestNo);
        String requestType = requestTypelbl.getText();

        //wait.until(ExpectedConditions.visibilityOf(requestDetailsbtn));

        if (requestNo.equals(map.get("ReqNo")) && requestType.equals(map.get("Reason")))
            return true;
        else
            return false;
    }

    public Boolean isItemFound(WebElement element) {
        return allMethods.VerifyElementDisplay(element);
    }
}
