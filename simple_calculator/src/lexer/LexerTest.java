package lexer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.StringReader;

import static org.junit.Assert.*;

public class LexerTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();



    @Test
    public void getLexeme() throws Exception {
        Lexer lexer;
        Lexeme  lexeme;
        //1
        StringReader reader = new StringReader("345w");
        lexer = new Lexer(reader);
        lexeme = lexer.getLexeme();
        assertEquals("345", lexeme.getLex());
        //2
        exception.expect(Exception.class);
        exception.expectMessage("Wrong character");
        lexer.getLexeme();
        //3
        StringReader reader2 = new StringReader("    5");
        lexer = new Lexer(reader2);
        lexeme = lexer.getLexeme();
        assertEquals(LexemeType.Number, lexeme.type);
        //4
        lexeme = lexer.getLexeme();
        assertEquals(LexemeType.EOF, lexeme.type);
        lexeme = lexer.getLexeme();
        assertEquals(LexemeType.EOF, lexeme.type);
        //5
        StringReader reader3 = new StringReader("() + - ^/*");
        lexer = new Lexer(reader3);
        lexeme = lexer.getLexeme();
        assertEquals("(", lexeme.lex);
        lexeme = lexer.getLexeme();
        assertEquals(")", lexeme.lex);
        lexeme = lexer.getLexeme();
        assertEquals("+", lexeme.lex);
        lexeme = lexer.getLexeme();
        assertEquals("-", lexeme.lex);
        lexeme = lexer.getLexeme();
        assertEquals("^", lexeme.lex);
        lexeme = lexer.getLexeme();
        assertEquals("/", lexeme.lex);
        lexeme = lexer.getLexeme();
        assertEquals("*", lexeme.lex);
    }
}