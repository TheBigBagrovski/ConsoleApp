package consoleApp.java;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    private final boolean r;
    private final boolean i;
    private final boolean v;
    private final String word;
    private final String fileName;

    private final static String defaultPath = "./test/consoleApp/resources/";

    public Grep(boolean v, boolean i, boolean r, String word, String fileName) {
        this.v = v;
        this.i = i;
        this.r = r;
        this.word = word;
        this.fileName = fileName;
    }

    public List<String> textFilter(Grep grep) {

        List<String> lines = new ArrayList<>();
        List<String> answer = new ArrayList<>();
        Pattern pattern = Pattern.compile(word);
        Matcher matcher;
        if (r && i) pattern = Pattern.compile(word, Pattern.CASE_INSENSITIVE);

        try {
            lines = Files.readAllLines(Paths.get(defaultPath + grep.fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        if (lines.isEmpty()) throw new IllegalStateException("File is empty");

        if (r)
            for (String line : lines) {
                if (line.isEmpty()) continue;
                matcher = pattern.matcher(line);
                if (v) {
                    if (!matcher.find()) answer.add(line);
                } else {
                    if (matcher.find()) answer.add(line);
                }
            }
        else
            for (String line : lines) {
                if (line.isEmpty()) continue;
                boolean caseIgnoreContains = line.toLowerCase().contains(word.toLowerCase());
                if (v && i) {
                    if (!caseIgnoreContains) answer.add(line);
                } else if (v) {
                    if (!line.contains(word)) answer.add(line);
                } else if (i) {
                    if (caseIgnoreContains) answer.add(line);
                } else {
                    if (line.contains(word)) answer.add(line);
                }
            }

        return answer;
    }
}