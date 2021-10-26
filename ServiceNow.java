package week4.day1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://dev60717.service-now.com/navpage.do");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.switchTo().frame(0);

		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("Keshav_25$");
		driver.findElement(By.name("not_important")).click();

		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incident");
		driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
		WebElement findElement = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(findElement);
		driver.findElement(By.xpath("//button[text()='New']")).click();

		driver.findElement(By.xpath("//button[@id='lookup.incident.caller_id']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> myList = new ArrayList<String>(windowHandles);
		WebDriver window = driver.switchTo().window(myList.get(1));
		driver.findElement(By.linkText("Abel Tuter")).click();
		driver.switchTo().window(myList.get(0));
		Thread.sleep(2000);
		driver.switchTo().frame(findElement);
		driver.findElement(By.xpath("//input[@id='incident.short_description']")).sendKeys("description");
		String attribute = driver.findElement(By.xpath("//input[@name='incident.number']")).getAttribute("value");
		System.out.println(attribute);
		driver.findElement(By.id("sysverb_insert")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(findElement);
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(attribute + Keys.ENTER);
		String text = driver.findElement(By.xpath("//td[@class='vt']/a")).getText();
		System.out.println(text);
		WebElement findElement2 = driver.findElement(By.xpath("//td[@class='vt']/a"));
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/pic2.png");
		FileUtils.copyFile(src, dst);

	}

}
