package Sumithaacademy;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Sumithaacademy.TestComponents.BaseTest;
import Sumithaacademy.TestComponents.Retry;
import Sumithaacademy.pageobects.CartPage;
import Sumithaacademy.pageobects.CheckoutPage;
import Sumithaacademy.pageobects.ConfirmationPage;
import Sumithaacademy.pageobects.ProductCatalogue;
import Sumithaacademy.pageobects.landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidations extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidations() throws IOException {
		// TODO Auto-generated method stub
		String productName="ZARA COAT 3"; 
		LandingPage.loginApplication("example@gmail.con", "Pass@123");
		 Assert.assertEquals("Incorrect email or password.",LandingPage.getErrorMessage());

	}
	
	@Test
	public void ProductErrorValidations()
	{
		String productName="ZARA COAT 3"; 
		 ProductCatalogue productcatalogue=LandingPage.loginApplication("example@gmail.con", "Pass@123");
		 List<WebElement>products=productcatalogue.getProductList();
		 productcatalogue.addProductToCart(productName);
		 CartPage cartpage=productcatalogue.goToCartPage();
		 Boolean match=cartpage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
