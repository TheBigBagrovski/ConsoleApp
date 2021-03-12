package consoleApp.java;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

    ByteArrayOutputStream bos = new ByteArrayOutputStream();

    @Test
    public void simpleTest() { //grep GoodBye simpleTest.txt
        Grep grep = new Grep(false, false, false, "GoodBye", "simpleTest.txt", bos);
        grep.textFilter();
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "����� ����� GoodBye\nGoodBye\n";
        assertEquals(expected, actual);
    }

    @Test
    public void v_Test() { //grep -v GoodBye simpleTest.txt
        Grep grep = new Grep(true, false, false, "GoodBye", "simpleTest.txt", bos);
        grep.textFilter();
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "���� �����\nGoodBee �����\n����� ����� GOODBYE\n����� ����� goodbye\n2345678\n";
        assertEquals(expected, actual);
    }

    @Test
    public void i_Test() { //grep -i GoodBye simpleTest.txt
        Grep grep = new Grep(false, true, false, "GoodBye", "simpleTest.txt", bos);
        grep.textFilter();
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "����� ����� GOODBYE\n����� ����� goodbye\n����� ����� GoodBye\nGoodBye\n";
        assertEquals(expected, actual);
    }

    @Test
    public void iv_Test() { //grep -v -i GoodBye simpleTest.txt
        Grep grep = new Grep(true, true, false, "GoodBye", "simpleTest.txt", bos);
        grep.textFilter();
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "���� �����\nGoodBee �����\n2345678\n";
        assertEquals(expected, actual);
    }

    @Test
    public void emptyLinesTest() { //grep hello emptyLinesTest.txt
        Grep grep = new Grep(false, false, false, "hello", "emptyLinesTest.txt", bos);
        grep.textFilter();
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "hello\nhello\n";
        assertEquals(expected, actual);
    }

    @Test
    public void invertedEmptyLinesTest() { //grep -v hello emptyLinesTest.txt
        Grep grep = new Grep(true, false, false, "hello", "emptyLinesTest.txt", bos);
        grep.textFilter();
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "hel\n";
        assertEquals(expected, actual);
    }

    @Test
    public void r_Test() { //grep -r [a-z] simpleTest.txt
        Grep grep = new Grep(false, false, true, "[a-z]", "simpleTest.txt", bos);
        grep.textFilter();
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "GoodBee �����\n����� ����� goodbye\n����� ����� GoodBye\nGoodBye\n";
        assertEquals(expected, actual);
    }

    @Test
    public void vr_Test() { //grep -v -r [a-z] simpleText.txt
        Grep grep = new Grep(true, false, true, "[a-z]", "simpleTest.txt", bos);
        grep.textFilter();
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "���� �����\n����� ����� GOODBYE\n2345678\n";
        assertEquals(expected, actual);
    }

    @Test
    public void ir_Test() { //grep -i -r [a-z] simpleText.txt
        Grep grep = new Grep(false, true, true, "[a-z]", "simpleTest.txt", bos);
        grep.textFilter();
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "GoodBee �����\n����� ����� GOODBYE\n����� ����� goodbye\n����� ����� GoodBye\nGoodBye\n";
        assertEquals(expected, actual);
    }

    @Test
    public void vir_Test() { //grep -v -i -r [a-z] simpleText.txt
        Grep grep = new Grep(true, true, true, "[a-z]", "simpleTest.txt", bos);
        grep.textFilter();
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "���� �����\n2345678\n";
        assertEquals(expected, actual);
    }

    @Test
    public void stringOrPatternTest() { //grep [aob] stringOrPatternTest.txt
        Grep grep = new Grep(false, false, false, "[aob]", "stringOrPatternTest.txt", bos);
        grep.textFilter();
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "[aob]\n[aob] 23\n";
        assertEquals(expected, actual);
    }

    @Test
    public void i_stringOrPatternTest() { //grep -i [aob] stringOrPatternTest.txt
        Grep grep = new Grep(false, true, false, "[aob]", "stringOrPatternTest.txt", bos);
        grep.textFilter();
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "[aob]\n[aob] 23\n[AOB]\n";
        assertEquals(expected, actual);
    }

    @Test
    public void r_stringOrPatternTest() { //grep -r [aob] stringOrPatternTest.txt
        Grep grep = new Grep(false, false, true, "[aob]", "stringOrPatternTest.txt", bos);
        grep.textFilter();
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "[aob]\naboobaboabaobao\n[aob] 23\n";
        assertEquals(expected, actual);
    }

    @Test
    public void ir_stringOrPatternTest() { //grep -i -r [aob] stringOrPatternTest.txt
        Grep grep = new Grep(false, true, true, "[aob]", "stringOrPatternTest.txt", bos);
        grep.textFilter();
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "[aob]\naboobaboabaobao\n[aob] 23\n[AOB]\n";
        assertEquals(expected, actual);
    }

    @Test
    public void vir_stringOrPatternTest() { //grep -v -i -r [aob] stringOrPatternTest.txt
        Grep grep = new Grep(true, true, true, "[aob]", "stringOrPatternTest.txt", bos);
        grep.textFilter();
        String actual = bos.toString(StandardCharsets.UTF_8);
        String expected = "rtrtrtrtrttr\n";
        assertEquals(expected, actual);
    }
}