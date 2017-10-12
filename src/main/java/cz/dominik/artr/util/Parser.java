package cz.dominik.artr.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

/**
 * Word parser for words from page:
 * https://duolinguist.wordpress.com/2015/01/06/top-5000-words-in-french-wordlist/
 * This class could be deleted later, is there only for inspiration for future german words parsing
 *
 * @author dominik.mozny
 */
public class Parser {
    public static void parseFile() throws IOException {
        Path pathInput = Paths.get("C:/sandbox/react/fr_words_unchanged.txt");
        Path pathOutput = Paths.get("C:/sandbox/react/fr_words_parsed.txt");
        try (BufferedReader bufferedReader = Files.newBufferedReader(pathInput);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(pathOutput)) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String lineWithSingleSpaces = line.replaceAll("(\\s)+", " ");
                String[] splits = lineWithSingleSpaces.split(" ");
                String leLa = getLeLa(splits[splits.length - 1]);
                if (leLa == null) {
                    continue;
                }
                bufferedWriter.write(leLa + " " + getQuestion(splits) + System.lineSeparator());
            }
        }

    }

    private static String getQuestion(String[] splits) {
        StringBuilder question = new StringBuilder(splits[1] + " (");
        IntStream.range(2, splits.length - 1).forEach(i -> question.append(splits[i]).append(" "));
        return question.substring(0, question.length() - 1) + ")";
    }

    private static String getLeLa(String lastString) {
        if (lastString.contains("nf")) {
            return "la";
        }
        if (lastString.contains("nm") && !lastString.contains("nmi")) {
            return "le";
        }
        return null;
    }
}
