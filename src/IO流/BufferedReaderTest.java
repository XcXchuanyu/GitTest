package IO流;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderTest {
    public static void main(String[] args) throws IOException {
        FileReader fr  = new FileReader(".\\src\\IO流\\IOtest01.txt");
        BufferedReader bufr = new BufferedReader(fr);

        String line = null;

        while((line = bufr.readLine())!=null)
            System.out.println(line);

        bufr.close();
    }
}
