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
    private final OutputStream outputStream;

    private final static String defaultPath = "./test/consoleApp/resources/";

    public Grep(boolean v, boolean i, boolean r, String word, String fileName, OutputStream outputStream) {
        this.v = v;
        this.i = i;
        this.r = r;
        this.word = word;
        this.fileName = fileName;
        this.outputStream = outputStream;
    }

    public void textFilter() {

        Pattern pattern = null;
        Matcher matcher;
        if (r && i) pattern = Pattern.compile(word, Pattern.CASE_INSENSITIVE);
        else if (r) pattern = Pattern.compile(word);
        File file = new File(defaultPath + fileName);
        try {
            FileReader fr = new FileReader(file, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            if (r) {
                while (line != null) {
                    if (line.isEmpty()) {
                        line = reader.readLine();
                        continue;
                    }
                    byte[] buf = line.getBytes(StandardCharsets.UTF_8);
                    matcher = pattern.matcher(line);
                    if (v) {
                        if (!matcher.find()) outputStream.write(buf);
                    } else {
                        if (matcher.find()) outputStream.write(buf);
                    }
                    line = reader.readLine();
                }
            } else {
                while (line != null) {
                    if (line.isEmpty()) {
                        line = reader.readLine();
                        continue;
                    }
                    boolean caseIgnoreContains = line.toLowerCase().contains(word.toLowerCase());
                    byte[] buf = line.getBytes(StandardCharsets.UTF_8);
                    if (v && i) {
                        if (!caseIgnoreContains) outputStream.write(buf);
                    } else if (v) {
                        if (!line.contains(word)) outputStream.write(buf);
                    } else if (i) {
                        if (caseIgnoreContains) outputStream.write(buf);
                    } else {
                        if (line.contains(word)) outputStream.write(buf);
                    }
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}