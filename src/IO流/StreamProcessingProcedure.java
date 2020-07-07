package IO流;
/*
确定流处理方式，需要明确以下几步：
1.明确源和目的
    源：输入流。InputStream  Reader
    目的：输出流。OutputStream Writer
2.操作的数据是否为纯文本
    是：字符流
    否：字节流
3.体系明确之后，在明确要使用哪个具体的对象。
    通过设备进行区分
    源设备：内存，硬盘，键盘
    目的设备：内存，硬盘，控制台
 */

/*
例题1，将文本文件中的数据储存到另一个文本文件中
       首先源：文本，可以使用Reader
       明确设备：硬盘，可以使用FileReader

       其次目的：文本，可以使用Writer
       设备：硬盘，使用FileWriter

        是否需要运用缓冲区进行功能增强？是。
        BufferedReader br = new BufferedReader(fr);

 */
public class StreamProcessingProcedure {
}
