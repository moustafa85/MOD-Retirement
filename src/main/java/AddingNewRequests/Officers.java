package AddingNewRequests;

import Pages.base.BasePage;
import Pages.utils.AllMethods;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class Officers extends BasePage {

    AllMethods allMethods;

    @FindBy(xpath = "//input[@aria-placeholder='اختر سبب نهاية الخدمة']")
    WebElement requestType;

    @FindBy(id = "firstName")
    WebElement firstName;

    @FindBy(xpath = "//input[@id='familyName']")
    WebElement familyName;

    @FindBy(id = "fatherName")
    WebElement secondName;

    @FindBy(id = "grandfatherName")
    WebElement grandfatherName;


    @FindBy(id = "birthDate")
    WebElement birthDate;

    @FindBy(id = "identityNo")
    WebElement identityNo;

    //@FindBy(css = "input[aria-placeholder='اختر الرتبة الحالية']")
    @FindBy(xpath = "//input[@aria-placeholder='اختر الرتبة الحالية']")
    WebElement Rank;

    @FindBy(css = "input[aria-placeholder='اختر مكان الولادة']")
    WebElement birthArea;

    @FindBy(xpath = "//input[@aria-placeholder='اختر مدينة الولادة']")
    WebElement birthCity;


    @FindBy(id = "pilotCheck")
    WebElement pilotCheck;

    @FindBy(id = "military-number")
    WebElement militarynumber;

    @FindBy(xpath = "//input[@aria-placeholder='اختر الجهة']")
    WebElement txtSideId;

    @FindBy(xpath = "//input[@aria-placeholder='اختر الوحدة']")
    WebElement unitDetails;

    @FindBy(id = "serviceStartDateHijri")
    WebElement serviceStartDateHijri;

    @FindBy(id = "serviceEndDateHijri")
    WebElement serviceEndDateHijri;

    @FindBy(id = "dateDecision")
    WebElement dateDecision;

    @FindBy(css="div[class='row g-3 mt-1'] select[class='form-select']")
    WebElement gendar;


    @FindBy(id = "incomingTransactionDate")
    WebElement incomingTransactionDate;

    @FindBy(xpath = "//*[@id=\"basicInfoCollapse\"]/div/div[2]/div[1]/input")
    WebElement disabilityRatio;

    @FindBy(id = "job")
    WebElement job;

    @FindBy(css = ".form-control[maxlength='9']")
    WebElement incomingTransactionNumber;

    @FindBy(id = "notes")
    WebElement notes;

    @FindBy(xpath = "(//input[@role='combobox'])[8]")
    WebElement area;
    //
    @FindBy(xpath = "(//input[@role='combobox'])[9]")
    WebElement city;

    @FindBy(id = "phone-number")
    WebElement phoneNum;

    ////div[@id='bankInfoCollapse']//div[@class='row g-3']//div[1]//div[1]//div[1]//input[1]
    @FindBy(xpath = "(//input[@role='combobox'])[10]")
    WebElement paymentCity;

    @FindBy(xpath = "(//input[@role='combobox'])[11]")
    WebElement pensionBank;

    @FindBy(xpath = "//input[@class='form-control txtiban']")
    WebElement IBAN;

    @FindBy(xpath = "//button[contains(text(),'إرســال')]")
    WebElement submitButton;

    @FindBy(xpath = "//button[contains(text(),'حفــظ كمسودة')]")
    WebElement saveAsDraft;

    public Officers() throws IOException {
        super();
        PageFactory.initElements(driver, this);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 5);
        allMethods = new AllMethods(driver);
    }



    public Boolean insertOfficerRequestData(Map<String, String> map) throws InterruptedException, IOException, ClassNotFoundException, SQLException {
        try {
            //wait till page is opened
            scrollHigh();
            Wait wait = new WebDriverWait(driver, 500);
            try {
                wait.until(ExpectedConditions.visibilityOf(saveAsDraft));

            } catch (NoSuchElementException e) {
                return false;
            }
            if (!isItemFound(requestType))
                return false;

            newScroll(requestType);
            setSelectVal(requestType,map.get("Reason"));

            setSelectVal(birthArea,"السعودية");
            wait.until(ExpectedConditions.visibilityOf(birthCity));

            setSelectVal(birthCity,"الرياض");
            setSelectVal(gendar,"ذكر");

            newScroll(Rank);
            setSelectVal(Rank,map.get("Rank"));

            newScroll(area);
            wait.until(ExpectedConditions.visibilityOf(area));
            setSelectbyVal(area,"منطقة الرياض");
            wait.until(ExpectedConditions.visibilityOf(city));
            setSelectbyVal(city,"الرياض");
            wait.until(ExpectedConditions.visibilityOf(txtSideId));
            newScroll(txtSideId);
            setSelectbyVal(txtSideId,"القوات الجويه");
            setSelectbyVal(unitDetails,"الوحدة الاولى");

            newScroll(paymentCity);
            //(//input[@role='combobox'])[9]
            setSelectbyVal(paymentCity,"الرياض");

            setSelectbyVal(pensionBank,"بنك الخليج");

            firstName.sendKeys(Keys.TAB);
            wait.until(ExpectedConditions.visibilityOf(firstName));
            newScroll(firstName);
            //setInputValue(firstName,map.get("Reason"));
            setRestrictedFieldID(firstName,map.get("Reason"));
            setRestrictedFieldID(secondName,map.get("secondName"));
            setRestrictedFieldID(familyName,map.get("familyName"));
            setRestrictedFieldID(grandfatherName,map.get("forthName"));

            setRestrictedFieldID(birthDate,map.get("DOB"));
            setRestrictedFieldID(identityNo,map.get("Identity"));
            setRestrictedFieldID(militarynumber,map.get("Identity"));

            newScroll(birthCity);
            //birthCity.sendKeys(Keys.TAB);

            setRestrictedFieldID(serviceStartDateHijri,map.get("StartDate"));
            setRestrictedFieldID(serviceEndDateHijri,map.get("EndDate"));
            //


            newScroll(dateDecision);
            setRestrictedFieldID(dateDecision,map.get("EndDate"));
            setRestrictedFieldID(incomingTransactionDate,map.get("EndDate"));


            if(map.get("Reason").trim().equalsIgnoreCase("العجز الطبي"))
                setRestrictedFieldID(disabilityRatio,"60");


            newScroll(incomingTransactionNumber);

            setRestrictedFieldID(incomingTransactionNumber,"2999200");
            setRestrictedFieldID(job,"مدير عام القسم");
            setRestrictedFieldID(notes, "By Automation Suite");

            newScroll(phoneNum);
            setRestrictedFieldID(phoneNum,map.get("phoneNum"));

            newScroll(IBAN);
            setRestrictedFieldID(IBAN,"0380000000608010167519");

            Thread.sleep(20000);

            //submitButton

            String output = "TerminationID: " + map.get("TerminationID") + " \t " +"Reason " + map.get("Reason");
            output += "DOB" + map.get("DOB") + " \t " +"StartDate" + map.get("StartDate") + " \t " ;
            output += "Identity" + map.get("Identity") + " \t " +" Name " + map.get("familyName")
                    + " "+map.get("secondName") +""+ map.get("familyName")+"\n";

            System.out.println(""+output);

            return true;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Boolean isItemFound(WebElement element) {
        return allMethods.VerifyElementDisplay(element);
    }

    @Test
    public void test1()
    {

    }
}
