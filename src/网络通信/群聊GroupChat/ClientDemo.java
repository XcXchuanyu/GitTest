package 网络通信.群聊GroupChat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("172.16.7.35",8989);

        SendThread st = new SendThread(socket);
        st.start();

        ReceiveThread rt = new ReceiveThread(socket);
        rt.start();

        try {
            st.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            rt.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class SendThread extends Thread {
    private Socket socket;

    public SendThread(Socket socket) {
        super();
        this.socket = socket;
    }

    @Override
    public void run() {

        super.run();

        try {
            Scanner scanner =  new Scanner(System.in);
            PrintStream ps = new PrintStream(socket.getOutputStream());

            while (true) {
                String sentence = scanner.nextLine();
                System.out.println("I said: "+sentence);
                ps.println(sentence);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class ReceiveThread extends Thread {
    private Socket socket;

    public ReceiveThread(Socket socket) {
        super();
        this.socket = socket;
    }

    @Override
    public void run() {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (true) {
                    //readline方法应该是阻塞式的
                    String result = br.readLine();
                    System.out.println(result);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}