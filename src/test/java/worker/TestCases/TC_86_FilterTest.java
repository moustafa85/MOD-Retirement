package worker.TestCases;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Map;

import Pages.Testrail.testrail;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages.base.BasePage;
import Pages.utils.TestUtils;
import Pages.utils.Utilities;
import login.Pages.LoginPage;
import workers.pages.Filter;
import workers.pages.WorkerBasePage;

public class TC_86_FilterTest extends BasePage{

	public TC_86_FilterTest() throws IOException {
		super();
	}
	
	LoginPage loginPage;
	TestUtils utilswrite;
	Filter filter;
	WorkerBasePage workerBasePage;
	Utilities utilize;
	

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		
		loginPage = new LoginPage();
		utilswrite = new TestUtils();
		filter = new Filter();
		workerBasePage = new WorkerBasePage();
		utilize = new Utilities();
	}
	
	@DataProvider
	public Object[][] getExcelData() throws IOException, InvalidFormatException{
		Object data[][] = TestUtils.dataSupplier("Filters", System.getProperty("user.dir")+"\\"+prop.getProperty("Environment")+prop.getProperty("WorkerFilePath"));
		return data;
	}

	@Test(dataProvider="getExcelData")
	@testrail(id = "16647,16935")
	public void FilterTesting(Map<String,String> map) throws InterruptedException, IOException, AWTException {


		test = extent.createTest("Workers -- Filters");
		test.assignCategory("Workers");

		loginPage.dashboardLink();
		workerBasePage.WorkersModule();
		filter.Status(map.get("Status"));
		filter.WorkerType(map.get("WorkerType"));
		filter.assignedDevice(map.get("AssignedDevice"));
		filter.Site(map.get("AllowedSite"));
		filter.Availability(map.get("Availability"));
		filter.Download();

	}

}
