package parser;

import lexer.*;
import java.io.Reader;


public class Parser {
    private Lexer lexer;
    private Lexeme current;

    public Parser(Reader reader) {
        try {
            lexer = new Lexer(reader);
            current = lexer.getLexeme();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int parseExpression() throws Exception {
        int tmp = parseTerm();
        //current = lexer.getLexeme();
        int sign;
        while("+".equals(current.getLex()) || "-".equals(current.getLex())) {
            sign = "+".equals(current.getLex()) ? 1 : -1;
            current = lexer.getLexeme();
            tmp+= sign * parseTerm();
        }
        return tmp;
    }

    public int parseTerm() throws Exception {
        int tmp = parseFactor();
        //current = lexer.getLexeme();
        while("*".equals(current.getLex()) || "/".equals(current.getLex())) {
            if("*".equals(current.getLex())) {
                current = lexer.getLexeme();
                tmp*=parseFactor();
            }
            else {
                current = lexer.getLexeme();
                tmp/=parseFactor();
            }
            //current = lexer.getLexeme();
        }
        return tmp;
    }

    public int parseFactor() throws Exception {
        /*int tmp = Integer.parseInt(current.getLex());
        current = lexer.getLexeme();
        return tmp;*/

        int temp = parsePower();
        if ("^".equals(current.getLex())) {
            current = lexer.getLexeme();
            return (int)Math.pow(temp, parseFactor());
        }
        return temp;
    }

    public int parsePower() throws Exception {
        int sign = 1;
        if ("-".equals(current.getLex())) {
            sign = -1;
            current = lexer.getLexeme();
        }
        return sign*parseAtom();
    }

    public int parseAtom() throws Exception {
        int temp = 0;
        if (current.getType() == LexemeType.Number) {
            temp = Integer.valueOf(current.getLex());
            current = lexer.getLexeme();
        }
        if("(".equals(current.getLex())) {
            current = lexer.getLexeme();
            temp = parseExpression();
            if(!")".equals(current.getLex())){
                throw new Exception("Error calculate");
            }
            current = lexer.getLexeme();
        }
        return temp;
    }
}
