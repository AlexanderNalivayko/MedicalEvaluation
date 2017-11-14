package columns;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColumnsTitlesFileProcessorTest {

    List<String> list;

    @BeforeEach
    void setUp() {
        list = Arrays.asList("Test1","Test2","Test3");
    }

    @Test
    void exportTitles() {
        ColumnsTitlesFileProcessor ctfp = new ColumnsTitlesFileProcessor();
        ctfp.exportTitles(list);
        System.out.println(list.get(0));
        List<String> check = ctfp.importTitles();
        System.out.println(check.get(0));
        assertEquals(list.get(0), check.get(0));
    }

}