package Selenium;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class StockPriceCapture {

    public static void main(String[] args) throws InterruptedException, IOException {

        //set GeckoDriver path for FireFox browser
        System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");        

        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.nseindia.com/live_market/dynaContent/live_analysis/top_gainers_losers.htm?cat=G");

        Thread.sleep(3000);

        //Get Row Count

        List<WebElement> stockName = driver.findElements(By.xpath(("//*[@id=\"topgainer-Table\"]/tbody/tr/td[1]")));

        System.out.println("=====Stockname=====");
        System.out.println(stockName.size());
        for(int i=0 ; i<stockName.size();i++) {
            System.out.println(stockName.get(i).getText());
        }

        List<WebElement> stockOpenPrice = driver.findElements(By.xpath(("//*[@id=\"topgainer-Table\"]/tbody/tr/td[2]")));

        System.out.println(stockOpenPrice.size());
        System.out.println("=====stockOpenPrice=====");
        for(int i=0 ; i<stockOpenPrice.size();i++) {
            System.out.println(stockOpenPrice.get(i).getText());
        }

        List<WebElement> stockHighPrice = driver.findElements(By.xpath(("//*[@id=\"topgainer-Table\"]/tbody/tr/td[3]")));

        System.out.println(stockHighPrice.size());
        System.out.println("=====stockHighPrice=====");
        for(int i=0 ; i<stockHighPrice.size();i++) {
            System.out.println(stockHighPrice.get(i).getText());
        }

        List<WebElement> stockLowPrice = driver.findElements(By.xpath(("//*[@id=\"topgainer-Table\"]/tbody/tr/td[4]")));

        System.out.println(stockLowPrice.size());
        System.out.println("=====stockLowPrice=====");
        for(int i=0 ; i<stockLowPrice.size();i++) {
            System.out.println(stockLowPrice.get(i).getText());
        }

        List<WebElement> date = driver.findElements(By.xpath(("//*[@id=\"topgainer-Table\"]/tbody/tr/td[10]/a")));

        System.out.println(date.size());
        System.out.println("=====date=====");
        for(int i=0 ; i<date.size();i++) {
            System.out.println(date.get(i).getText());
        }

        Thread.sleep(3000);

            File file = new File("./result/stockData.csv");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("stockName,OpenPrice,HighPrice,LowPrice,date");
            bw.newLine();
            for(int i=0;i<stockName.size();i++)
            {
                bw.write(stockName.get(i).getText()+","+stockOpenPrice.get(i).getText().replaceAll(",", "")+","+stockHighPrice.get(i).getText().replaceAll(",", "")+","+stockLowPrice.get(i).getText().replaceAll(",", "")+","+date.get(i).getText().replaceAll(",", ""));
                bw.newLine();
            }
            bw.close();
            fw.close();      
            
            driver.quit();
        
//        int rowCount = driver.findElements(By.tagName("tr")).size();
//        
//        //Get Column Count
//        int colCount = driver.findElements(By.xpath("//thead//tr//th")).size();
//        
//        System.out.println("Row count :" + rowCount);
//        System.out.println("Col count :" + colCount);
//        
//        //Print table Data
//        for(WebElement tdata:driver.findElements(By.tagName("tr"))){
//            System.out.println(tdata.getText());
//            
//        }
    }
}