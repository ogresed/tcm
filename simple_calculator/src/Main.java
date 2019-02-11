import lexer.Lexeme;
import lexer.Lexer;
import parser.Parser;

import java.io.StringReader;

public class Main {
    public static void main(String[] args) throws Exception {
        StringReader reader = new StringReader("()()(()(()-+-44)");
        Lexer lexer = new Lexer(reader);

       Lexeme lexeme = lexer.getLexeme();
        lexeme = lexer.getLexeme();
        lexeme = lexer.getLexeme();
        lexeme = lexer.getLexeme();
        lexeme = lexer.getLexeme();
        lexeme = lexer.getLexeme();
        lexeme = lexer.getLexeme();
    }
}
