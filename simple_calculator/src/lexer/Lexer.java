package lexer;

import java.io.IOException;
import java.io.Reader;

import static lexer.LexemeType.*;

public class Lexer {
    private static final int END_OF_FILE = -1;
    private int current;
    private StringBuffer buffer;
    private Reader reader;

    public Lexer(Reader reader) throws IOException {
        this.reader = reader;
        buffer = new StringBuffer();
        current = reader.read();
    }

    public Lexeme getLexeme() throws Exception {
        Lexeme lexeme = new Lexeme();
        boolean b = true;
        while(b) {
            if(Character.isDigit(current)) {
                buffer.append((char)current);
                current = reader.read();
            }
            else {
                if(buffer.length() != 0) {
                    lexeme.setType(LexemeType.Number);
                    lexeme.setLex(buffer.toString());
                    buffer.delete(0, buffer.length());
                    b = false;
                }
                else if (isOperationOrBracket(current) || -1 == current) {
                    lexeme.setLex(String.valueOf((char)current));
                    b = false;
                    switch (current) {
                        case '^': {
                            lexeme.setType(Degree);
                            break;
                        }
                        case '+': {
                            lexeme.setType(Plus);
                            break;
                        }
                        case '-': {
                            lexeme.setType(Minus);
                            break;
                        }
                        case '/': {
                            lexeme.setType(Divide);
                            break;
                        }
                        case '*': {
                            lexeme.setType(Multiply);
                            break;
                        }
                        case '(': {
                            lexeme.setType(LeftBracket);
                            break;
                        }
                        case ')': {
                            lexeme.setType(RightBracket);
                            break;
                        }
                        case END_OF_FILE: {
                            lexeme.setType(EOF);
                            break;
                        }
                    }
                    current = reader.read();
                }
                else if(current == ' ')
                    current = reader.read();
                else throw new Exception("Wrong character");
            }
        }
        return lexeme;
    }

    private boolean isOperationOrBracket(int current) {
        return current == '+' || current == '-' || current == '*' || current == '/' ||
                current == '^' || current == '(' || current == ')';
    }
}
