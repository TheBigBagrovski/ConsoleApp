package consoleApp.java;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;

public final class GrepLauncher {

    @Option(name = "-v")
    private Boolean v;

    @Option(name = "-r")
    private Boolean r;

    @Option(name = "-i")
    private Boolean i;

    @Argument(required = true)
    private Pattern pattern;

    @Argument(required = true, index = 1)
    private String fileName;

    private final static String defaultPath = "./test/consoleApp/resources/";

    public static void main(String[] args) {
        new GrepLauncher().launch(args);
    }
    public void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            return;
        }

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(defaultPath + fileName), UTF_8);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        for(String line : lines) {
            System.out.println(line);
        }

    }
}
