package ReviewRequests;

import Pages.base.BasePage;
import Pages.utils.AllMethods;
import org.apache.groovy.parser.antlr4.GroovyLexer;
import org.openqa.selenium.By;
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

public class requestDetails extends BasePage
{
    AllMethods allMethods;


    @FindBy(xpath = "(//input[@id='search-by-number'])[1]")
    WebElement searchByRequest;

    @FindBy(xpath = "//button[contains(text(),'تصفية')]")
    WebElement searchBTN;

    @FindBy(xpath = "(//input[@id='search-by-number'])[2]")
    WebElement searchByIdentity;

    @FindBy(xpath = "//tbody//tr//td[1]")
    WebElement identity;


    @FindBy(xpath = "//div[@id='basicInfoCollapse']//div[@class='row g-3']//div[2]//p[1]")
    WebElement ReqIdentity;

    @FindBy(xpath="//a[@id='basic-info-tab']")
    WebElement Page1;

    @FindBy(xpath = "//button[contains(text(),'البيانات الأساسية')]")
    WebElement basicInfo;

    @FindBy(xpath = "//button[contains(text(),'بيانات السكن والتواصل')]")
    WebElement addressInfo;

    @FindBy(xpath = "//button[contains(text(),'بيانات البنك')]")
    WebElement bankInfo;

    public requestDetails() throws IOException {
        super();
        PageFactory.initElements(driver, this);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 5);
        allMethods = new AllMethods(driver);
    }

    public Boolean getRequest(Map<String, String> map) throws InterruptedException {


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

        System.out.println("Identity Details:"+identity.getText() +"---\t"+map.get("Identity"));

        if (!identity.getText().equals(map.get("ReqNo")))
            return false;

        return true;
    }

    public Boolean viewRequest(Map<String,String> map) throws InterruptedException {
        Wait wait = new WebDriverWait(driver, 500);
        try {
            wait.until(ExpectedConditions.visibilityOf(Page1));

        } catch (NoSuchElementException e) {
            return false;
        }

        Page1.click();
        Thread.sleep(2000);
        newScroll(addressInfo);
        System.out.println( addressInfo.isEnabled());;
        //wait.until(ExpectedConditions.visibilityOf(addressInfo));
        //driver.findElement(By.xpath("//button[contains(text(),'بيانات السكن والتواصل')]")).click();
        clickBTN(addressInfo);//.click();
        Thread.sleep(2000);
        newScroll(bankInfo);
        System.out.println( bankInfo.isEnabled());;
        clickBTN(bankInfo);
        //bankInfo.click();
        //verify elements in Request
        Thread.sleep(2000);
        return true;
    }

    public Boolean isItemFound(WebElement element) {
        return allMethods.VerifyElementDisplay(element);
    }
}
