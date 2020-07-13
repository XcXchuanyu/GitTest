package IO流;
import java.io.*;

/*
使用流处理方式，将数据提取出来
 */
public class copyImageTest {
    public static void main(String[] args){
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try{
            fos = new FileOutputStream(".\\src\\IO流\\puff2.jpg");
            fis = new FileInputStream(".\\src\\IO流\\puff.jpg");
            byte[] buf = new byte[1024];

            int len = 0;

            while ((len = fis.read(buf))!=-1) {
                fos.write(buf, 0, len);
            }
        }
        catch (IOException e){
            throw new RuntimeException("复制失败");
        }
        finally {
            try{
                if(fis!=null)
                    fis.close();
            }
            catch (IOException e){
                throw new RuntimeException("关闭失败");
            }
        }
    }
}
