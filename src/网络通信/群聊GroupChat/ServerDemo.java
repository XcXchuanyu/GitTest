package 网络通信.群聊GroupChat;

import com.sun.xml.internal.bind.v2.TODO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ServerDemo {
    private static ArrayList<Socket> waiting;

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(8989);
        waiting = new ArrayList<>();
        while (true) {
            Socket socket = ss.accept();
            waiting.add(socket);
            ServerReceiveThread set = new ServerReceiveThread(socket);
            set.start();

        }

    }

    static class ServerReceiveThread extends Thread {
        private Socket socket;

        public ServerReceiveThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
            while (true) {
                InputStreamReader ips = null;
                try {
                    ips = new InputStreamReader(socket.getInputStream());
                    BufferedReader bfr = new BufferedReader(ips);

                    String word;
                    while ((word = bfr.readLine()) != null) {
                        System.out.println("Received "+word);
                        sendToOthers(socket.getInetAddress().getHostAddress()+" Said: "+word);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        public void sendToOthers(String str) {
            for (Socket item: waiting) {
                if (!item.equals(socket)) {
                    try {
                        OutputStream ops = item.getOutputStream();
                        PrintStream prs = new PrintStream(ops);
                        prs.println(str);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}


