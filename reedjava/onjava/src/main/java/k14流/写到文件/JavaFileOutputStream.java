/*
 * FileName: JavaFileOutputStream
 * Author:   reedsource
 */
package main.java.k14流.写到文件;

import org.junit.Test;
import top.ireed.deal.DealLog;

import java.io.*;

/**
 * 功能简述:
 * 〈写数据〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/9 22:57
 * reedsource@189.cn
 */
public class JavaFileOutputStream {

    /**
     * FileOutputStream
     * 将数组数据写入到文件
     * 将文件内容按字节读取
     * <p>
     * InputStream 和 OutputStream 用法
     * 创建OutputStream 对象完成后，就可以使用下面的方法来写入流或者进行其他的流操作。
     * 1	public void close() throws IOException{}        关闭此文件输入流并释放与此流有关的所有系统资源。抛出IOException异常。
     * 2	protected void finalize()throws IOException {}  这个方法清除与该文件的连接。确保在不再引用文件输入流时调用其 close 方法。抛出IOException异常。
     * 3	public void write(int w)throws IOException{}    这个方法把指定的字节写到输出流中。
     * 4	public void write(byte[] w)                     把指定数组中w.length长度的字节写到OutputStream中。
     * <p>
     * <p>
     * 首先创建文件test.txt，
     * 并把给定的数字以二进制形式写进该文件，同时输出到控制台上
     */
    @Test
    public void c0() {
        try {
            byte[] bWrite = {11, 21, 3, 40, 5, 21, 3, 40, 5, 21, 3, 40, 5};

            //可以使用一个文件对象来创建一个输出流来写文件。
            // 我们首先得使用File()方法来创建一个文件对象：new File
            File f = new File("D:\\top\\topcache\\topcode\\file\\a.txt");
            // 先得到文件的上级目录，并创建上级目录，在创建文件
            if (!f.exists()) {
                f.getParentFile().mkdir();
                f.createNewFile();
            }

            //输出流
            OutputStream os = new FileOutputStream(f);
            for (int x = 0; x < bWrite.length; x++) {
                // writes the bytes
                os.write(bWrite[x]);
            }
            os.close();

            //输入流
            InputStream is = new FileInputStream(f);
            int size = is.available();

            for (int i = 0; i < size; i++) {
                System.out.print((char) is.read() + "  ");
            }
            is.close();
        } catch (IOException e) {
            DealLog.log("Exception");
        }
    }

    /**
     * FileOutputStream
     * OutputStreamWriter 输出流写入器
     * InputStreamReader 输入流读取器
     * <p>
     * 该类用来创建一个文件并向文件中写数据。
     * 如果该流在打开文件进行输出前，目标文件不存在，那么该流会创建该文件。
     * 有两个构造方法可以用来创建 FileOutputStream 对象。
     * 使用字符串类型的文件名来创建一个输出流对象
     *
     * @throws IOException IOException
     */
    @Test
    public void c1() throws IOException {
        File f = new File("D:\\top\\topcache\\topcode\\file\\b.txt");

        // 先得到文件的上级目录，并创建上级目录，在创建文件
        if (!f.exists()) {
            f.getParentFile().mkdir();
            f.createNewFile();
        }

        // 构建FileOutputStream对象,文件不存在会自动新建--------------------------------
        FileOutputStream fop = new FileOutputStream(f);

        // 构建OutputStreamWriter对象,参数可以指定编码,默认为操作系统默认编码,windows上是gbk
        OutputStreamWriter writer = new OutputStreamWriter(fop, "GBK");

        // 写入到缓冲区
        writer.append("中文输入");

        // 换行
        writer.append("\r\n");

        // 刷新缓存冲,写入到文件,如果下面已经没有写入的内容了,直接close也会写入
        writer.append("English");

        // 关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉
        writer.close();

        // 关闭输出流,释放系统资源
        fop.close();

        // 构建FileInputStream对象---------------------------------------------------
        FileInputStream fip = new FileInputStream(f);

        // 构建InputStreamReader对象,编码与写入相同
        InputStreamReader reader = new InputStreamReader(fip, "GBK");

        StringBuffer sb = new StringBuffer();
        while (reader.ready()) {
            // 转成char加到StringBuffer对象中
            sb.append((char) reader.read());
        }
        DealLog.log(sb.toString());
        // 关闭读取流
        reader.close();

        // 关闭输入流,释放系统资源
        fip.close();
    }
}
