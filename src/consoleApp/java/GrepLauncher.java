package consoleApp.java;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public final class GrepLauncher {

    @Option(name = "-v")
    private static boolean v;

    @Option(name = "-r")
    private static boolean r;

    @Option(name = "-i")
    private static boolean i;

    @Argument(required = true)
    private static String word;

    @Argument(required = true, index = 1)
    private static String fileName;

    public static void main(String[] args) {
        new GrepLauncher().launch(args);
    }

    public void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
        }
        Grep grep = new Grep(v, i, r, word, fileName);
        for (String line : grep.textFilter(grep)) {
            System.out.println(line);
        }
    }

}