package lexer;

import static lexer.LexemeType.EOF;

public class Lexeme {
    LexemeType type;
    String lex;

    Lexeme() {
        type = EOF;
    }

    public LexemeType getType() {
        return type;
    }

    public String getLex() {
        return lex;
    }

    void setType(LexemeType type) {
        this.type = type;
    }

    void setLex(String lex) {
        this.lex = lex;
    }
}

