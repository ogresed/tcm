class Lexeme {
    LexemeType type;
    String lex;

    Lexeme() {

    }

    Lexeme(int character, LexemeType type) {
        lex = String.valueOf((char)character);
        this.type = type;
    }

    Lexeme(String lex, LexemeType type) {
        this.lex = lex;
        this.type = type;
    }

    public LexemeType getType() {
        return type;
    }

    public String getLex() {
        return lex;
    }

    public void setType(LexemeType type) {
        this.type = type;
    }

    public void setLex(String lex) {
        this.lex = lex;
    }
}

