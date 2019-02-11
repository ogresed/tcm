import parser.Parser;
import java.io.StringReader;

public class Main {
    public static void main(String[] args) {
        StringReader reader = new StringReader("((- 64 * (-(-1)) + 2 ^ (49 /7)) /64)");
        Parser parser;
        try {
            parser = new Parser(reader);
            int answer = 0;
            try {
                answer = parser.parseExpression();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println(answer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
