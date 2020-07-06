package IO流;
/*
重写BufferReader的底层实现，并且自己写MylineNumberReader，使之继承自MyBufferReader
 */

import java.io.*;

public class MyBufferReaderTest {
    public static void main(String[] args) throws IOException{
        FileReader fr = new FileReader(".\\src\\IO流\\IOtest01.txt");
        MyLineNumberReader mlf = new MyLineNumberReader(fr);

        String line = null;
        mlf.setLinenumber(-1);//此处设置为-1，可以使得行号从0开始打印
        while((line = mlf.readLineNumber())!=null)System.out.println(mlf.getLinenumber()+":"+line);
        mlf.close();
    }
}

class MyBufferReader{
//实现了bufferReader的printLine方法
    private Reader r;
    MyBufferReader(Reader r){
        this.r = r;
    }
    public String readLine() throws IOException{
        StringBuilder sb = new StringBuilder();
        int ch = 0;

        while((ch =r.read())!=-1){
            if(ch =='\r')continue;
            if(ch =='\n')return sb.toString();
            else sb.append((char) ch);
        }

        if(sb.length()!=0)return sb.toString();
        return null;
    }

    public void close() throws IOException{
        r.close();
    }

}

class MyLineNumberReader extends MyBufferReader{
//继承自MyBufferReader，实现了打印行号的方法
    private int linenumber;
    private Reader r;
    MyLineNumberReader(Reader r){
        super(r);
    }
    public String readLineNumber() throws IOException{
        linenumber+=1;
        return super.readLine();
    }
    public void setLinenumber(int num){
        this.linenumber = num;
    }
    public int getLinenumber(){
        return linenumber;
    }
}
