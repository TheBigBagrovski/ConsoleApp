package consoleApp.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

    ByteArrayOutputStream bos;

    @BeforeEach
    public void initBos() {
        bos = new ByteArrayOutputStream();
    }

    @Test
    public void simpleTest() { //grep GoodBye simpleTest.txt
        Grep grep = new Grep(false, false, false, "GoodBye", "simpleTest.txt");
        grep.textFilter(bos);
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "текст текст GoodBye\nGoodBye";
        assertEquals(expected, actual);
    }

    @Test
    public void v_Test() { //grep -v GoodBye simpleTest.txt
        Grep grep = new Grep(true, false, false, "GoodBye", "simpleTest.txt");
        grep.textFilter(bos);
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "тест текст\nGoodBee текст\nтекст текст GOODBYE\nтекст текст goodbye\n2345678";
        assertEquals(expected, actual);
    }

    @Test
    public void i_Test() { //grep -i GoodBye simpleTest.txt
        Grep grep = new Grep(false, true, false, "GoodBye", "simpleTest.txt");
        grep.textFilter(bos);
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "текст текст GOODBYE\nтекст текст goodbye\nтекст текст GoodBye\nGoodBye";
        assertEquals(expected, actual);
    }

    @Test
    public void iv_Test() { //grep -v -i GoodBye simpleTest.txt
        Grep grep = new Grep(true, true, false, "GoodBye", "simpleTest.txt");
        grep.textFilter(bos);
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "тест текст\nGoodBee текст\n2345678";
        assertEquals(expected, actual);
    }

    @Test
    public void emptyLinesTest() { //grep hello emptyLinesTest.txt
        Grep grep = new Grep(false, false, false, "hello", "emptyLinesTest.txt");
        grep.textFilter(bos);
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "hello\nhello";
        assertEquals(expected, actual);
    }

    @Test
    public void emptyFileTest() { //grep hello emptyFileTest.txt
        Grep grep = new Grep(false, false, false, "hello", "emptyFileTest.txt");
        grep.textFilter(bos);
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "";
        assertEquals(expected, actual);
    }

    @Test
    public void invertedEmptyLinesTest() { //grep -v hello emptyLinesTest.txt
        Grep grep = new Grep(true, false, false, "hello", "emptyLinesTest.txt");
        grep.textFilter(bos);
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "\n\n\n\n\n\n\nhel\n\n";
        assertEquals(expected, actual);
    }

    @Test
    public void findEmptyLinesTest() { //grep hello emptyLinesTest.txt
        Grep grep = new Grep(false, false, true, "^\\s*$", "emptyLinesTest.txt");
        grep.textFilter(bos);
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "\n\n\n\n\n\n\n\n";
        assertEquals(expected, actual);
    }

    @Test
    public void r_Test() { //grep -r [a-z] simpleTest.txt
        Grep grep = new Grep(false, false, true, "[a-z]", "simpleTest.txt");
        grep.textFilter(bos);
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "GoodBee текст\nтекст текст goodbye\nтекст текст GoodBye\nGoodBye";
        assertEquals(expected, actual);
    }

    @Test
    public void vr_Test() { //grep -v -r [a-z] simpleText.txt
        Grep grep = new Grep(true, false, true, "[a-z]", "simpleTest.txt");
        grep.textFilter(bos);
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "тест текст\nтекст текст GOODBYE\n2345678";
        assertEquals(expected, actual);
    }

    @Test
    public void ir_Test() { //grep -i -r [a-z] simpleText.txt
        Grep grep = new Grep(false, true, true, "[a-z]", "simpleTest.txt");
        grep.textFilter(bos);
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "GoodBee текст\nтекст текст GOODBYE\nтекст текст goodbye\nтекст текст GoodBye\nGoodBye";
        assertEquals(expected, actual);
    }

    @Test
    public void vir_Test() { //grep -v -i -r [a-z] simpleText.txt
        Grep grep = new Grep(true, true, true, "[a-z]", "simpleTest.txt");
        grep.textFilter(bos);
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "тест текст\n2345678";
        assertEquals(expected, actual);
    }

    @Test
    public void stringOrPatternTest() { //grep [aob] stringOrPatternTest.txt
        Grep grep = new Grep(false, false, false, "[aob]", "stringOrPatternTest.txt");
        grep.textFilter(bos);
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "[aob]\n[aob] 23";
        assertEquals(expected, actual);
    }

    @Test
    public void i_stringOrPatternTest() { //grep -i [aob] stringOrPatternTest.txt
        Grep grep = new Grep(false, true, false, "[aob]", "stringOrPatternTest.txt");
        grep.textFilter(bos);
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "[aob]\n[aob] 23\n[AOB]";
        assertEquals(expected, actual);
    }

    @Test
    public void r_stringOrPatternTest() { //grep -r [aob] stringOrPatternTest.txt
        Grep grep = new Grep(false, false, true, "[aob]", "stringOrPatternTest.txt");
        grep.textFilter(bos);
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "[aob]\naboobaboabaobao\n[aob] 23";
        assertEquals(expected, actual);
    }

    @Test
    public void ir_stringOrPatternTest() { //grep -i -r [aob] stringOrPatternTest.txt
        Grep grep = new Grep(false, true, true, "[aob]", "stringOrPatternTest.txt");
        grep.textFilter(bos);
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "[aob]\naboobaboabaobao\n[aob] 23\n[AOB]";
        assertEquals(expected, actual);
    }

    @Test
    public void vir_stringOrPatternTest() { //grep -v -i -r [aob] stringOrPatternTest.txt
        Grep grep = new Grep(true, true, true, "[aob]", "stringOrPatternTest.txt");
        grep.textFilter(bos);
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "rtrtrtrtrttr";
        assertEquals(expected, actual);
    }
}