package consoleApp.java;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

    @Test
    public void simpleTest() { //grep GoodBye simpleTest.txt
        Grep grep = new Grep(false, false, false, "GoodBye", "simpleTest.txt", new ByteArrayOutputStream());
        grep.textFilter();
        /*
         expected = List.of("текст текст GoodBye", "GoodBye");
        assertEquals(expected, actual);*/
    }

    @Test
    public void v_Test() { //grep -v GoodBye simpleTest.txt
        Grep grep = new Grep(true, false, false, "GoodBye", "simpleTest.txt");
        List<String> actual = grep.textFilter();
        List<String> expected = List.of(
                "тест текст",
                "GoodBee текст",
                "текст текст GOODBYE",
                "текст текст goodbye",
                "2345678");
        assertEquals(expected, actual);
    }

    @Test
    public void i_Test() { //grep -i GoodBye simpleTest.txt
        Grep grep = new Grep(false, true, false, "GoodBye", "simpleTest.txt");
        List<String> actual = grep.textFilter();
        List<String> expected = List.of(
                "текст текст GOODBYE",
                "текст текст goodbye",
                "текст текст GoodBye",
                "GoodBye");
        assertEquals(expected, actual);
    }

    @Test
    public void iv_Test() { //grep -v -i GoodBye simpleTest.txt
        Grep grep = new Grep(true, true, false, "GoodBye", "simpleTest.txt");
        List<String> actual = grep.textFilter();
        List<String> expected = List.of("тест текст", "GoodBee текст", "2345678");
        assertEquals(expected, actual);
    }

    @Test
    public void emptyLinesTest() { //grep hello emptyLinesTest.txt
        Grep grep = new Grep(false, false, false, "hello", "emptyLinesTest.txt");
        List<String> actual = grep.textFilter();
        List<String> expected = List.of("hello", "hello");
        assertEquals(expected, actual);
    }

    @Test
    public void invertedEmptyLinesTest() { //grep -v hello emptyLinesTest.txt
        Grep grep = new Grep(true, false, false, "hello", "emptyLinesTest.txt");
        List<String> actual = grep.textFilter();
        List<String> expected = List.of("hel");
        assertEquals(expected, actual);
    }

    @Test
    public void r_Test() { //grep -r [a-z] simpleTest.txt
        Grep grep = new Grep(false, false, true, "[a-z]", "simpleTest.txt");
        List<String> actual = grep.textFilter();
        List<String> expected = List.of(
                "GoodBee текст",
                "текст текст goodbye",
                "текст текст GoodBye",
                "GoodBye");
        assertEquals(expected, actual);
    }

    @Test
    public void vr_Test() { //grep -v -r [a-z] simpleText.txt
        Grep grep = new Grep(true, false, true, "[a-z]", "simpleTest.txt");
        List<String> actual = grep.textFilter();
        List<String> expected = List.of(
                "тест текст",
                "текст текст GOODBYE",
                "2345678");
        assertEquals(expected, actual);
    }

    @Test
    public void ir_Test() { //grep -i -r [a-z] simpleText.txt
        Grep grep = new Grep(false, true, true, "[a-z]", "simpleTest.txt");
        List<String> actual = grep.textFilter();
        List<String> expected = List.of(
                "GoodBee текст",
                "текст текст GOODBYE",
                "текст текст goodbye",
                "текст текст GoodBye",
                "GoodBye"
        );
        assertEquals(expected, actual);
    }

    @Test
    public void vir_Test() { //grep -v -i -r [a-z] simpleText.txt
        Grep grep = new Grep(true, true, true, "[a-z]", "simpleTest.txt");
        List<String> actual = grep.textFilter();
        List<String> expected = List.of("тест текст", "2345678");
        assertEquals(expected, actual);
    }

    @Test
    public void stringOrPatternTest() { //grep [aob] stringOrPatternTest.txt
        Grep grep = new Grep(false, false, false, "[aob]", "stringOrPatternTest.txt");
        List<String> actual = grep.textFilter();
        List<String> expected = List.of("[aob]", "[aob] 23");
        assertEquals(expected, actual);
    }

    @Test
    public void i_stringOrPatternTest() { //grep -i [aob] stringOrPatternTest.txt
        Grep grep = new Grep(false, true, false, "[aob]", "stringOrPatternTest.txt");
        List<String> actual = grep.textFilter();
        List<String> expected = List.of("[aob]", "[aob] 23", "[AOB]");
        assertEquals(expected, actual);
    }

    @Test
    public void r_stringOrPatternTest() { //grep -r [aob] stringOrPatternTest.txt
        Grep grep = new Grep(false, false, true, "[aob]", "stringOrPatternTest.txt");
        List<String> actual = grep.textFilter();
        List<String> expected = List.of("[aob]", "aboobaboabaobao", "[aob] 23");
        assertEquals(expected, actual);
    }

    @Test
    public void ir_stringOrPatternTest() { //grep -i -r [aob] stringOrPatternTest.txt
        Grep grep = new Grep(false, true, true, "[aob]", "stringOrPatternTest.txt");
        List<String> actual = grep.textFilter();
        List<String> expected = List.of("[aob]", "aboobaboabaobao", "[aob] 23", "[AOB]");
        assertEquals(expected, actual);
    }

    @Test
    public void vir_stringOrPatternTest() { //grep -v -i -r [aob] stringOrPatternTest.txt
        Grep grep = new Grep(true, true, true, "[aob]", "stringOrPatternTest.txt");
        List<String> actual = grep.textFilter();
        List<String> expected = List.of("rtrtrtrtrttr");
        assertEquals(expected, actual);
    }
}