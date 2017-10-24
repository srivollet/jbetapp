package betapp;

import static betapp.util.StringUtil.getString;
import static java.nio.charset.StandardCharsets.ISO_8859_1;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;

import betapp.util.DateUtil;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver = new ChromeDriver();

		driver.get("http://www.flashresultats.fr/");

		driver.findElement(By.className("yesterday")).click();

		Thread.sleep(3000);

		driver.findElement(By.cssSelector(".ifmenu-odds.li4")).click();

		Thread.sleep(3000);

		WebElement resultats = driver.findElement(By.className("odds-content"));

		// File input = new File("src/test/resources/flashresultats.txt");
		// Document soup = Jsoup.parse(input, "UTF-8", "http://example.com/");

		Document soup = Jsoup.parse(resultats.getAttribute("innerHTML"));

		Elements soccers = soup.getElementsByClass("soccer odds");

		for (Element soccer : soccers) {

			Element competition = soccer.getElementsByClass("tournament_part").first();

			File file = new File(String.format("src/main/resources/%s.csv", competition.text()));
			boolean exists = file.exists();

			if (!exists) {
				// On met dans le fichier la première l'entête des colonnes
				Files.append("\"date\";\"horaire\";\"team_home\";\"team_away\";\"score\";\"cote1\";\"coteN\";\"cote2\"",
						file, ISO_8859_1);
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

				Files.append(
						String.format("\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\"",
								DateUtil.getYesterday(), heure, domicile, visiteur, score, cote1, coteN, cote2),
						file, ISO_8859_1);
				Files.append("\n", file, ISO_8859_1);
			}
		}

		driver.quit();
	}

}
