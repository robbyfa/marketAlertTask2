package webScraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MarketAlert {

    public String url;
    public WebDriver driver;
    public int headingsFound;
    public int iconsFound;
    public int descriptionsFound;
    public int urlsFound;
    public int imageUrlsFound;
    public int pricesFound;



    public List<String> headings = new ArrayList<>();
    public List<String> descriptions = new ArrayList<>();
    public  List<String> urls = new ArrayList<>();
    public  List<String> imageUrls = new ArrayList<>();
    public  List<Integer> prices = new ArrayList<>();



    public String postedBy;
    public int alerts;
    public int alertType = 6;


    public void addAlerts(int k) throws ParseException, IOException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/rober/webtesting/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.ultimate.com.mt/product-category/tv-audio/audio/headphones/");
        Thread.sleep(500);

        for (int i = 0; i < 5; i++) {

            headings.add(i,driver.findElements(By.className("woocommerce-loop-product__title")).get(i).getText());
            descriptions.add(i,driver.findElements(By.cssSelector("#content > div:nth-of-type(2) > div > div > div > section:nth-of-type(4) > div > div > div:nth-of-type(2) > div > div > div > div > div > div > ul > li > a > p")).get(i).getText());
            imageUrls.add(i,driver.findElements(By.cssSelector("#content > div:nth-of-type(2) > div > div > div > section:nth-of-type(4) > div > div > div:nth-of-type(2) > div > div > div > div > div > div > ul > li > a > img")).get(i).getAttribute("src"));
            urls.add(i,driver.findElements(By.cssSelector("#content > div:nth-of-type(2) > div > div > div > section:nth-of-type(4) > div > div > div:nth-of-type(2) > div > div > div > div > div > div > ul > li > a")).get(i).getAttribute("href"));
            NumberFormat nf = NumberFormat.getInstance(Locale.UK);
            Number num = nf.parse(driver.findElements(By.className("wccpf_archive_price_tag") ).get(i).getAttribute("value"));
            long price = (long) num * 100;
            prices.add(i, (int) price);


            String body = "{\n" +
                    "\"alertType\":"+ alertType +",\n" +
                    "\"heading\": \""+headings.get(i)+"\",\n" +
                    "\"description\": \""+descriptions.get(i)+"\",\n" +
                    "\"url\": \""+urls.get(i)+"\",\n" +
                    "\"imageUrl\" : \""+imageUrls.get(i)+"\",\n" +
                    "\"postedBy\": \"ff557502-1ba4-4578-b094-2efdd4375b1d\",\n" +
                    "\"priceInCents\":"+ prices.get(i)+"\n" +
                    "}";

            URI uri = URI.create("https://api.marketalertum.com/Alert");
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode =  response.statusCode();
        }





        alerts = k;




    }

    public void validLogin() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/users/rober/webtesting/chromedriver.exe");
        driver = new ChromeDriver();
        //Go to google and disable cookies dialog
        driver.get("https://www.marketalertum.com/Alerts/Login");
        WebElement searchField = driver.findElement(By.name("UserId"));
        searchField.sendKeys("ff557502-1ba4-4578-b094-2efdd4375b1d");
        WebElement searchButton = driver.findElement(By.xpath("/html/body/div/main/form/input[2]"));
        searchButton.submit();

        Thread.sleep(500);

        url = driver.getCurrentUrl();
    }

    public void invalidLogin() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/users/rober/webtesting/chromedriver.exe");
        driver = new ChromeDriver();

        //Go to google and disable cookies dialog
        driver.get("https://www.marketalertum.com/Alerts/Login");
        WebElement searchField = driver.findElement(By.name("UserId"));
        searchField.sendKeys("invalidId");
        WebElement searchButton = driver.findElement(By.xpath("/html/body/div/main/form/input[2]"));
        searchButton.submit();

        Thread.sleep(500);

        url = driver.getCurrentUrl();

    }

    public Boolean findHeading(){

        int alerts = driver.findElements(By.cssSelector(" tbody ")).size();
        headingsFound =  driver.findElements(By.tagName("h4")).size();
        if(headingsFound == alerts){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean findIcon(){

        int alerts = driver.findElements(By.cssSelector(" tbody ")).size();
        iconsFound =  driver.findElements(By.cssSelector("tbody > tr:first-of-type > td > h4 > img")).size();

        if(iconsFound == alerts) {
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean findImage(){
        int alerts = driver.findElements(By.cssSelector(" tbody ")).size();
        imageUrlsFound =  driver.findElements(By.cssSelector("tbody > tr:nth-of-type(2) > td")).size();
        if(imageUrlsFound == alerts){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean findDescription(){
        int alerts = driver.findElements(By.cssSelector(" tbody ")).size();
        descriptionsFound =  driver.findElements(By.cssSelector("tbody > tr:nth-of-type(3) > td")).size();
        if(descriptionsFound == alerts){
            return true;
        }
        else {
            return false;
        }
    }
    public Boolean findLink(){

        int alerts = driver.findElements(By.cssSelector(" tbody ")).size();
        urlsFound =  driver.findElements(By.cssSelector(" tbody > tr:nth-of-type(5) > td")).size();

        if(alerts == urlsFound) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean findPrice() {
        int alerts = driver.findElements(By.cssSelector(" tbody ")).size();
        pricesFound = driver.findElements(By.cssSelector(" tbody > tr:nth-of-type(4) > td")).size();
        if (pricesFound == alerts) {
            return true;
        }
        else {
            return true;
        }
    }

    public Boolean countAlerts(int arg0){

           int number  = driver.findElements(By.cssSelector("tbody > tr:nth-of-type(2) > td")).size();

           if (arg0 == number){
             return true;
           }
          return false;
    }


    public void uploadAlertWithType(int type) throws ParseException, InterruptedException, IOException {


        System.setProperty("webdriver.chrome.driver", "/Users/rober/webtesting/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.ultimate.com.mt/product-category/tv-audio/audio/headphones/");


        headings.add(driver.findElement(By.className("woocommerce-loop-product__title")).getText());
        descriptions.add(driver.findElement(By.cssSelector("#content > div:nth-of-type(2) > div > div > div > section:nth-of-type(4) > div > div > div:nth-of-type(2) > div > div > div > div > div > div > ul > li > a > p")).getText());
        imageUrls.add(driver.findElement(By.cssSelector("#content > div:nth-of-type(2) > div > div > div > section:nth-of-type(4) > div > div > div:nth-of-type(2) > div > div > div > div > div > div > ul > li > a > img")).getAttribute("src"));
        urls.add(driver.findElement(By.cssSelector("#content > div:nth-of-type(2) > div > div > div > section:nth-of-type(4) > div > div > div:nth-of-type(2) > div > div > div > div > div > div > ul > li > a")).getAttribute("href"));
        NumberFormat nf = NumberFormat.getInstance(Locale.UK);
        Number num = nf.parse(driver.findElement(By.className("wccpf_archive_price_tag") ).getAttribute("value"));
        long price = (long) num * 100;
        prices.add((int) price);

        String body = "{\n" +
                "\"alertType\":"+ type +",\n" +
                "\"heading\": \""+headings.get(0)+"\",\n" +
                "\"description\": \""+descriptions.get(0)+"\",\n" +
                "\"url\": \""+urls.get(0)+"\",\n" +
                "\"imageUrl\" : \""+imageUrls.get(0)+"\",\n" +
                "\"postedBy\": \"ff557502-1ba4-4578-b094-2efdd4375b1d\",\n" +
                "\"priceInCents\":"+ prices.get(0)+"\n" +
                "}";


        URI uri = URI.create("https://api.marketalertum.com/Alert");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


    }



    public Boolean checkIconFileName(String type){

        String icon = driver.findElement(By.cssSelector(" tbody > tr:first-of-type > td > h4 > img")).getAttribute("src");

        String split = "https://www.marketalertum.com/images/";
        if(icon.equals(split.concat(type))){
            return true;
        }
        return false;
    }

}
