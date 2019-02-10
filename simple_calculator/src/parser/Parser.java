package parser;

import lexer.LexemeType.*;
import lexer.*;
import java.io.Reader;


public class Parser {
    private Lexer lexer;
    private Lexeme current;

    public Parser(Reader reader) {
        lexer = new Lexer(reader);
        try {
            current = lexer.getLexeme();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int parseExpression() throws Exception {
        int sign = 1;
        int temp = 0;
        LexemeType type = current.getType();
        switch (type) {
            case Bracket: {
                if("(".equals(current.getLex())) {
                    current = lexer.getLexeme();
                    temp+=sign*parseExpression();
                }
                else
                    return temp;
                break;
            }
            case Operation:{
                if("+".equals(current.getLex())) {
                    current = lexer.getLexeme();
                    sign = 1;
                }
                if("-".equals(current.getLex())) {
                    current = lexer.getLexeme();
                    sign = -1;
                }
            }
            case Number: {
                temp = Integer.parseInt(current.getLex());
                current = lexer.getLexeme();
                break;
            }
            case EOF: {
                return temp;
            }
        }
        return temp;
    }
}
