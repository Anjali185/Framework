package Maven.Selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pageObject {

	static WebDriver w;

	public static String projectPath = System.getProperty("user.dir");

	String url = "http://blazedemo.com/";

	@FindBy(name = "fromPort")
	public WebElement fromPort;

	@FindBy(name = "toPort")
	public WebElement toPort;

	@FindBy(css = "input[type='submit']")
	public WebElement findFlight;

	@FindBy(css = "input[value='Choose This Flight']")
	public WebElement ChooseFlight;

	@FindBy(id = "inputName")
	public WebElement Name;

	@FindBy(id = "address")
	public WebElement Address;

	@FindBy(id = "city")
	public WebElement City;

	@FindBy(id = "state")
	public WebElement State;

	@FindBy(id = "zipCode")
	public WebElement zipCode;

	@FindBy(id = "cardType")
	public WebElement cardType;

	@FindBy(id = "creditCardNumber")
	public WebElement CardNumber;

	@FindBy(id = "nameOnCard")
	public WebElement nameOnCard;

	@FindBy(xpath = "//div[11]/div/input")
	public WebElement PurchaseFlight;

	// ********************************** validation Object

	@FindBy(xpath = "html/body/div[2]/h3")
	public WebElement labeltoVerify;

	@FindBy(xpath = "html/body/div[2]/table/tbody/tr[1]/td[6]")
	public WebElement price;

	@FindBy(xpath = "html/body/div[2]/p[3]")
	public WebElement validatePrice;

	@FindBy(xpath = "html/body/div[2]/p[4]")
	public WebElement Arbitrary_Fees_and_Taxes;
	
	@FindBy(xpath = "html/body/div[2]/p[5]/em")
	public WebElement Total_Cost;

	

	@FindBy(xpath="html/body/div[2]/div/table/tbody/tr[1]/td[2]")
	public WebElement ID;

	public pageObject() {

		PageFactory.initElements(w, this);
	}

	public static void browserCode(String br) {

		if (br.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\Exec\\geckodriver.exe");
			w = new FirefoxDriver();
		} else if (br.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", projectPath + "\\Exec\\chromedriver.exe");
			w = new ChromeDriver();

		}

	}

	public void openUrl() {
		w.get(url);
	}

	public String verifyText(WebElement webele) {
		return webele.getText();
	}

	public String verifyTitle() {
		return w.getTitle();
	}

	public void closeBrowser() {
		w.quit();
	}
	
	
	public double getNumber1(WebElement we){
		return Float.parseFloat(we.getText().replace("$", ""));
		
	}
	
	public double getNumber(WebElement we){
		String s=we.getText().replaceAll("[a-zA-Z]", "");
		
		return Float.parseFloat(s.replace(":", ""));
		
		
	}
	

	public XSSFWorkbook wb;
	public XSSFSheet st;

	public void excelCode(String filename, int sheet) throws Exception {

		FileInputStream f = new FileInputStream(filename);
		wb = new XSSFWorkbook(f);
		st = wb.getSheetAt(sheet);
	}

}
