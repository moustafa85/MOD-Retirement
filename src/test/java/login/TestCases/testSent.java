package login.TestCases;

import AddingNewRequests.sentRequests;
import Pages.base.BasePage;
import Pages.utils.TestUtils;
import Pages.utils.Utilities;
import login.Pages.LoginPage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class testSent extends BasePage{

	public testSent() throws IOException {
		super();
	}
	
	LoginPage loginPage;
	TestUtils utilswrite;
	sentRequests requestsObj;
	Utilities utilize;
	

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		
		loginPage = new LoginPage();
		utilswrite = new TestUtils();
		requestsObj = new sentRequests();
		utilize = new Utilities();
	}
	
	@DataProvider
	public Object[][] getExcelData() throws IOException, InvalidFormatException{
		Object data[][] = TestUtils.dataSupplier("Officer-Cases2023", System.getProperty("user.dir")+"\\QA\\excelData\\RetirementData.xlsx");
		return data;
	}

	//@testrail(id = "16647,16935")
	@Test(dataProvider="getExcelData")
	public void testSentRequests(Map<String,String> map) throws InterruptedException, IOException, AWTException, SQLException, ClassNotFoundException {

		driver.navigate().refresh();
		test = extent.createTest("Officers-SentRequests");
		test.assignCategory("SentRequests");

		driver.get("http://10.10.2.106:6006/Request/Sent");
		if(requestsObj.searchByRequestID(map))
			test.pass("Succeeded for Request ID: \t"+map.get("ReqNo") + "\t Requests Type:"+map.get("Reason"));
		else
			test.fail("Failed for Request ID: \t"+map.get("ReqNo") + "\t Requests Type:"+map.get("Reason"));

		System.out.println(prop.getProperty("Origin")+"/RetirmentRequest/Details/"+map.get("ReqNo"));

		driver.get(prop.getProperty("Origin")+"/RetirmentRequest/Details/"+map.get("ReqNo"));
		Thread.sleep(3000);
		//http://10.10.2.106:6006
	}
}
