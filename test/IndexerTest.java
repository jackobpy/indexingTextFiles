import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IndexerTest {
    private static final Indexer indexer = new Indexer(new SimpleTokenizer());
    File test = new File("files/test.txt");
    File test2 = new File("files/files2/test2.txt");
    File test3 = new File("files/test3.js");

    @Test
    void t1() {
        indexer.indexFile(test.getAbsolutePath());
        assertEquals(List.of(test.getAbsolutePath()),indexer.findWord("bla"));
        assertEquals(null,indexer.findWord("abc"));
    }

    @Test
    void t2() {
        indexer.indexDirectory("files");
        assertEquals(List.of(test.getAbsolutePath(), test2.getAbsolutePath()),indexer.findWord("bla"));
    }

    @Test
    void t3() {
        assertEquals(List.of(test.getAbsolutePath()),indexer.findWord("blabla"));
    }

    @Test
    void getFileExtension() {
        assertEquals("txt", Indexer.getFileExtension(test.getAbsolutePath()));
    }
}