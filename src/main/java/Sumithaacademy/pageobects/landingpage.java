package Sumithaacademy.pageobects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Sumithaacademy.AbstractComponents.AbstractComponent;

public class landingpage extends AbstractComponent {
	WebDriver driver;
	public landingpage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement useremail=driver.findElement(By.id("userEmail"));
	
	//pagefactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement Submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		waitForElementToAppear(Submit);
		Submit.click();
		ProductCatalogue productcatalogue= new ProductCatalogue(driver);
		return productcatalogue;
	}
		
	public String getErrorMessage()
	{
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}
		public void goTo()
		{
			driver.get("https://rahulshettyacademy.com/client/#/dashboard/dash");
		}
		
	
}
