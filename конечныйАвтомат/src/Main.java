import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("few arguments");
            return;
        }
        try {
            File description = new File(args[0]);
            File text = new File(args[1]);

            Automate automate = load(description);
            boolean result =  processing(automate, text);
            System.out.println(result);
        } catch (NullPointerException | IOException e) {
            System.out.println("Error");
        }
    }
    private static boolean processing(Automate automate, File text) throws IOException {
        int currentState = 0;
        FileInputStream stream = new FileInputStream(text);
        DataInputStream inputStream = new DataInputStream(stream);
        int n = inputStream.read();
        while (n != -1) {
            String s = String.valueOf(currentState) + " " + String.valueOf((char)n);
            String st = automate.map.get(s);
            if(st == null) {
                return false;
            }
            currentState = Integer.parseInt(st);
            n = inputStream.read();
        }
        for(int d : automate.endStates) {
            if(currentState == d)
                return true;
        }
        return false;
    }
    private static Automate load(File description) throws IOException {
        Automate automate = new Automate();
        DataInputStream stream = new DataInputStream(new FileInputStream(description));
        String[] states = stream.readLine().split(" ");
        if(states.length == 0) {
            System.out.println("not end states");
            return null;
        }
        int [] st = new int[states.length];
        for(int i = 0; i < states.length; i++) {
            st[i] = Integer.parseInt(states[i]);
        }
        automate.endStates = st;
        String[] p;
        int n = Integer.parseInt(stream.readLine());
        Map<String, String> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            p = stream.readLine().split("-");
            map.put(p[0], p[1]);
        }
        automate.map = map;
        return automate;
    }
    static class Automate {
        Map<String, String> map;
        int [] endStates;
        Automate () {
        }
    }
}
