package consoleApp.java;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    private final boolean r;
    private final boolean i;
    private final boolean v;
    private final String word;
    private final String fileName;
    private Pattern pattern = null;

    public Grep(boolean v, boolean i, boolean r, String word, String fileName) {
        this.v = v;
        this.i = i;
        this.r = r;
        this.fileName = fileName;
        if (!i) this.word = word;
        else this.word = word.toLowerCase();
        if (this.r && this.i) this.pattern = Pattern.compile(word, Pattern.CASE_INSENSITIVE);
        else if (this.r) this.pattern = Pattern.compile(word);
    }

    public void textFilter(OutputStream outputStream) {
        File file = new File(fileName);
        try {
            FileReader fr = new FileReader(file, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(fr);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            String line = reader.readLine();
            boolean firstTime = true;
            while (line != null) {
                if (check(line)) {
                    if (!firstTime) outputStreamWriter.write("\n");
                    else firstTime = false;
                    outputStreamWriter.write(line);
                }
                line = reader.readLine();
            }
            outputStreamWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private boolean check(String line) {
        if (r) return regexMatches(line);
        else return stringMatches(line);
    }

    private boolean regexMatches(String line) {
        Matcher matcher = pattern.matcher(line);
        boolean matchesOrNot = matcher.find();
        if (v) return !matchesOrNot;
        else return matchesOrNot;
    }

    private boolean stringMatches(String line) {
        if (i) line = line.toLowerCase();
        if (v) return !line.contains(word);
        else return line.contains(word);
    }
}