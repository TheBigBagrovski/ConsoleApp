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
        File file = new File("test/consoleApp/resources/", fileName);

        try {
            FileReader fr = new FileReader(file, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(fr);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            String line = reader.readLine();
            String space = "\n";

            if (r) {
                while (line != null) {
                    if (line.isEmpty()) {
                        line = reader.readLine();
                        continue;
                    }
                    matcher = pattern.matcher(line);
                    if (v) {
                        if (!matcher.find()) {
                            outputStreamWriter.write(line);
                            outputStreamWriter.write(space);
                        }
                    } else {
                        if (matcher.find()) {
                            outputStreamWriter.write(line);
                            outputStreamWriter.write(space);
                        }
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
                    if (v && i) {
                        if (!caseIgnoreContains) {
                            outputStreamWriter.write(line);
                            outputStreamWriter.write(space);
                        }
                    } else if (v) {
                        if (!line.contains(word)) {
                            outputStreamWriter.write(line);
                            outputStreamWriter.write(space);
                        }
                    } else if (i) {
                        if (caseIgnoreContains) {
                            outputStreamWriter.write(line);
                            outputStreamWriter.write(space);
                        }
                    } else {
                        if (line.contains(word)) {
                            outputStreamWriter.write(line);
                            outputStreamWriter.write(space);
                        }
                    }
                    line = reader.readLine();
                }
            }
            outputStreamWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}