package Selenium;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Yammer {

static WebDriver driver;
public static void main(String[] args) throws InterruptedException, IOException {

System.setProperty("webdriver.chrome.driver", "C:\\Users\\rheas\\OneDrive\\Desktop\\Selenium Assessment\\chromedriver.exe");
ChromeOptions option= new ChromeOptions();
option.addArguments("--disable-notifications");

driver= new ChromeDriver(option);
driver.get("https://web.yammer.com/main/capgemini.com/");
Thread.sleep(3000);
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
driver.manage().window().maximize();	
driver.findElement(By.name("loginfmt")).sendKeys("rhea.salih@capgemini.com");
driver.findElement(By.id("idSIButton9")).click();
Thread.sleep(10000);


WebElement yesButton= driver.findElement(By.id("idSIButton9"));
yesButton.click();

//Click on OneTeamONeFamily
driver.findElement(By.linkText("OneTeamOneFamily")).click();
Thread.sleep(10000);

// Set set= new TreeSet();
List list2= new ArrayList();
Map<Integer,WebElement > map= new TreeMap();

WebElement list=null;
for(int j=1;j<5 ;j++) {
list= driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div/div/div/div[2]/ul/li["+j+"]/div/div/div/div/div/div/div[1]/div[5]/div[2]/div/div/div/div/div[2]/div/div/div/button/span/div"));
System.out.println("running "+ j);
JavascriptExecutor executor= (JavascriptExecutor) driver;
//executor.executeScript("window.scrollBy(0,750)");
Thread.sleep(3000);
String a=list.getText().substring(0, 2).replaceAll(" ","");

int num=Integer.parseInt(a);
list2.add(num);
map.put( num,list);
Thread.sleep(5000);
}
Collections.sort(list2);

System.out.println(list2.get(list2.size()-1));
System.out.println(map.get(list2.get(list2.size()-1)));
WebElement mostLikedLocator = map.get(list2.get(list2.size()-1));


allPosts();
JavascriptExecutor executor2= (JavascriptExecutor) driver;
String js = "window.scrollBy"+mostLikedLocator.getLocation();
executor2.executeScript(js);
System.out.println("Most liked post location: "+mostLikedLocator.getLocation());
Thread.sleep(5000);
//Take SS of post
File srcfile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(srcfile1, new File("./screenshot/image2.png"));

}
public static void allPosts() throws InterruptedException {
//get all posts present

List<WebElement> allPost= driver.findElements(By.xpath("//ul[@class='layoutList-552']//li[@class='y-layoutList--item qaFeedThread layoutListItem-162']"));
Thread.sleep(3000);
System.out.println(allPost.size());
List addNumber= new ArrayList();
List <WebElement> list= driver.findElements(By.xpath("//ul[@class='layoutList-552']//li[@class='y-layoutList--item qaFeedThread layoutListItem-162']//span[@class='y-fakeLink']//div[@class='ms-TooltipHost root-394']"));
for(int i=0;i<list.size();i++) {
String a=list.get(i).getText().substring(0, 2).replaceAll(" ","");
int num=Integer.parseInt(a);
addNumber.add(num);
}
Collections.sort(addNumber);
System.out.println("addNumber List: "+addNumber);
}


}
