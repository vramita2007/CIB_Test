package pageObjectClasses;

import org.testng.annotations.Test;

@Test
public class AddUserPage {
	
	public static String AddButton = "//button[@type='add']";
	
   //path to locate to elements on add users Page
	public static String FirstName = "//input[@name= 'FirstName']";
	public static String LastName = "//input[@name= 'LastName']";
	public static String UserName = "//input[@name= 'UserName']";
	public static String Password = "//input[@type= 'password']";
    public static String Company_aaa = "//input[@value= '15']";
    public static String Company_bbb = "//input[@value= '16']";
    public static String RoleID = "//select[@name= 'RoleId']";
    public static String Email = "//input[@name= 'Email']";
    public static String Moblie = "//input[@name= 'Mobilephone']";
    
	//*********click to save button***************
	public static String SAVEbutton ="//*[@class = 'btn btn-success']";
	//Go to Search text path
	public static String SearchButton = "//input[@placeholder='Search']";

	

}
