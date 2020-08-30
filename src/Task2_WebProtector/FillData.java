package Task2_WebProtector;

import org.testng.annotations.DataProvider;

public class FillData {

	@DataProvider(name= "data")
	public Object[][] getData()
	{
		Object[][] data = {{"FName1", "LName1", "User1","Pass1","Company AAA","Admin","admin@mail.com","82555"},{"FName2", "LName2", "User2","Pass2","Company BBB","Customer","customer@mail.com","83444"}};
		return data;
	}
	
}
