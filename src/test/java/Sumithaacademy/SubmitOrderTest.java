package Sumithaacademy;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Sumithaacademy.AbstractComponents.OrdersPage;
import Sumithaacademy.TestComponents.BaseTest;
import Sumithaacademy.pageobects.CartPage;
import Sumithaacademy.pageobects.CheckoutPage;
import Sumithaacademy.pageobects.ConfirmationPage;
import Sumithaacademy.pageobects.ProductCatalogue;
import Sumithaacademy.pageobects.landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	String productName="ZARA COAT 3"; 

	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {
		// TODO Auto-generated method stub
		
		System.out.println("================================");
		System.out.println("Email   : " + input.get("email"));
		System.out.println("Product : " + input.get("product"));
		System.out.println("================================");
		
		 ProductCatalogue productcatalogue=LandingPage.loginApplication(input.get("email"),input.get("password"));
		 List<WebElement>products=productcatalogue.getProductList();
		 productcatalogue.addProductToCart(input.get("product"));
		 CartPage cartpage=productcatalogue.goToCartPage();
		 Boolean match=cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage=cartpage.goToCheckout();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage=checkoutpage.submitOrder();
		String confirmMessage=confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productcatalogue=LandingPage.loginApplication("example@gmail.con", "Pass@123");
		OrdersPage orderspage=productcatalogue.goToOrdersPage();
		orderspage.VerifyOrderDisplay(productName);
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\jsonpractice\\data\\PurchaseOrder.json");
		return new Object [][] {{data.get(0)},{data.get(1)}};
		
	}
	
	//HashMap<String, String>map= new HashMap<String, String>();
			//map.put("email", "example@gmail.con");
			//map.put("password", "Pass@123");
			//map.put("product", "ZARA COAT 3");
			
			//HashMap<String, String>map1= new HashMap<String, String>();
			//map1.put("email", "testng55@gmail.com");
			//map1.put("password", "Test@1234");
			//map1.put("product", "ADIDAS ORIGINAL");
}
