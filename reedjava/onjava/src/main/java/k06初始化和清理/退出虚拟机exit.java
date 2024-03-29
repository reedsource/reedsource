/*
 * FileName: 退出虚拟机exit
 * Author:   reedsource
 */
package main.java.k06初始化和清理;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class 退出虚拟机exit {
    public static void main(String[] args) {
        int a = 10;
        int b = 10;
        method(a, b);
        DealLog.log("此消息想要打印,实际因为虚拟机被关闭,不会被打印 ");
        DealLog.log("a" + a, "b" + b);        //a 10
        //b 10
    }

    private static void method(int a, int b) {
        DealLog.log("a", a, "b", b);
        //退出虚拟机
        System.exit(1);
    }

/*
这个方法是用来结束当前正在运行中的java虚拟机。如何status是非零参数，那么表示是非正常退出。
System.exit(0)是将你的整个虚拟机里的内容都停掉了 ，而dispose()只是关闭这个窗口，但是并没有停止整个application exit() 。
无论如何，内存都释放了！也就是说连JVM都关闭了，内存里根本不可能还有什么东西
System.exit(0)是正常退出程序，而System.exit(1)或者说非0表示非正常退出程序
System.exit(status)不管status为何值都会退出程序。和return 相比有以下不同点：
return是回到上一层，而System.exit(status)是回到最上层

如果是非0  表示非正常退出
*/
}
