package login.TestCases;

import AddingNewRequests.Officers;
import AddingNewRequests.generateRequests;
import Pages.base.BasePage;
import Pages.utils.TestUtils;
import Pages.utils.Utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import login.Pages.LoginPage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Map;

import static Pages.utils.ExtentManager.createInstance;

public class generateRequestsTest  {

    generateRequests GRObj;
    public static ExtentReports extent;
    public static ExtentTest test;
    String requestNo;
    String Identity;
    String requestType;

    @BeforeSuite
    public void initializeGenerateRequestsTest() throws IOException {
        extent = createInstance();
        requestNo = "-1";
        requestType ="";
        Identity="";
    }



    @BeforeMethod
    public void setup() throws IOException {
        GRObj = new generateRequests();
    }

    @DataProvider
    public Object[][] getExcelData() throws IOException, InvalidFormatException {
        Object data[][] = TestUtils.dataSupplier("Officer-Cases2023", System.getProperty("user.dir")+"\\QA\\excelData\\RetirementData.xlsx");
        return data;
    }

    @Test(dataProvider="getExcelData",testName = "Prepare-TestData")
    public void generateTest(Map<String,String> map) throws IOException
    {
        test = extent.createTest("Prepare Testing Data");
        test.assignCategory("Basic-Information");
        Identity = map.get("Identity");
        requestType = map.get("Reason");
        requestNo = GRObj.generateAPIRequests(map);
        if(requestNo.equals("-1"))
            System.out.println("No request generated");
        else
        {
            int rowNum = TestUtils.searchInExcel(System.getProperty("user.dir")+"\\QA\\excelData\\RetirementData.xlsx","Officer-Cases2023" ,map.get("Identity"));
            System.out.println("Identity: \t"+Identity+ " found @row:\t "+rowNum);
            TestUtils.ExcelWrite(requestNo,rowNum,14,"Officer-Cases2023", System.getProperty("user.dir")+"\\QA\\excelData\\RetirementData.xlsx");
        }
    }
    @AfterMethod
    public void close() throws IOException {
        //extent.setSystemInfo(System.getProperty("os.name"), System.getProperty("os.version"));
        extent.flush();

        try{
            if(requestNo.equals("-1"))
                test.fail("Failed to create a request for identity: \t"+Identity + "\t Requests Type:");
            else
                test.pass("Succeeded to create a request for identity: \t"+Identity);

        }catch (Exception e){
            e.printStackTrace();
        }
        requestNo = "-1";
        Identity ="";
    }

}
