package 网络通信.客户端服务端多线程;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
    public static void main(String[] args) throws IOException {
        //开启服务器
        ServerSocket server = new ServerSocket(8989);
        boolean flag = true;
        while (flag) {
            //接受客户端连接
            Socket socket = server.accept();

            MessageHandler mh = new MessageHandler(socket);
            mh.start();

        }
        server.close();
    }
}

class MessageHandler extends Thread{
    private Socket socket;

    MessageHandler(Socket socket) {
        super();
        this.socket = socket;
    }

    @Override
    public void run() {
        //获取输入流，并且需要把字节流转化为字符流
        System.out.println("mh run");
        InputStream in = null;
        try {
            in = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);

            //客户端创建输出流
            OutputStream out = socket.getOutputStream();
            PrintStream ps = new PrintStream(out);
            String word;
            while ((word = br.readLine()) != null) {
                if ("bye".equals(word)) {
                    break;
                }
                StringBuilder sb = new StringBuilder(word);
                sb.reverse();

                ps.println(sb.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}