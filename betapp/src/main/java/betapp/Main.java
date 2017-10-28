package betapp;

import java.io.IOException;
import java.util.List;

import betapp.connector.FlashresultatConnector;
import betapp.model.Match;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		List<Match> matchs = FlashresultatConnector.run();

		System.out.println(matchs);
	}

}
