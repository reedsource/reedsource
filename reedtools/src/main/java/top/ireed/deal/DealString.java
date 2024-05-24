package top.ireed.deal;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * 字符串处理工具类
 *
 * @author ireed
 */
public class DealString {

    private static final char[] SS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private DealString() {
    }

    /**
     * 删除字符串首尾空格,判断字符串是否为空
     *
     * @param inputString 字符串
     * @return 删除首尾空之后的字符串
     */
    public static String deleteEmptyString(String inputString) {
        if (inputString == null) {
            inputString = "";
        }
        //删除首尾空
        return inputString.trim();
    }
//======================================================================================================

    /**
     * 转换编码
     *
     * @param strString 要转换的字符串
     * @param srcCode   原编码
     * @param destCode  目标编码
     * @return 转换完成的字符串
     */
    public static String convertCode(String strString, String srcCode, String destCode) {
        String returnString = deleteEmptyString(strString);
        try {
            byte[] ascii = returnString.getBytes(srcCode);
            returnString = new String(ascii, destCode);
        } catch (Exception e) {
            DealLog.log("转换编码异常");
        }
        return returnString;
    }

    /**
     * 替换Html非法字符，字符串中的字符替换为转译字符
     *
     * @param htmlString 要转换替换的字符串
     * @return 替换转译后的我字符串
     */
    public static String replaceHtmlErrorString(String htmlString) {
        //创建字符串缓冲区
        StringBuilder returnString = new StringBuilder(htmlString.length());
        char ch;
        for (int i = 0; i < htmlString.length(); i++) {
            ch = htmlString.charAt(i);
            switch (ch) {
                case '<':
                    returnString.append("&lt");
                    break;
                case '>':
                    returnString.append("&gt");
                    break;
                case ' ':
                    returnString.append("&nbsp");
                    break;
                case '\\':
                    returnString.append("&acute");
                    break;
                default:
                    returnString.append(ch);
                    break;
            }
        }
        //逻辑值转换为字符串
        return returnString.toString();
    }

//======================================================================================================

    /**
     * 替换字符串中的单引号为其它字符串
     *
     * @param inputString 要转换单引号的字符串
     * @return 替换完成的字符串
     */
    public static String replaceString(String inputString) {
        StringBuilder returnString = new StringBuilder(inputString.length());
        char charOne;
        String oneCharString = "'";
        char oneChar = oneCharString.charAt(0);

        for (int i = 0; i < inputString.length(); i++) {
            charOne = inputString.charAt(i);
            if (charOne == oneChar) {
                returnString.append("''");
            } else {
                returnString.append(charOne);
            }
        }
        return returnString.toString();
    }

    /**
     * 数字金额格式化表达式
     *
     * @param num 金额数值
     * @return 返回中文金钱格式字符串 例如￥1,234,566.00
     */
    public static String formatMoneyString(int num) {
        //创建金钱格式化对象
        NumberFormat formatc = NumberFormat.getCurrencyInstance(Locale.CHINA);
        return formatc.format(num);
    }

    //======================================================================================================

    /**
     * 格式化数值
     *
     * @param num 数值
     * @return 格式化后的数值文本
     */
    public static String formatString(int num) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###.0000");
        return decimalFormat.format(num);
    }

    /**
     * 倒找字符出现的位置
     *
     * @param str 字符串
     * @param ch  查找的字符
     * @param n   倒数第n次出现的位置
     * @return 正数位置
     */
    public static int inverted(String str, char ch, int n) {
        char[] array = str.toCharArray();
        for (int i = array.length - 1; i > -1; i--) {
            if (array[i] == ch && --n == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 数值字符串解压
     *
     * @param str 压缩完成字符串
     * @return 解压字符串
     */
    public static String jy(String str) {
        try {
            int a = 0;
            for (int i = 0; i < str.length(); i++) {
                for (int j = 0; j < SS.length; j++) {
                    if (str.charAt(i) == SS[j]) {
                        a = a * 62 + j;
                        break;
                    }
                }
            }
            return String.valueOf(a);
        } catch (Exception e) {
            DealLog.log("解压文本异常,无法解压缩");
            return str;
        }
    }

    /**
     * 数值字符串压缩   注意有长度限制
     *
     * @param str 待压缩数值字符串
     * @return 压缩完成字符串
     */
    public static String ys(String str) {
        try {
            int a = Integer.parseInt(str);
            int yu;
            int z;
            StringBuilder s = new StringBuilder();
            do {
                z = a / 62;
                yu = a % 62;
                //如果余数为0,刚好整除
                if (yu == 0) {
                    s.insert(0, "0");
                } else {
                    s.insert(0, SS[yu]);
                }
                a = z;
                if (0 < a && a < 62) {
                    s.insert(0, SS[a]);
                }
            } while (a >= 62);
            return String.valueOf(s);
        } catch (NumberFormatException e) {
            DealLog.log("数值文本异常,数值文本无法压缩");
            return str;
        }
    }

    /**
     * Object组转String
     *
     * @param o object组
     * @return object组组合成的String
     */
    public static String objectToString(Object... o) {
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < o.length; i++) {
            if (i > 0) {
                a.append(" ");
            }
            a.append(o[i].toString());
        }

        return a.toString();
    }

    //======================================================================================================
    //字符串排序相关

    //首字母排序


}

