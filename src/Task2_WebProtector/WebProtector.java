package Task2_WebProtector;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjectClasses.AddUserPage;

public class WebProtector {
	public WebDriver driver;
	//Browser selection from testng.xml file and this method will execute first.
	@Parameters("browser")
	@BeforeClass
	public void InvokeBrowser(@Optional("chrome") String browser) 
	{
        System.out.println("**********************Task 2 - Web**********************");
        System.out.println("Browser Initialization");
		if (browser.equalsIgnoreCase("chrome")) 
		{ 
			System.setProperty("webdriver.chrome.driver","C://chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", "C://IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
	}

	@Test(priority =1) 
	public void ValidateTitleOnUserListTable()
	{   
		System.out.println("**********************Task 2 - Web**********************");
		System.out.println("\nStep1 : To Verify successfull navigation to the given URL.");
		driver.get("http://www.way2automation.com/angularjs-protractor/webtables/");
		driver.getTitle();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

		System.out.println("Result : Successfully navigated to the given URL.");
		String Title = driver.getTitle();
		System.out.println("\nStep2 : To Verify Title of User List Table.");
		String Expectedtiltle ="Protractor practice website - WebTables";
		if (Title.equalsIgnoreCase(Expectedtiltle))
		{   
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
			System.out.println("Result : User List Table Name is: "+Title);
		}
		else
		{
			System.out.println("Unable to load WebProtector site, please check your internat connectivity");
		}
	} 

	@Test(dataProvider ="data",dataProviderClass=FillData.class, priority=2)
	public void AddUserDetails(String FirstName,String LastName,String UserName,String Password, String Customer,String Role, String Email, String Cell)
	{   
		System.out.println("\nStep3 : To Verify User is able to click ADD-USER."+UserName);
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		//User clicks on add user button
		driver.findElement(By.xpath(AddUserPage.AddButton)).click();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		//User clicks on add user button
		driver.findElement(By.xpath(AddUserPage.FirstName)).clear();
		driver.findElement(By.xpath(AddUserPage.FirstName)).sendKeys(FirstName);
		driver.findElement(By.xpath(AddUserPage.LastName)).clear();
		driver.findElement(By.xpath(AddUserPage.LastName)).sendKeys(LastName);
		driver.findElement(By.xpath(AddUserPage.UserName)).clear();
		driver.findElement(By.xpath(AddUserPage.UserName)).sendKeys(UserName);
		driver.findElement(By.xpath(AddUserPage.Password)).clear();
		driver.findElement(By.xpath(AddUserPage.Password)).sendKeys(Password);
		if (Customer.equals("Company AAA"))
		{
			driver.findElement(By.xpath(AddUserPage.Company_aaa)).click();
		}
		else if (Customer.equals("Company BBB"))
		{
			driver.findElement(By.xpath(AddUserPage.Company_aaa)).click();
		}

		//Select Role optionout of 3 roles--Sales Team, Admin and Customer
		//driver.findElement(By.xpath(AddUserPage.RoleID)).clear();
		if (Role.equalsIgnoreCase("Sales Team"))
		{
			driver.findElement(By.xpath(AddUserPage.RoleID)).click();
			driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
			driver.findElement(By.xpath(AddUserPage.RoleID)).sendKeys(Role);
			
		}
		else if (Role.equalsIgnoreCase("Customer"))
		{
			driver.findElement(By.xpath(AddUserPage.RoleID)).click();
			driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
			driver.findElement(By.xpath(AddUserPage.RoleID)).sendKeys(Role);
			
		}
		else if (Role.equalsIgnoreCase("Admin"))
		{ 
			Select dropdown = new Select(driver.findElement(By.xpath(AddUserPage.RoleID)));
			driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
			dropdown.selectByVisibleText(Role);
			driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
							
		}
		
		driver.findElement(By.xpath(AddUserPage.Email)).clear();
		driver.findElement(By.xpath(AddUserPage.Email)).sendKeys(Email);
		
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		driver.findElement(By.xpath(AddUserPage.Moblie)).clear();
		driver.findElement(By.xpath(AddUserPage.Moblie)).sendKeys(Cell);
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		driver.findElement(By.xpath(AddUserPage.SAVEbutton)).click();
		System.out.println("Result : Users details added successfully."+UserName);
	}

		
	@Parameters("UserName")
	@Test(priority=3)
	public void SearchAddedUsersInWebTable(@Optional("User")String UserName) throws AWTException, IOException
	{
		System.out.println("\nStep4 : To Verify if Unique UsersName are to WebTable.");
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		driver.findElement(By.xpath(AddUserPage.SearchButton)).sendKeys(UserName);
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		Robot r = new Robot(); 
		// It saves screenshot to desired path 
		String path = "D://WebTable.jpg"; 
		// Used to get ScreenSize and capture image 
		Rectangle capture =  new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()); 
		BufferedImage Image = r.createScreenCapture(capture); 
		//append(Image, "jpg", new File(path)); 
		ImageIO.write(Image, "jpg", new File(path));
		System.out.println("Result : Screenshot saved newly added users with unique UserID will be saved to D drive with name WebTable as jpg image.");   
	}

	@AfterClass 
	public void teardownTest() throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		System.out.println("\nClosing out browser -All the tests cases have been successfully tested");
		Thread.sleep(5000);  
		driver.quit();
	}

}

