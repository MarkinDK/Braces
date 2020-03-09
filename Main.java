import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in))) {
            String line;
            line = reader.readLine();
            TreeSet<PairOfIndex> set = new TreeSet<>();
            StringBuilder currentLine = new StringBuilder();
            for (int i=0;i<line.length();i++){
                char c= line.charAt(i);
                if ((c=='(')||(c==')')||(c=='{')||(c=='}')||(c=='[')||(c==']'))
                    currentLine.append(c);
            }
            System.out.println(currentLine.toString());
            boolean flag=currentLine.length() > 1;
            PairOfIndex minRound;
            PairOfIndex minFig;
            PairOfIndex minQuad;
            while (currentLine.length() > 0 && flag) {
                minRound = bracesPosition(currentLine, "(", ")");
                minFig = bracesPosition(currentLine, "{", "}");
                minQuad = bracesPosition(currentLine, "[", "]");
                if (minRound.isCorrectly())
                    set.add(minRound);
                if (minFig.isCorrectly())
                    set.add(minFig);
                if (minQuad.isCorrectly())
                    set.add(minQuad);
                if (set.size() == 0) {
                    flag = false;
                    break;
                }
                PairOfIndex min = set.first();
                if (min.getDistance() > 1)
                    flag = false;
                else {
                    currentLine.deleteCharAt(min.getClosed());
                    currentLine.deleteCharAt(min.getOpen());
                }
                set.clear();
            }
            if (flag) System.out.println("Всё верно");
            else System.out.println("Не верно");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static PairOfIndex bracesPosition(StringBuilder temp, String braceOpen, String braceClosed) {
        char open = braceOpen.charAt(0);
        char closed = braceClosed.charAt(0);
        PairOfIndex pair = new PairOfIndex(temp.indexOf(braceOpen), temp.indexOf(braceClosed));
        if (pair.isCorrectly()) {
            if (pair.getDistance() == 1)
                return pair;
            int currentOpen = pair.getOpen();
            for (int i = 0; i < temp.length(); i++) {
                if (temp.charAt(i) == closed)
                    if (i - currentOpen < pair.getDistance()) {
                        pair.setOpen(currentOpen);
                        pair.setClosed(i);
                    }
                if (temp.charAt(i) == open) {
                    currentOpen = i;
                    if (pair.getClosed() > i && (pair.getClosed() - i < pair.getDistance()))
                        pair.setOpen(i);
                }
                if (pair.getDistance() == 1) return pair;
            }
        }
        return pair;
    }
}
