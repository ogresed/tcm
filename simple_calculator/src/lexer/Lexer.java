package lexer;

import java.io.IOException;
import java.io.Reader;

public class Lexer {
    private static final char END_OF_FILE = '#';
    private int current;
    private StringBuffer buffer;
    private Reader reader;

    public Lexer(Reader reader) {
        this.reader = reader;
        buffer = new StringBuffer();
        try {
            current = reader.read();
        } catch (IOException e) {
            System.out.println("Impossible read character");
        }
    }

    public Lexeme getLexeme() throws Exception {
        Lexeme lexeme = new Lexeme();
        boolean b = true;
        while(b) {
            switch (current) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9': {
                    buffer.append((char)current);
                    current = reader.read();
                    break;
                }
                case '^':
                case '+':
                case '-':
                case '/':
                case '*': {
                    if(buffer.length() != 0) {
                        lexeme.setType(LexemeType.Number);
                        lexeme.setLex(buffer.toString());
                        buffer.delete(0, buffer.length());
                        b = false;
                        break;
                    }
                    lexeme.setLex(String.valueOf((char)current));
                    lexeme.setType(LexemeType.Operation);
                    current = reader.read();
                    b = false;
                    break;
                }
                case '(':
                case ')': {
                    if(buffer.length() != 0) {
                        lexeme.setType(LexemeType.Number);
                        lexeme.setLex(buffer.toString());
                        buffer.delete(0, buffer.length());
                        b = false;
                        break;
                    }
                    lexeme.setLex(String.valueOf((char)current));
                    lexeme.setType(LexemeType.Bracket);
                    current = reader.read();
                    b = false;
                    break;
                }
                case -1: {
                    if(buffer.length() != 0) {
                        lexeme.setType(LexemeType.Number);
                        lexeme.setLex(buffer.toString());
                        buffer.delete(0, buffer.length());
                        b = false;
                        break;
                    }
                    lexeme.setLex(String.valueOf(END_OF_FILE));
                    lexeme.setType(LexemeType.EOF);
                    b = false;
                    break;
                }
                case ' ': {
                    if(buffer.length() != 0) {
                        lexeme.setType(LexemeType.Number);
                        lexeme.setLex(buffer.toString());
                        buffer.delete(0, buffer.length());
                        b = false;
                    }
                    current = reader.read();
                    break;
                }
                default: {
                    if(buffer.length() != 0) {
                        lexeme.setType(LexemeType.Number);
                        lexeme.setLex(buffer.toString());
                        buffer.delete(0, buffer.length());
                        b = false;
                        break;
                    }
                    throw new Exception("Wrong character");
                }
            }
        }
        return lexeme;
    }
}
