package betapp;

import static betapp.util.DateUtil.DDMMYYYY;
import static betapp.util.StringUtil.getString;
import static java.nio.charset.StandardCharsets.ISO_8859_1;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		Date date = new Date();

		WebDriver driver = getWebDriver(args);

		WebDriverWait driverWait = new WebDriverWait(driver, 60);

		driver.get("http://www.flashresultats.fr/");

		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ifmenu-odds.li4"))).click();

		for (int i = 0; i < Integer.valueOf(args[0]); i++) {

			Thread.sleep(5000);

			WebElement yesterday = driverWait
					.until(ExpectedConditions.visibilityOfElementLocated(By.className("yesterday")));
			yesterday.click();

			date = DateUtils.addDays(date, -1);
			WebElement resultats = driverWait
					.until(ExpectedConditions.visibilityOfElementLocated(By.className("odds-content")));

			Document soup = Jsoup.parse(resultats.getAttribute("innerHTML"));

			Elements soccers = soup.getElementsByClass("soccer odds");

			for (Element soccer : soccers) {

				Element competition = soccer.getElementsByClass("tournament_part").first();

				File file = new File(String.format(args[1] + "/%s.csv", competition.text()));
				boolean exists = file.exists();

				if (!exists) {
					// On met dans le fichier la première l'entête des colonnes
					Files.append("date,horaire,team_home,team_away,score,cote1,coteN,cote2", file, ISO_8859_1);
					Files.append("\n", file, ISO_8859_1);
				}

				Elements matchs = soccer.getElementsByTag("tbody").first().getElementsByTag("tr");
				for (Element match : matchs) {

					String heure = getString(match.getElementsByClass("cell_ad").first().text());
					String domicile = getString(match.getElementsByClass("team-home").first().text());
					String visiteur = getString(match.getElementsByClass("team-away").first().text());
					String score = getString(match.getElementsByClass("score").first().text());
					String cote1 = getString(match.getElementsByClass("cell_oa").first().text());
					String coteN = getString(match.getElementsByClass("cell_ob").first().text());
					String cote2 = getString(match.getElementsByClass("cell_oc").first().text());

					Files.append(String.format("%s,%s,%s,%s,%s,%s,%s,%s", DateFormatUtils.format(date, DDMMYYYY), heure,
							domicile, visiteur, score, cote1, coteN, cote2), file, ISO_8859_1);
					Files.append("\n", file, ISO_8859_1);
				}
			}
		}
		driver.quit();
	}

	private static WebDriver getWebDriver(String[] args) {
		WebDriver driver = null;
		if (args.length > 2) {
			String browser = args[2];
			switch (browser) {
			case "firefox":
				if (args.length > 3)
					System.setProperty("webdriver.gecko.driver", args[3]);

				return new FirefoxDriver();
			default:
				break;
			}
		}
		return driver == null ? new ChromeDriver() : driver;
	}

}
