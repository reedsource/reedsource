package top.ireed.tools;

import java.io.*;
import java.util.ArrayList;

/**
 * txt工具集
 * 2023年07月20日 首次提交, 用于pdf转文本后的内容整理
 */
public class TxtTools {
    public static void main(String[] args) {
        String file = "D:\\reed\\Downloads\\开窍开悟开智.md";
        String fileName = "D:\\reed\\Downloads\\开窍开悟开智-1.md";
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
     * 规则处理 * @param lines 行 * @return
     */
    private static ArrayList<String> rule(ArrayList<String> lines) {
        ArrayList<String> result = new ArrayList<>();
        StringBuilder res = new StringBuilder();
        for (String line : lines) {
            //0 ##开头不做操作 添加换行符
            if (line.length()>2 && "##".equals(line.substring(0, 2))) {
                //1遇到。句号换行
                String mm = res.toString();
                if (!"".equals(mm)) {
                    String[] ms = mm.split("。");
                    for (String m : ms) {
                        result.add(m + "。");
                    }
                }
                res.delete(0, res.length());
                result.add(line);
            } else {
                res.append(line);
            }
        }
        String mm = res.toString();
        if (!"".equals(mm)) {
            String[] ms = mm.split("。");
            for (String m : ms) {
                result.add(m + "。");
            }
        }
        return result;
    }

    /**
     * @param file txt文本路径 * @return 按行读取的txt文本内容 * @throws IOException 异常
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