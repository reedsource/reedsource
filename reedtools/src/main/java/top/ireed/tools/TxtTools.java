package top.ireed.tools;

import java.io.*;
import java.util.ArrayList;

/**
 * txt工具集
 * 20240111 用于遍历查询文本行
 * 当文本行符合规则时,屏蔽
 * 将屏蔽处理后的内容写到新文件
 */
public class TxtTools {
    public static void main(String[] args) {
        String file = "D:\\reed\\Downloads\\超级异性吸引术.md";
        String fileName = "D:\\reed\\Downloads\\超级异性吸引术-1.md";
        try {
            ArrayList<String> lines = GetTxtReader(file);
            ArrayList<String> result = rule(lines);
            StringBuilder res = new StringBuilder();
            for (String s : result) {
                res.append(s).append("\r\n");
            }
            File toFile = new File(fileName);
            FileWriter writer = new FileWriter(toFile);
            writer.write(res.toString());
            writer.close();
            System.out.println("文件写入成功。");
        } catch (IOException e) {
            System.out.println("文本处理失败: " + e.getMessage());
        }
    }

    /**
     * 规则处理 行
     */
    private static ArrayList<String> rule(ArrayList<String> lines) {
        ArrayList<String> result = new ArrayList<>();
        StringBuilder res = new StringBuilder();
        //当开头为  ![](assets/   md文件图片链接
        for (String line : lines) {
            if (!line.startsWith("![](assets/")) {
                result.add(line);
            }
        }
        return result;
    }

    /**
     * 读取文本的内容,返回一个文本行的数组
     *
     * @param file txt文本路径
     */
    private static ArrayList<String> GetTxtReader(String file) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }
}