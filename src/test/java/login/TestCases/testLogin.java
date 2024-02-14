package login.TestCases;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages.base.BasePage;
import Pages.utils.TestUtils;
import Pages.utils.Utilities;
import login.Pages.LoginPage;
import AddingNewRequests.Officers;

public class testLogin extends BasePage{

	public testLogin() throws IOException {
		super();
	}
	
	LoginPage loginPage;
	TestUtils utilswrite;
	Officers officersObj;
	Utilities utilize;
	

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		
		loginPage = new LoginPage();
		utilswrite = new TestUtils();
		officersObj = new Officers();
		utilize = new Utilities();
	}
	
	@DataProvider
	public Object[][] getExcelData() throws IOException, InvalidFormatException{
		Object data[][] = TestUtils.dataSupplier("Officer-Cases2023", System.getProperty("user.dir")+"\\QA\\"+prop.getProperty("OfficersFilePath"));
		return data;
	}

	//@testrail(id = "16647,16935")
	@Test(dataProvider="getExcelData")
	public void OfficerTesting(Map<String,String> map) throws InterruptedException, IOException, AWTException, SQLException, ClassNotFoundException {

		driver.navigate().refresh();
		test = extent.createTest("Officers");
		test.assignCategory("Officers");
		driver.get(prop.getProperty("Origin")+"/RetirmentRequest/Basic");
		officersObj.insertOfficerRequestData(map);

	}

}
