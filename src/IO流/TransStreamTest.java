package IO流;
/*
将读取的字符串转化成大写字母并打印，在主函数中，我们使用BufferedReader将字符串存入缓冲区，后面自己实现，将字符存入
StringBUilder之后打印
InputStreamReader就是转换流，用来将字节流转换为字符流
 */
import java.io.*;

public class TransStreamTest {
    public static void main(String[] args)throws IOException{
        InputStream in = System.in;
        //将字节流转化为字符流对象,输入的是字节流？
        InputStreamReader isr = new InputStreamReader(in);
        //为了提高读写效率，将字符串录入缓冲区
        BufferedReader bufr = new BufferedReader(isr);

        OutputStream out = System.out;
        OutputStreamWriter osw = new OutputStreamWriter(out);
        BufferedWriter bufw = new BufferedWriter(osw);

        String line = null;

        while((line = bufr.readLine())!=null){
            if("over".equals(line))
                break;
            osw.write(line.toUpperCase());
            osw.flush();
            //输出流需要被刷新才能记入文档中
        }
        bufr.close();
    }
}

class MyBufferedReader{
    public void ReadLineandUpper() throws IOException{
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        //将字符存入StringBuilder中，打印后清空

        while(true){
            int ch = in.read();
            if(ch!='\r')continue;
            if(ch!='\n'){
                String s = sb.toString();
                if("over".equals(s))
                    break;
                System.out.println(s.toUpperCase());
                sb.delete(0,sb.length());
            }
            else sb.append((char)ch);
        }
    }
}