/*
 * FileName: DealIo
 * Author:   reedsource
 */
package top.ireed.deal;

import cn.hutool.core.io.IoUtil;
import top.ireed.general.TopException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;

/**
 * 功能简述:
 * 〈IO工具类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/4/26 23:21
 * reedsource@189.cn
 */
public class DealIo {
    /**
     * 将字符串写入到文件中
     * <p>
     * 目录及文件不存在将尝试创建,注意文件存在时,内容将会被覆盖
     *
     * @param file 生成文件的路径
     * @param str  文件的字符串
     */
    public static void toFileIo(File file, String str) throws TopException {
        try {
            //文件不存在  且  创建文件父级目录成功 创建文件成功
            if (!file.exists() && (!DealContents.newContents(file) || !file.createNewFile())) {
                DealLog.log(file, "写入到文件创建失败");
                return;
            }

            // 构建FileOutputStream对象,文件不存在会自动新建--------------------------------
            FileOutputStream fop = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = IoUtil.getUtf8Writer(fop);
            outputStreamWriter.write(str);
            // 关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉
            outputStreamWriter.close();

            // 关闭输出流,释放系统资源
            fop.close();
            DealLog.log(file, "完成");
        } catch (IOException e) {
            throw new TopException("写入到文件异常 路径", file, e);
        }
    }

    /**
     * 读取文件内容到字符串
     *
     * @param path 文件路径
     * @return 文件字符串
     */
    public static String getFileIo(File path) throws TopException {
        StringBuilder sb = new StringBuilder();
        //读取文件的内容
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Files.newInputStream(path.toPath()), StandardCharsets.UTF_8), 512 * 1024)) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append("\r\n");
            }

            return sb.toString();
        } catch (IOException e) {
            throw new TopException("读取文件内容到字符串异常", e);
        }
    }

    /**
     * @param input 流
     * @return byte数组
     * @throws IOException 异常
     */
    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }

    /**
     * @param filePath 文件路径
     * @return 文件base64加密后的流
     */
    public static String fileToString(String filePath) {
        File file = new File(filePath);
        try (InputStream input = new FileInputStream(file)) {
            byte[] buffer = toByteArray(input);
            // 读到buffer字节数组中

            return Base64.getEncoder().encodeToString(buffer);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @param fileContent base64加密后的文件流
     * @param filePath    文件写入位置
     * @return 写入文件
     */
    public static boolean stringToFile(String fileContent, String filePath) {
        byte[] bytes = Base64.getDecoder().decode(fileContent);
        File file = new File(filePath).getParentFile();
        if (!file.exists()) {
            boolean s = file.mkdirs();
            if (!s) {
                return false;
            }
        }
        try (InputStream input = new ByteArrayInputStream(bytes); OutputStream output = new FileOutputStream(filePath)) {
            byte[] buf = new byte[1024 * 8];
            int n;
            while ((n = input.read(buf)) != -1) {
                output.write(buf, 0, n);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
