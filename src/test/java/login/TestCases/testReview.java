package login.TestCases;

import Pages.base.BasePage;
import Pages.utils.TestUtils;
import Pages.utils.Utilities;
import ReviewRequests.requestDetails;
import SearchRequests.searchRequests;
import login.Pages.LoginPage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class testReview extends BasePage{

	public testReview() throws IOException {
		super();
	}
	
	LoginPage loginPage;
	TestUtils utilswrite;
	requestDetails requestsObj;
	Utilities utilize;
	

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		
		loginPage = new LoginPage();
		utilswrite = new TestUtils();
		requestsObj = new requestDetails();
		utilize = new Utilities();
	}
	
	@DataProvider
	public Object[][] getExcelData() throws IOException, InvalidFormatException{
		Object data[][] = TestUtils.dataSupplier("Officer-Cases2023", System.getProperty("user.dir")+"\\QA\\excelData\\RetirementData.xlsx");
		return data;
	}

	//@testrail(id = "16647,16935")
	@Test(dataProvider="getExcelData")
	public void testSearchByReqID(Map<String,String> map) throws InterruptedException, IOException, AWTException, SQLException, ClassNotFoundException {

		driver.navigate().refresh();
		test = extent.createTest("Officers-searchRequests");
		test.assignCategory("View Request Details");

		driver.get(prop.getProperty("Origin")+ "/Request/Inbox");

		if(requestsObj.getRequest(map)) {
			System.out.println(prop.getProperty("Origin")+"/RetirmentRequest/Details/"+map.get("ReqNo"));
			driver.get(prop.getProperty("Origin")+"/RetirmentRequest/Details/"+map.get("ReqNo"));
			if (requestsObj.viewRequest(map))
				test.pass("Succeeded in searching Request ID: \t" + map.get("ReqNo") + "\t Requests Type:" + map.get("Reason"));
		}else
			test.fail("Failed for searching Request ID: \t"+map.get("ReqNo") + "\t Requests Type:"+map.get("Reason"));

		//Thread.sleep(3000);
	}
}
