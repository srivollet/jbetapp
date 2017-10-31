package betapp;

import java.io.IOException;
import java.util.List;

import betapp.connector.FlashresultatConnector;
import betapp.converter.ConverterModel;
import betapp.model.Match;

public class Main {

	public static void main(String[] args) throws InterruptedException,
			IOException {
		List<Match> matchs = FlashresultatConnector.run();

		ConverterModel.toFile(matchs);

		System.out.println(matchs);
	}

}
