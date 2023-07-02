/*
 * FileName: javaCatalogueFile
 * Author:   reedsource
 */
package k14流.目录操作;

import top.ireed.deal.DealLog;

import java.io.File;

/**
 * 功能简述:
 * 〈目录文件读取删除〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/9 22:57
 * reedsource@189.cn
 */
public class javaCatalogueFile {
    public static void main(String[] args) {
        c0();
        c1();
        m1();

        // 这里修改为自己的测试目录
        File folder = new File("/tmp/java/");
        c2(folder);

    }

    /**
     * 创建目录
     * <p>
     * Java 在 UNIX 和 Windows 自动按约定分辨文件路径分隔符。
     * 如果你在 Windows 版本的 Java 中使用分隔符 (/) ，路径依然能够被正确解析
     */
    private static void c0() {
        String dirname = "/tmp/user/java/bin";
        File d = new File(dirname);
        // 现在创建目录
        d.mkdirs();
    }

    /**
     * 读取目录
     * <p>
     * 一个目录其实就是一个 File 对象，它包含其他文件和文件夹。
     * 如果创建一个 File 对象并且它是一个目录，那么调用 isDirectory() 方法会返回 true。
     * 可以通过调用该对象上的 list() 方法，来提取它包含的文件和文件夹的列表。
     * 下面展示的例子说明如何使用 list() 方法来检查一个文件夹中包含的内容
     */
    private static void c1() {
        String dirname = "/tmp";
        File f1 = new File(dirname);
        if (f1.isDirectory()) {
            DealLog.log("目录 " + dirname);
            String s[] = f1.list();
            for (int i = 0; i < s.length; i++) {
                File f = new File(dirname + "/" + s[i]);
                if (f.isDirectory()) {
                    DealLog.log(s[i] + " 是一个目录");
                } else {
                    DealLog.log(s[i] + " 是一个文件");
                }
            }
        } else {
            DealLog.log(dirname + " 不是一个目录");
        }
    }

    /**
     * 删除目录或文件
     * <p>
     * 删除文件可以使用 java.io.File.delete() 方法。
     * 以下代码会删除目录 /tmp/java/，
     * 需要注意的是当删除某一目录时，
     * 必须保证该目录下没有其他文件才能正确删除，否则将删除失败。
     */
    private static void c2(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    c2(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }

    /**
     * 读取系统根目录 及相关信息
     */
    private static void m1() {
        //列出系统根目录
        File[] listRoots = File.listRoots();
        //遍历
        for (File file : listRoots) {
            DealLog.log(file.getAbsolutePath() + ",总计容量total:" + file.getTotalSpace() + ", 剩余容量free:" + file.getFreeSpace() + ",可用容量 usable:" + file.getUsableSpace()
            );
        }
    }
}
