package Sumithaacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Sumithaacademy.TestComponents.BaseTest;
import Sumithaacademy.pageobects.CartPage;
import Sumithaacademy.pageobects.CheckoutPage;
import Sumithaacademy.pageobects.ConfirmationPage;
import Sumithaacademy.pageobects.ProductCatalogue;
import Sumithaacademy.pageobects.landingpage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	
	public landingpage LandingPage;
	public  ProductCatalogue productcatalogue;
	public ConfirmationPage confirmationpage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		LandingPage = launchapplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_username_and_password(String username, String password)
	{
		  productcatalogue=LandingPage.loginApplication(username,password);

	}
	
    @When("^I add product (.+) from Cart$")
    public void     I_add_product_from_Cart(String productName)
    {
    	List<WebElement>products=productcatalogue.getProductList();
		 productcatalogue.addProductToCart(productName);
    }
    
    @When("^Checkout (.+) and submit the order$")
    public void Checkout_submit_order(String productName)
    {
    	CartPage cartpage=productcatalogue.goToCartPage();
		 Boolean match=cartpage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage=cartpage.goToCheckout();
		checkoutpage.selectCountry("india");
		 confirmationpage=checkoutpage.submitOrder();
    }
    
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationpage(String string)
    {
    	String confirmMessage=confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();

    }
    
    @Then("{string} message is displayed")
    public void something_message_displayed(String strArg1)
    {
    	Assert.assertEquals("Incorrect email or password.",LandingPage.getErrorMessage());
    	driver.close();

    }

	

}
