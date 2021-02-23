import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test_topsort {

    public static void main(String[] args) {
        BufferedReader reader;
        int n = -1;
        Topological_sort t = new Topological_sort();
        String file = "/home/jovan/Jovans courses/Programming Techniques/Assignment_1/topsort_test.txt";

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            n = Integer.parseInt(line);

        } catch (IOException e) {
            e.printStackTrace();
        }

        t.read(n, file);
        t.baginalization();
        t.topsorts();
        System.out.println("Total number of topological sorts: " + t.topsortsNum);

    }


}
