package login.TestCases;

import Pages.base.BasePage;
import Pages.utils.TestUtils;
import Pages.utils.Utilities;
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

public class testSearch extends BasePage{

	public testSearch() throws IOException {
		super();
	}
	
	LoginPage loginPage;
	TestUtils utilswrite;
	searchRequests requestsObj;
	Utilities utilize;
	

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		
		loginPage = new LoginPage();
		utilswrite = new TestUtils();
		requestsObj = new searchRequests();
		utilize = new Utilities();
	}
	
	@DataProvider
	public Object[][] getExcelData() throws IOException, InvalidFormatException{
		Object data[][] = TestUtils.dataSupplier("Officer-Cases2023", System.getProperty("user.dir")+"\\QA\\excelData\\RetirementData.xlsx");
		return data;
	}

	//@testrail(id = "16647,16935")
	//@Test(dataProvider="getExcelData")
	public void testSearchByReqID(Map<String,String> map) throws InterruptedException, IOException, AWTException, SQLException, ClassNotFoundException {

		driver.navigate().refresh();
		test = extent.createTest("Officers-searchRequests");
		test.assignCategory("SentRequests");

		driver.get(prop.getProperty("Origin")+ "/Request/Inquire");

		if(requestsObj.searchByRequestID(map))
			test.pass("Succeeded in searching Request ID: \t"+map.get("ReqNo") + "\t Requests Type:"+map.get("Reason"));
		else
			test.fail("Failed for searching Request ID: \t"+map.get("ReqNo") + "\t Requests Type:"+map.get("Reason"));

		System.out.println(prop.getProperty("Origin")+"/RetirmentRequest/Details/"+map.get("ReqNo"));
		driver.get(prop.getProperty("Origin")+"/RetirmentRequest/Details/"+map.get("ReqNo"));
		//Thread.sleep(3000);
	}

	@Test(dataProvider="getExcelData")
	public void testSearchByIdentity(Map<String,String> map) throws InterruptedException, IOException, AWTException, SQLException, ClassNotFoundException {

		driver.navigate().refresh();
		test = extent.createTest("Officers-searchRequests");
		test.assignCategory("SentRequests");

		driver.get(prop.getProperty("Origin")+ "/Request/Inquire");

		if(requestsObj.searchByIdentity(map))
			test.pass("Succeeded in searching Request ID: \t"+map.get("ReqNo") + "\t Requests Type:"+map.get("Reason"));
		else
			test.fail("Failed for searching Request ID: \t"+map.get("ReqNo") + "\t Requests Type:"+map.get("Reason"));

		System.out.println(prop.getProperty("Origin")+"/RetirmentRequest/Details/"+map.get("ReqNo"));
		driver.get(prop.getProperty("Origin")+"/RetirmentRequest/Details/"+map.get("ReqNo"));
		Thread.sleep(3000);
	}
}
