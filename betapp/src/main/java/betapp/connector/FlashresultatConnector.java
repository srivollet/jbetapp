package betapp.connector;

import static betapp.util.StringUtil.getFloat;
import static betapp.util.StringUtil.getString;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;

import betapp.exception.BetappException;
import betapp.model.Competition;
import betapp.model.Equipe;
import betapp.model.Match;
import betapp.util.DateUtil;
import betapp.util.StringUtil;

public abstract class FlashresultatConnector {

	public static List<Match> run() throws BetappException {
		WebDriver driver = null;
		List<Match> matchs = null;

		try {
			driver = new ChromeDriver();

			driver.get("http://www.flashresultats.fr/");

			Thread.sleep(3000);

			driver.findElement(By.cssSelector(".ifmenu-odds.li4")).click();

			Thread.sleep(3000);

			Calendar calendar = DateUtil.getCalendar_ddMM(driver.findElement(By.className("today")).getText());

			WebElement resultats = driver.findElement(By.className("odds-content"));

			Document soup = Jsoup.parse(resultats.getAttribute("innerHTML"));

			Elements soccers = soup.getElementsByClass("soccer odds");

			matchs = new ArrayList<Match>();
			for (Element soccer : soccers) {
				Elements elements = soccer.getElementsByTag("tbody").first().getElementsByTag("tr");

				Competition competition = getCompetition(soccer);

				for (Element element : elements) {
					Match match = getMatch(element, calendar);
					match.setCompetition(competition);

					matchs.add(match);
				}
			}

		} catch (InterruptedException e) {
			throw new BetappException();
		} finally {
			if (driver != null) {
				driver.quit();
			}
		}
		return matchs == null ? Collections.<Match>emptyList() : matchs;
	}

	private static Competition getCompetition(Element element) {
		Competition competition = new Competition();

		Element nom = element.getElementsByClass("tournament_part").first();
		if (nom != null) {
			competition.setName(nom.text());
		}

		Element pays = element.getElementsByClass("country_part").first();
		if (pays != null) {
			String value = pays.text();
			if (!Strings.isNullOrEmpty(value)) {
				// suppression du dernier caractère :
				competition.setCountry(value.substring(0, value.length() - 1));
			}
		}
		return competition;
	}

	private static Match getMatch(Element element, Calendar calendar) {
		Match match = new Match();

		String heure = element.getElementsByClass("cell_ad").first().text();
		if (heure != null) {
			String[] t = heure.split(":");
			calendar.set(Calendar.HOUR, Integer.valueOf(t[0]));
			calendar.set(Calendar.MINUTE, Integer.valueOf(t[1]));
			match.setDate(calendar.getTime());
		}

		Equipe equipe1 = new Equipe();
		equipe1.setNom(getString(element.getElementsByClass("team-home").first().text()));
		match.setEquipe1(equipe1);

		Equipe equipe2 = new Equipe();
		equipe2.setNom(getString(element.getElementsByClass("team-away").first().text()));
		match.setEquipe2(equipe2);

		String s = getString(element.getElementsByClass("score").first().text(), StandardCharsets.UTF_8);
		if (s != null && !s.trim().contains("-")) {
			String[] scores = s.trim().split(":");
			match.setScore1(Integer.valueOf(scores[0].substring(0, 1)));
			match.setScore2(Integer.valueOf(scores[1].substring(1, 2)));
		}
		
		match.setCote1(getFloat(element.getElementsByClass("cell_oa").first().text()));
		match.setCoteN(getFloat(element.getElementsByClass("cell_ob").first().text()));
		match.setCote2(getFloat(element.getElementsByClass("cell_oc").first().text()));

		return match;
	}

}
