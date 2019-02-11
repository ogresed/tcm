package parser;

import lexer.*;
import java.io.Reader;
import static lexer.LexemeType.*;

public class Parser {
    private Lexer lexer;
    private Lexeme current;

    public Parser(Reader reader) throws Exception {
            lexer = new Lexer(reader);
            current = lexer.getLexeme();
    }

    public int parseExpression() throws Exception {
        int tmp = parseTerm();
        int sign;
        while(current.getType() == Minus || current.getType() == Plus) {
            sign = current.getType() == Plus ? 1 : -1;
            current = lexer.getLexeme();
            tmp+= sign * parseTerm();
        }
        return tmp;
    }

    int parseTerm() throws Exception {
        int tmp = parseFactor();
        while(current.getType() == Multiply || current.getType() == Divide) {
            if(current.getType() == Multiply) {
                current = lexer.getLexeme();
                tmp*=parseFactor();
            }
            else {
                current = lexer.getLexeme();
                tmp/=parseFactor();
            }
        }
        return tmp;
    }

    int parseFactor() throws Exception {
        int temp = parsePower();
        if (current.getType() == Degree) {
            current = lexer.getLexeme();
            return (int)Math.pow(temp, parseFactor());
        }
        return temp;
    }

    int parsePower() throws Exception {
        int sign = 1;
        if (current.getType() == Minus) {
            sign = -1;
            current = lexer.getLexeme();
        }
        return sign*parseAtom();
    }

    int parseAtom() throws Exception {
        int temp = 0;
        if (current.getType() == Number) {
            temp = Integer.valueOf(current.getLex());
            current = lexer.getLexeme();
        }
        if(current.getType() == LeftBracket) {
            current = lexer.getLexeme();
            temp = parseExpression();
            if(current.getType() != RightBracket) {
                throw new Exception("Error calculate");
            }
            current = lexer.getLexeme();
        }
        return temp;
    }
}
