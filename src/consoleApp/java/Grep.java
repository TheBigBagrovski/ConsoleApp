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

    public Grep(boolean v, boolean i, boolean r, String word, String fileName) {
        this.v = v;
        this.i = i;
        this.r = r;
        this.word = word;
        this.fileName = fileName;
    }

    public void textFilter(OutputStream outputStream) {
        Pattern pattern = null;
        if (r && i) pattern = Pattern.compile(word, Pattern.CASE_INSENSITIVE);
        else if (r) pattern = Pattern.compile(word);
        File file = new File(fileName);
        try {
            FileReader fr = new FileReader(file, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(fr);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            String line = reader.readLine();
            boolean firstTime = true;
            while (line != null) {
                if (r) {
                    if (regexMatches(pattern, line, v)) {
                        if (!firstTime) outputStreamWriter.write("\n");
                        else firstTime = false;
                        outputStreamWriter.write(line);
                    }
                } else if (stringMatches(word, line, v, i)) {
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

    private boolean regexMatches(Pattern pattern, String line, boolean v) {
        Matcher matcher = pattern.matcher(line);
        boolean lol = matcher.find();
        if (v) return !lol;
        else return lol;
    }

    private boolean stringMatches(String word, String line, boolean v, boolean i) {
        if (i) {
            word = word.toLowerCase();
            line = line.toLowerCase();
        }
        if (v) return !line.contains(word);
        else return line.contains(word);
    }
}