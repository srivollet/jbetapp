package betapp;

import static betapp.util.StringUtil.getFloat;
import static betapp.util.StringUtil.getString;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import betapp.model.Competition;
import betapp.model.Equipe;
import betapp.model.Match;
import betapp.util.DateUtil;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver = new ChromeDriver();

		driver.get("http://www.flashresultats.fr/");

		Thread.sleep(3000);

		driver.findElement(By.cssSelector(".ifmenu-odds.li4")).click();

		Thread.sleep(3000);

		Calendar calendar = DateUtil.getCalendar_ddMM(driver.findElement(By.className("today")).getText());

		WebElement resultats = driver.findElement(By.className("odds-content"));

		Document soup = Jsoup.parse(resultats.getAttribute("innerHTML"));

		Elements soccers = soup.getElementsByClass("soccer odds");

		for (Element soccer : soccers) {

			Element c = soccer.getElementsByClass("tournament_part").first();

			Competition competition = new Competition();
			competition.setName(c.text());

			Elements matchs = soccer.getElementsByTag("tbody").first().getElementsByTag("tr");
			for (Element m : matchs) {
				Match match = new Match();

				String heure = m.getElementsByClass("cell_ad").first().text();
				if (heure != null) {
					String[] t = heure.split(":");
					calendar.set(Calendar.HOUR, Integer.valueOf(t[0]));
					calendar.set(Calendar.MINUTE, Integer.valueOf(t[1]));
					match.setDate(calendar.getTime());
				}

				Equipe equipe1 = new Equipe();
				equipe1.setNom(getString(m.getElementsByClass("team-home").first().text()));
				match.setEquipe1(equipe1);

				Equipe equipe2 = new Equipe();
				equipe2.setNom(getString(m.getElementsByClass("team-away").first().text()));
				match.setEquipe2(equipe2);

				String s = getString(m.getElementsByClass("score").first().text(), StandardCharsets.UTF_8);
				if (s != null && !s.trim().contains("-")) {
					String[] scores = s.trim().split(":");
					match.setScore1(Integer.valueOf(new String(scores[0].trim())));
					match.setScore2(Integer.valueOf(new String(scores[1].trim())));
				}

				match.setCote1(getFloat(m.getElementsByClass("cell_oa").first().text()));
				match.setCoteN(getFloat(m.getElementsByClass("cell_ob").first().text()));
				match.setCote2(getFloat(m.getElementsByClass("cell_oc").first().text()));
			}
		}

		driver.quit();
	}

}
