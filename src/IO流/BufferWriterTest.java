package IO流;
import java.io.*;

public class BufferWriterTest {
    public static void main(String[] args) throws IOException{
        FileWriter fw = new FileWriter(".\\src\\IO流\\IOtest01.txt");

        BufferedWriter bufw = new BufferedWriter(fw);
        for(int x = 0; x<105;x++) {
            bufw.write("asdas"+x);
            bufw.newLine();
            bufw.flush();
        }
        //BufferedWriter强制要求在关闭之前，需要手动flush
        bufw.close();
    }
}
