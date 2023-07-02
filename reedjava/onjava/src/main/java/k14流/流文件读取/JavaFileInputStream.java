/*
 * FileName: JavaFileInputStream
 * Author:   reedsource
 */
package main.java.k14流.流文件读取;

import org.junit.Test;
import top.ireed.deal.DealLog;

import java.io.*;

/**
 * 功能简述:
 * 〈流读数据〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/9 22:57
 * reedsource@189.cn
 */
public class JavaFileInputStream {


    /**
     * 逐个字节读取io
     */
    @Test
    public void m1() {
        try {
            //1. 在当前程序与 件之间建 流通道,就是创建FileInputStream对象
            FileInputStream fis = new FileInputStream("d:/file/333.txt");

            //2. 读取数据, read() 法读取 个字节, 如果读到 件末尾返回-1
            int data = fis.read();

            //当读取的内容不是-1,即没有到 件末尾就继续读取
            while (data != -1) {
                //转换为字符打印到屏幕上
                DealLog.log((char) data);
                //读取下 个字节
                data = fis.read();
            }
            //3. 关闭流
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void main1() {
        try {
            //可以使用字符串类型的文件名来创建一个输入流对象来读取文件
            InputStream f = new FileInputStream("D:\\top\\topcache");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //也可以使用一个文件对象来创建一个输入流对象来读取文件
        //我们首先得使用 File() 方法来创建一个文件对象
        File f = new File("D:\\top\\topcache");
        try {
            InputStream out = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


/*
1	public void close() throws IOException{}          关闭此文件输入流并释放与此流有关的所有系统资源。抛出IOException异常。
2	protected void finalize()throws IOException {}    这个方法清除与该文件的连接。确保在不再引用文件输入流时调用其 close 方法。抛出IOException异常。
3	public int read(int r)throws IOException{}        这个方法从 InputStream 对象读取指定字节的数据。返回为整数值。返回下一字节数据，如果已经到结尾则返回-1。
4	public int read(byte[] r) throws IOException{}    这个方法从输入流读取r.length长度的字节。返回读取的字节数。如果是文件结尾则返回-1。
5	public int available() throws IOException{}       返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取的字节数。返回一个整数值。

除了 InputStream 外，还有一些其他的输入流

* */
    }
}
