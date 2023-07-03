/*
 * FileName: SystemProperty
 * Author:   reedsource
 */
package main.java.k01实现案例;

import top.ireed.deal.DealLog;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * 功能简述:
 * 〈当前系统信息类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class SystemProperty {

    public static void main(String[] args) {

        DealLog.log("os.name 当前系统 : " + System.getProperty("os.name"));

        DealLog.log("java.home jre路径 : " + System.getProperty("java.home"));
		/*
		JDK 1.8 = 52
		JDK 1.7 = 51
		JDK 1.6 =50
		JDK 1.5 = 49
		JDK 1.4 = 48
		JDK 1.3 = 47
		JDK 1.2 = 46
		JDK 1.1 = 45*/
        DealLog.log("java.class.version class版本 : " + System.getProperty("java.class.version"));

        DealLog.log("java.class.path 系统类加载器加载字节码class的路径 : " + System.getProperty("java.class.path"));

        DealLog.log("java.library.path 加载库时搜索的路径列表 : " + System.getProperty("java.library.path"));

        DealLog.log("java.io.tmpdir 默认的临时文件路径 : " + System.getProperty("java.io.tmpdir"));

        DealLog.log("java.compiler 要使用的 JIT 编译器的名称 : " + System.getProperty("java.compiler"));

        DealLog.log("java.ext.dirs 一个或多个扩展目录的路径: " + System.getProperty("java.ext.dirs"));

        DealLog.log("user.name 用户的账户名称: " + System.getProperty("user.name"));

        DealLog.log("user.home 用户的主目录: " + System.getProperty("user.home"));

        DealLog.log("user.dir 当前工程根目录: " + System.getProperty("user.dir"));

        DealLog.log("===================");

        DealLog.log("package: 当前class文件路径及名称: " + SystemProperty.class.getPackage().getName());

        DealLog.log("package: 当前class文件路径及名称更多" + SystemProperty.class.getPackage().toString());

        DealLog.log("=========================");

        String packName = SystemProperty.class.getPackage().getName();
        DealLog.log("反射 当前class文件路径及名称:" + packName);

        DealLog.log("替换点后的当前class文件路径及名称: " + packName.replaceAll("\\.", "/"));

        DealLog.log(System.getProperty("user.dir") + "/" + (SystemProperty.class.getPackage().getName()).replaceAll("\\.", "/") + "/");
    }
}
