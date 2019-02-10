import parser.Parser;

import java.io.StringReader;

public class Main {
    public static void main(String[] args) throws Exception {
        StringReader reader = new StringReader("((- 64 * (-(-1)) + 2 ^ (49 /7)) /64)");
        Parser parser = new Parser(reader);
        int result = parser.parseExpression();
        System.out.println(result);
    }
}
