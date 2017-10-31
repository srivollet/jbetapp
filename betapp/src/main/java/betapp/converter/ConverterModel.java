package betapp.converter;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

import java.io.File;
import java.io.IOException;
import java.util.List;

import betapp.model.Match;

import com.google.common.io.Files;

public class ConverterModel {

	private ConverterModel() {

	}

	public static void toFile(List<Match> matchs) throws IOException {

		for (Match match : matchs) {

			File file = new File(String.format("src/main/resources/%s.csv",
					match.getCompetition().getName()));

			boolean exists = file.exists();

			if (!exists) {
				Files.append(
						"date,domicile,visiteur,score1,score2,cote1,coteN,cote2",
						file, ISO_8859_1);
				Files.append("\n", file, ISO_8859_1);
			}

			Files.append(String.format("%s,%s,%s,%s,%s,%s,%s,%s", match
					.getDate(), match.getEquipe1().getNom(), match.getEquipe2()
					.getNom(), match.getScore1(), match.getScore2(), match
					.getCote1(), match.getCoteN(), match.getCote2()), file,
					ISO_8859_1);
			Files.append("\n", file, ISO_8859_1);
		}

	}

}
