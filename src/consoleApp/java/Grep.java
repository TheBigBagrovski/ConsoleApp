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
        File file = new File("./test/consoleApp/resources/", fileName);
        try {
            FileReader fr = new FileReader(file, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(fr);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            String line = reader.readLine();
            if (r) while (line != null) {
                if (regexMatches(pattern, line, v)) output(outputStreamWriter, line);
                line = reader.readLine();
            }
            else {
                while (line != null) {
                    if (stringMatches(word, line, v, i)) output(outputStreamWriter, line);
                    line = reader.readLine();
                }
            }
            outputStreamWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void output(OutputStreamWriter outputStreamWriter, String line) throws IOException {
        outputStreamWriter.write(line);
        outputStreamWriter.write("\n");
    }

    private boolean regexMatches(Pattern pattern, String line, boolean v) {
        Matcher matcher = pattern.matcher(line);
        if (v) return !matcher.find();
        else return matcher.find();
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