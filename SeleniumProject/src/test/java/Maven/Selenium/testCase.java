package Maven.Selenium;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;

import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

@Listeners(Maven.Selenium.listenerCode.class)

public class testCase {

	pageObject p;
	Select s;
	SoftAssert st;

	public static String projectPath = System.getProperty("user.dir");

	@BeforeTest
	public void beforeTest() {

		pageObject.browserCode("chrome");
		p = new pageObject();
		st = new SoftAssert();

	}

	@Test(priority = 0, description = "Test case to verify departure from and to city is selected.")
	public void TC01_Fligth_Page() throws Exception {

		p.excelCode(projectPath + "\\Project_Assignment.xlsx", 0);
		String from = p.st.getRow(1).getCell(0).toString();
		String to = p.st.getRow(1).getCell(1).toString();

		p.openUrl();

		s = new Select(p.fromPort);
		s.selectByVisibleText(from);

		s = new Select(p.toPort);
		s.selectByVisibleText(to);
		Thread.sleep(2000);

	}

	@Test(priority = 1, description = "Test case to validate Flight 'From' and 'To' information")
	public void TC02_Flight_Selection_Page() throws Exception {

		p.findFlight.click();
		Thread.sleep(2000);

		st.assertTrue(p.labeltoVerify.getText().contains("London"), "to City Names are different");
		st.assertTrue(p.labeltoVerify.getText().contains("Paris"), "from City Names are different");

		st.assertAll();

	}

	@Test(priority = 2, description = "Test case to verify prices amount and Total Cost")
	public void TC03_Validation_Price_Amount() throws Exception {

		Thread.sleep(2000);
		
		double price=p.getNumber1(p.price);

		p.ChooseFlight.click();

		Thread.sleep(2000);

		st.assertEquals(price, p.getNumber(p.validatePrice));

		double cal_Total=p.getNumber(p.validatePrice) + p.getNumber(p.Arbitrary_Fees_and_Taxes) ;

		System.out.println(p.Total_Cost.getText());
		st.assertEquals(cal_Total,p.getNumber(p.Total_Cost));
		
		
		
		st.assertAll();

	}

	@Test(priority = 3, description = "Test case to Fill Information from Excel file")
	public void TC04_Presonal_Info() throws Exception {
		p.excelCode(projectPath + "\\Project_Assignment.xlsx", 1);
		String name = p.st.getRow(1).getCell(0).toString();
		String adress = p.st.getRow(1).getCell(1).toString();
		String city = p.st.getRow(1).getCell(2).toString();
		String state = p.st.getRow(1).getCell(3).toString();
		String zipCode = p.st.getRow(1).getCell(4).toString();
		String cardType = p.st.getRow(1).getCell(5).toString();
		String CardNumber = p.st.getRow(1).getCell(6).toString();
		String NameOnCard = p.st.getRow(1).getCell(7).toString();

		p.Name.sendKeys(name);
		p.Address.sendKeys(adress);
		p.City.sendKeys(city);
		p.State.sendKeys(state);
		p.zipCode.sendKeys(zipCode);

		s = new Select(p.cardType);
		s.selectByVisibleText(cardType);

		p.CardNumber.sendKeys(CardNumber);

		p.nameOnCard.sendKeys(NameOnCard);
		Thread.sleep(2000);

	}

	@Test(priority = 4, description = "Test case to Verify Page title and take sceenshoot")
	public void TC_05_Generate_Ticket() throws Exception {

		p.PurchaseFlight.click();
		Thread.sleep(2000);

		System.out.println("ID Generated is : " + p.ID.getText());

	}

	@AfterTest
	public void afterTest() {

	}

}
