
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Date;
import java.util.Random;
import java.awt.*;
import java.sql.Time;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class main {

    public static void print(String string){
        System.out.println(new Date() + " - " + string);
    }

    public static String generateMail(int len){
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_.";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }


    public static void main(String[] args) throws InterruptedException  {
        String temporaryMail = generateMail(16) + "@gmail.com";

        print("Firefox otomasyon islemi icin aciliyor.");

        System.setProperty("webdriver.gecko.driver","C:\\selenium\\ChromeDriver\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        WebDriver driver=new FirefoxDriver(options);

        print("A101 Online Magaza Websitesine giris yapilir.");

        driver.get("https://www.a101.com.tr/online-alisveris");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        print("Çerezlerle ilgili bildirimin cikmasi bekleniyor.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")));
        driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();

        print("Cerezler bildirimi kapatildi.");


        print("Giyim ve Aksesuar bolumune tiklaniyor.");
        TimeUnit.SECONDS.sleep(2);
        driver.findElement(By.xpath("/html/body/section/section[1]/div/div/div/div[1]/div/div/ul/li[4]/a")).click();
        TimeUnit.SECONDS.sleep(2);
        print("Kadin ic giyim bolumune tiklaniyor.");
        driver.findElement(By.xpath("/html/body/section/section[4]/div[3]/div[3]/div/div[1]/div[1]/div[1]/ul/li[6]/a")).click();
        TimeUnit.SECONDS.sleep(2);
        print("Dizalti corap bolumune tiklaniyor.");
        driver.findElement(By.xpath("/html/body/section/section[4]/div[3]/div[2]/div/div[1]/div[1]/div[1]/ul/li[4]/a")).click();
        TimeUnit.SECONDS.sleep(2);
        print("Tum urunler yukleniyor...");
        TimeUnit.SECONDS.sleep(2);

        print("Sitede ki tum urunlerin url'leri aliniyor.");

        ArrayList<String> ar = new ArrayList<String>();
        List<WebElement> elements = driver.findElement(By.className("product-list-general")).findElements(By.tagName("li"));
        for (int i = 0; i < 3; i++) {
            ar.add(elements.get(i).findElement(By.tagName("a")).getAttribute("href"));
        }

        print("Sitede siyah urun bulunana kadar devam edilecek.");

        for (String link : ar) {
            driver.get(link);
            TimeUnit.SECONDS.sleep(2);

            if ((driver.findElement(By.className("variants-bar")).findElement(By.className("selected-variant-text")).findElement(By.tagName("span")).getText()).equals("SİYAH")){
                print("Siyah renkte urun bulundu, diger urunlere gecilmeyecek. ");
                break;
            }else{
                print("Urun siyah degil, diger urune geciliyor.");
            }
        }

        print("Sepete ekle butonuna basilir.");
        driver.findElement(By.xpath("/html/body/section/section[3]/div[2]/div[1]/div/div[3]/div[2]/div[1]/button")).click();
        TimeUnit.SECONDS.sleep(2);
        print("Sepete gidilir.");
        driver.findElement(By.className("go-to-shop")).click();
        print("Sepeti onayla butonuna basilir.");
        driver.findElement(By.xpath("/html/body/section/div[1]/div[2]/div/div[2]/div/a")).click();
        TimeUnit.SECONDS.sleep(2);
        print("Uye olmadan devam et botununa basilir.");
        driver.findElement(By.xpath("/html/body/section/div[1]/div/div[1]/div[1]/div[2]/a")).click();
        print("Gecici olusturulan email yazilir ve devam et butonuna basilir.");
        driver.findElement(By.name("user_email")).sendKeys(temporaryMail);
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.xpath("/html/body/section/div[1]/div/div[2]/div/div/form/button")).click();


        // Adres olusturma bolumu
        print("Adres olustur butonuna basilir.");
        TimeUnit.SECONDS.sleep(4);
        driver.findElement(By.xpath("/html/body/section/section/div/div[2]/div/div[1]/div/div[1]/div[2]/ul[2]/li/a")).click();
        TimeUnit.SECONDS.sleep(2);
        print("Adres olusturulur.");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/div[2]/div/div/label/input")).sendKeys("evim");

        //Ad, Soyad, cep telefonu
        print("Ad,Soyad Ve Cep Telefonu Girilir.");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/div[3]/div[1]/div/label/input")).sendKeys("Kerem Mert");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/div[3]/div[2]/div/label/input")).sendKeys("Izmir");

        driver.findElement(By.className("js-phone-number")).sendKeys("5055555555");

        //Il, ilce ve mahalle
        print("İl, ilce ve mahalle secilir.");
        Select il = new Select(driver.findElement(By.className("js-cities")));
        il.selectByValue("58");
        TimeUnit.SECONDS.sleep(3);
        Select ilce = new Select(driver.findElement(By.className("js-township")));
        ilce.selectByValue("715");

        TimeUnit.SECONDS.sleep(3);
        Select mahalle = new Select(driver.findElement(By.className("js-district")));
        mahalle.selectByValue("52290");

        //Adres textarea
        driver.findElement(By.className("js-address-textarea")).sendKeys("bu benim adresim");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/button[1]")).click();
        TimeUnit.SECONDS.sleep(2);
        driver.findElement(By.xpath("/html/body/section/section/div/div[2]/div/div[1]/div/div[2]/form/div[2]/div[2]/ul/li[1]/label/div[2]/div")).click();
        print("Kargo sirketi secilir ve tamamla butonuna basilir.");
        driver.findElement(By.xpath("/html/body/section/section/div/div[2]/div/div[1]/div/div[2]/form/div[2]/button")).click();
        print("Otomasyon islemi tamamlandi.");
    }

}
