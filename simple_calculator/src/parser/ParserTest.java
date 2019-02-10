package parser;

import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void parseExpression() throws Exception {
        StringReader reader = new StringReader("512 + 512");
        Parser parser = new Parser(reader);
        assertEquals(512* 2, parser.parseExpression());
    }

    @Test
    public void parseTerm() throws Exception {
        StringReader reader = new StringReader("512*2");
        Parser parser = new Parser(reader);
        assertEquals(512* 2, parser.parseTerm());
    }

    @Test
    public void parseFactor() throws Exception {
        StringReader reader = new StringReader("512");
        Parser parser = new Parser(reader);
        assertEquals(512, parser.parseFactor());

        StringReader reader1 = new StringReader("2^9");
        Parser parser1 = new Parser(reader1);
        assertEquals(512, parser1.parseFactor());
    }

    @Test
    public void parsePower() throws Exception {
        StringReader reader = new StringReader("512");
        Parser parser = new Parser(reader);
        assertEquals(512, parser.parsePower());

        StringReader reader1 = new StringReader("-2");
        Parser parser1 = new Parser(reader1);
        assertEquals(-2, parser1.parsePower());
    }

    @Test
    public void parseAtom() throws Exception {
        StringReader reader = new StringReader("512");
        Parser parser = new Parser(reader);
        assertEquals(512, parser.parseAtom());

        StringReader reader1 = new StringReader("((- 64 * (-(-1)) + 2 ^ (49 /7)) /64)");
        Parser parser1 = new Parser(reader1);
        assertEquals(1, parser1.parseAtom());
    }
}