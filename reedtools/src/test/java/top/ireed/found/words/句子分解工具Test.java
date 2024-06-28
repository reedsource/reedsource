package top.ireed.found.words;

import junit.framework.TestCase;

/**
 * 句子解析工具
 */
public class 句子分解工具Test extends TestCase {
    public void testDict0() {
        String msg = "分词的目标是将连续的汉字序列切分成有意义的词语";

        for (int i = 0; i < msg.length(); i++) {
            for (int j = 1; j < msg.length() - i; j++) {
                int right = j + i;
                String s = msg.substring(i, right);
                System.out.print(s);
                System.out.print("    ");

                if (i > 0) {
                    System.out.print(msg.charAt(i - 1));
                }
                System.out.print("    ");

                if (i + s.length() < msg.length() - 1) {
                    System.out.print(msg.charAt(i + s.length()));
                }
                System.out.print("    ");
                if (i > 0 && i + s.length() < msg.length() - 1) {
                    System.out.print(msg.charAt(i - 1) + "" + msg.charAt(i + s.length()));
                }

                System.out.println();
            }
        }
    }
}