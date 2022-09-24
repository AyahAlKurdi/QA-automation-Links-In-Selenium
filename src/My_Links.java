import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class My_Links {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Users\\aykurdi\\Desktop\\old data D\\backup-aya\\chromedriver_win32\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.facebook.com");
		driver.manage().window().maximize();
		
		//1. Count all links in the page
		System.out.println(driver.findElements(By.tagName("a")).size());
		
		//2. Count the links in the footer only (the footer is a mini driver)
		WebElement pageFooter = driver.findElement(By.id("pageFooter"));
		System.out.println(pageFooter.findElements(By.tagName("a")).size());
		
		//3. Count the links in the children footer only 
		WebElement childFooter = pageFooter.findElement(By.id("pageFooterChildren"));
		int childCount = childFooter.findElements(By.tagName("a")).size();
		System.out.println(childCount);
		
		//4. Click on the children footer links and open them in a new tab (will fail)
//		for (int i = 0; i < childCount ; i++)
//		{
//			childFooter.findElements(By.tagName("a")).get(i).click();
//		}
		
		//5. Click on the children footer links and open them in a new tab
		for (int i = 0; i < childCount ; i++)
		{
			String openLink = Keys.chord(Keys.CONTROL, Keys.ENTER);
			childFooter.findElements(By.tagName("a")).get(i).sendKeys(openLink);
		}
		
		//6. get the title of each tab
		
		Set<String> myTabs = driver.getWindowHandles();
		
		Iterator<String> tabView = myTabs.iterator();
		
		while(tabView.hasNext())
		{
			driver.switchTo().window(tabView.next());
			System.out.println(driver.getTitle());
		}
		
	}

}
