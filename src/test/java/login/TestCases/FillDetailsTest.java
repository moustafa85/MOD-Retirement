package login.TestCases;

import AddingNewRequests.addReqDetails;
import Pages.utils.ManipulateDB;
import Pages.utils.TestUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Map;

import static Pages.utils.ExtentManager.createInstance;

public class FillDetailsTest {

    addReqDetails GRObj;
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
        GRObj = new addReqDetails();
    }

    @DataProvider
    public Object[][] getExcelData() throws IOException, InvalidFormatException {
        Object data[][] = TestUtils.dataSupplier("Officer-Cases2023", System.getProperty("user.dir")+"\\QA\\excelData\\RetirementData.xlsx");
        return data;
    }

    @Test(dataProvider="getExcelData",testName = "Prepare-TestData")
    public void prepareDetailsTest(Map<String,String> map) throws IOException
    {
        test = extent.createTest("Prepare Advanced Details");
        test.assignCategory("Advanced-Information");
        Identity = map.get("Identity");
        requestType = map.get("Reason");
        requestNo = GRObj.fillRequestDetails(map);
        //requestNo = GRObj.AddSalaryTemp(map);
        if(requestNo.equals("-1"))
            System.out.println("Cannot Add Request Details: \t"+map.get("ReqNo"));
        System.out.println(requestNo);
    }

    @Test(dataProvider="getExcelData",testName = "Prepare-TestData")
    public void submitRequestTest(Map<String,String> map) throws IOException
    {
        test = extent.createTest("Submit Requests");
        test.assignCategory("Submit Request");
        Identity = map.get("Identity");
        requestType = map.get("Reason");
        requestNo = GRObj.submitRequest(map);

        if(requestNo.equals("-1"))
            System.out.println("Cannot Submit Request Details: \t"+map.get("ReqNo"));

    }

    @Test(dataProvider="getExcelData",testName = "Prepare-TestData")
    public void prepareTemplate(Map<String,String> map) throws IOException
    {
        test = extent.createTest("Prepare Attachment");
        test.assignCategory("Data-Validation");
        Identity = map.get("Identity");
        requestType = map.get("Reason");
        requestNo = GRObj.addAttachmentToRequest(map);
        //requestNo = GRObj.AddSalaryTemp(map);
        if(requestNo.equals("-1"))
            System.out.println("Cannot Add Request Template: \t"+map.get("ReqNo"));
        System.out.println(requestNo);
    }

    @Test(dataProvider="getExcelData",testName = "Prepare-TestData")
    public void validatorSubmit(Map<String,String> map) throws IOException
    {
        test = extent.createTest("Validator Submit");
        test.assignCategory("Data-Validation");
        Identity = map.get("Identity");

        requestNo = GRObj.submitValidatorRequest(map);
        //requestNo = GRObj.AddSalaryTemp(map);
        if(requestNo.equals("-1"))
            System.out.println("Validator cannot submit request : \t"+map.get("ReqNo"));
        System.out.println(requestNo);
    }

    @Test(dataProvider="getExcelData",testName = "Prepare-TestData")
    public void supervisorSubmit(Map<String,String> map) throws IOException
    {
        test = extent.createTest("Officer Submit");
        test.assignCategory("Data-Validation");
        Identity = map.get("Identity");

        requestNo = GRObj.submitSupervisorRequest(map);
        //requestNo = GRObj.AddSalaryTemp(map);
        if(requestNo.equals("-1"))
            System.out.println("Validator cannot submit request : \t"+map.get("ReqNo"));
        System.out.println(requestNo);
    }

    @Test(dataProvider="getExcelData",testName = "Prepare-TestData")
    public void setVacationsValues(Map<String,String> map) throws IOException
    {
        /**
         * loop through file to read request id
         * Query with the request id
         * return three values
         * store the values
         */
        //Step 1
        int rowNum = TestUtils.searchInExcel(System.getProperty("user.dir")+"\\QA\\excelData\\RetirementData.xlsx","Officer-Cases2023" ,map.get("Identity"));
        System.out.println("Request No: \t"+map.get("Identity")+ " found @row:\t "+rowNum);
        if(rowNum < 1 )
            return;
        //Step 2
        int setcolumn = 20;

        for (String token:"TotalRemainingLeaves,TotaEndOfServiceCount,TotalReadyStatus".split(",")) {
            System.out.println("Column Name:"+token);
        String returnValue = ManipulateDB.executeQuery("select * from RequestTotals where RequestId = "+map.get("ReqNo"),token);
        //TotalRemainingLeaves,     TotaEndOfServiceCount, TotalReadyStatus
        if (returnValue.equals("0"))
            returnValue = "غير مستحق";


        TestUtils.ExcelWrite(returnValue,rowNum,setcolumn,"Officer-Cases2023", System.getProperty("user.dir")+"\\QA\\excelData\\RetirementData.xlsx");
        setcolumn +=1;
        if(setcolumn % 23 ==0)
            setcolumn = 20;


        }
    }


    @AfterMethod
    public void close() throws IOException {
        //extent.setSystemInfo(System.getProperty("os.name"), System.getProperty("os.version"));
        extent.flush();

        try{
            if(requestNo.equals("-1"))
                test.fail("Failed to add a request details: \t"+Identity + "\t Requests Type:");
            else
                test.pass("Succeeded to add a request details: \t"+Identity);

        }catch (Exception e){
            e.printStackTrace();
        }
        requestNo = "-1";
        Identity ="";
    }

}
