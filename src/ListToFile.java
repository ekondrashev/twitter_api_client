import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 *
 * @author mutagen
 */
public class ListToFile {
 
    public static void main(String[] args) {
 
        List<String> str = new ArrayList<String>();
        str.add("Hello");
        str.add("World!");
 
        Writer writer = null;
        try {
            writer = new FileWriter("file.txt");
            for (String line : str) {
                writer.write(line);
//                тут мог бы быть пробел если надо в одну строку
                writer.write(System.getProperty("line.separator"));
            }
            writer.flush();
        } catch (Exception e) {
            Logger.getLogger(ListToFile.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                }
            }
        }
    }
}