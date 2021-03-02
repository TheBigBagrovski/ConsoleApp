package consoleApp.java;
//зашерить преокт на гитзаб (VCS)

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public final class GrepLauncher {

    @Option(name = "[-v]")
    private String v;

    @Option(name = "[-r]")
    private  String r;

    @Option(name = "[-i]")
    private  String i;

    @Argument(required = true)
    private  String fileName;

    @Argument(required = true)
    private  Pattern pattern;

    private final static String defaultPath = "./test/consoleApp/resources/";
    //private final static File file;

    public static void main(String[] args) {
        CmdLineParser parser = new CmdLineParser(args);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            return;
        }


    }
}
