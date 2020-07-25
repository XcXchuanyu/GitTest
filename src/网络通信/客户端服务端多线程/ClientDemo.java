package 网络通信.客户端服务端多线程;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("172.16.7.34",8989);

        Scanner input = new Scanner(System.in);
        //因为是发送词语，以及词语之间的分割问题，我们选择PrintSteam和BufferedReader
        PrintStream ps = new PrintStream(socket.getOutputStream());

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while (true) {
            System.out.print("请输入词语：");
            String word = input.next();

            //发送给服务器端
            ps.println(word);

            if("bye".equals(word)) {
                break;
            }

            String result = br.readLine();
            System.out.println("Server return "+result);
        }


    }
}
