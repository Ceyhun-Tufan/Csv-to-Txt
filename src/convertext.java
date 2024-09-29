import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class convertext {
    public static void main(String[] args) {
        System.out.println("Csv to Txt app is starting!");
        String filepath = "";
        String textSeperator = " "; // to change when needed
        String txtname = "result"; // result text name

        try {
            filepath = args[0];
            if (args.length > 1) {
                textSeperator = args[1];
            }
        } catch (Exception e) {
            System.out.println(String.format(
                    "Closing the app, you must give the args: filepath and (optional) the separator! Error:\n  %s", e));
            return;
        }
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filepath));) {
            String[] vals;
            String row = "";
            BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("%s.txt", txtname)));
            while ((line = br.readLine()) != null) {
                row = "";
                vals = line.split(",", -1); // never forget to hate regex
                for (int i = 0; i < vals.length; i++) {
                    String val = vals[i].isEmpty() ? "null" : vals[i]; // Handle empty values

                    row += val;
                    if (i < vals.length - 1) {
                        row += textSeperator;
                    }
                }
                row += "\n";
                writer.write(row);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(String.format("Closing the app due to error:\n  %s", e));
        }

    }
}
