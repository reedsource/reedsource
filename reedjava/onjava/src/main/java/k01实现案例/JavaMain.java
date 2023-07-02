/*
 * FileName: JavaMain
 * Author:   reedsource
 */
package k01实现案例;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java启动main方法时附带数据   使用控制台java 运行时 附带数据进入主main方法〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/6 22:52
 * reedsource@189.cn
 */
public class JavaMain {
    public static void main(String[] args) {
        if (args.length != 2) {
            DealLog.log("用法: java javaMain <host1 msg> <host2 msg>");
            System.exit(1);
        }
        String host1 = args[0];
        String host2 = args[1];
        DealLog.log(host1);
        DealLog.log(host2);
    }
}
