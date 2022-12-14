import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class autoItFileUpload {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		String pro_path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\smrit\\Documents\\chromedriver_win32\\chromedriver.exe");
		
		//we can use some ChromeDriver option to make the default download path to project path
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", pro_path);
		ChromeOptions chro_option = new ChromeOptions();
		chro_option.setExperimentalOption("prefs", chromePrefs);
		
		WebDriver driver = new ChromeDriver(chro_option);
		driver.get("https://www.ilovepdf.com/jpg_to_pdf");
		driver.findElement(By.id("pickfiles")).click();
		Thread.sleep(3000);
		
		//Using AutoIT for uploading file to the website - having command in java
		Runtime.getRuntime().exec("D:\\Desktop\\My\\My Stuff\\Selenium_Udemy\\AutoIt_ScriptFiles\\AutoItScript.exe");
		Thread.sleep(2000);
		//To download the converted file
		driver.findElement(By.id("processTask")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pickfiles")));
		//Thread.sleep(5000);
		driver.findElement(By.id("pickfiles")).click();
		Thread.sleep(5000);
		System.out.println("After 4 min thread");
		File fl = new File(pro_path+"/sample.pdf");
		System.out.println("File class call");
		if(fl.exists()) {
			System.out.println("File exist.......");
			if(fl.delete()) {
				System.out.println("File deleted.....");
			}
		}
		driver.close();
	}

}
