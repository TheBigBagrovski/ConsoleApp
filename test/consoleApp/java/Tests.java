package consoleApp.java;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {

    private Grep grep;

    @Test
    public void simpleTest() { //grep GoodBye simpleTest.txt
        grep = new Grep(false, false, false, "GoodBye", "simpleTest.txt");
        List<String> actual = grep.textFilter(grep);
        List<String> expected = Arrays.asList("����� ����� GoodBye", "GoodBye");
        assertEquals(expected, actual);
    }

    @Test
    public void v_Test() { //grep -v GoodBye simpleTest.txt
        grep = new Grep(true, false, false, "GoodBye", "simpleTest.txt");
        List<String> actual = grep.textFilter(grep);
        List<String> expected = Arrays.asList(
                "���� �����",
                "GoodBee �����",
                "����� ����� GOODBYE",
                "����� ����� goodbye",
                "2345678");
        assertEquals(expected, actual);
    }

    @Test
    public void i_Test() { //grep -i GoodBye simpleTest.txt
        grep = new Grep(false, true, false, "GoodBye", "simpleTest.txt");
        List<String> actual = grep.textFilter(grep);
        List<String> expected = Arrays.asList(
                "����� ����� GOODBYE",
                "����� ����� goodbye",
                "����� ����� GoodBye",
                "GoodBye");
        assertEquals(expected, actual);
    }

    @Test
    public void iv_Test() { //grep -v -i GoodBye simpleTest.txt
        grep = new Grep(true, true, false, "GoodBye", "simpleTest.txt");
        List<String> actual = grep.textFilter(grep);
        List<String> expected = Arrays.asList("���� �����", "GoodBee �����", "2345678");
        assertEquals(expected, actual);
    }

    @Test
    public void emptyFileTest() { //grep hello emptyFileTest.txt
        grep = new Grep(false, false, false, "hello", "emptyFileTest.txt");
        assertThrows(IllegalStateException.class, () -> grep.textFilter(grep));
    }

    @Test
    public void emptyLinesTest() { //grep hello emptyLinesTest.txt
        grep = new Grep(false, false, false, "hello", "emptyLinesTest.txt");
        List<String> actual = grep.textFilter(grep);
        List<String> expected = Arrays.asList("hello", "hello");
        assertEquals(expected, actual);
    }

    @Test
    public void invertedEmptyLinesTest() { //grep -v hello emptyLinesTest.txt
        grep = new Grep(true, false, false, "hello", "emptyLinesTest.txt");
        List<String> actual = grep.textFilter(grep);
        List<String> expected = Collections.singletonList("hel");
        assertEquals(expected, actual);
    }

    @Test
    public void r_Test() { //grep -r [a-z] simpleTest.txt
        grep = new Grep(false, false, true, "[a-z]", "simpleTest.txt");
        List<String> actual = grep.textFilter(grep);
        List<String> expected = Arrays.asList(
                "GoodBee �����",
                "����� ����� goodbye",
                "����� ����� GoodBye",
                "GoodBye");
        assertEquals(expected, actual);
    }

    @Test
    public void vr_Test() { //grep -v -r [a-z] simpleText.txt
        grep = new Grep(true, false, true, "[a-z]", "simpleTest.txt");
        List<String> actual = grep.textFilter(grep);
        List<String> expected = Arrays.asList(
                "���� �����",
                "����� ����� GOODBYE",
                "2345678");
        assertEquals(expected, actual);
    }

    @Test
    public void ir_Test() { //grep -i -r [a-z] simpleText.txt
        grep = new Grep(false, true, true, "[a-z]", "simpleTest.txt");
        List<String> actual = grep.textFilter(grep);
        List<String> expected = Arrays.asList(
                "GoodBee �����",
                "����� ����� GOODBYE",
                "����� ����� goodbye",
                "����� ����� GoodBye",
                "GoodBye"
        );
        assertEquals(expected, actual);
    }

    @Test
    public void vir_Test() { //grep -v -i -r [a-z] simpleText.txt
        grep = new Grep(true, true, true, "[a-z]", "simpleTest.txt");
        List<String> actual = grep.textFilter(grep);
        List<String> expected = Arrays.asList("���� �����", "2345678");
        assertEquals(expected, actual);
    }

}