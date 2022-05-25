/*
 * FileName: 序列化对象
 * Author:   reedsource
 */
package e对象序列化;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 序列化对象
 * ObjectOutputStream 类用来序列化一个对象，如下的 序列化对象 例子实例化了一个 Serializable接口实现 对象，并将该对象序列化到一个文件中。
 * <p>
 * 该程序执行后，就创建了一个名为 employee.ser 文件。该程序没有任何输出，但是你可以通过代码研读来理解程序的作用。
 * <p>
 * 注意： 当序列化一个对象到文件时， 按照 Java 的标准约定是给文件一个 .ser 扩展名。
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/25 15:00
 * reedsource@189.cn
 */
class 序列化对象 {
	public static void main(String[] args) {
		Serializable接口实现 e = new Serializable接口实现();
		e.name = "Reyan Ali";
		e.address = "Phokka Kuan, Ambehta Peer";
		e.SSN = 11122333;
		e.number = 101;
		try {
			FileOutputStream fileOut = new FileOutputStream("/tmp/employee.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(e);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in /tmp/employee.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}
