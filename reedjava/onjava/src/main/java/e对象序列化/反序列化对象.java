/*
 * FileName: 反序列化对象
 * Author:   reedsource
 */
package e对象序列化;

import top.ireed.deal.DealLog;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 反序列化对象
 * 面的 反序列化对象 程序实例了反序列化，/cache/io/io测试数据.txt 存储了 Serializable接口实现 对象。
 * 反序列化对象.java 文件代码
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/25 15:00
 * reedsource@189.cn
 */
class 反序列化对象 {
	public static void main(String[] args) {
		Serializable接口实现 e = null;
		try {
			FileInputStream fileIn = new FileInputStream("/cache/io/io测试数据.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (Serializable接口实现) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			DealLog.log("Serializable接口实现 class not found");
			c.printStackTrace();
			return;
		}
		DealLog.log("Deserialized Serializable接口实现...");
		DealLog.log("Name: " + e.name);
		DealLog.log("Address: " + e.address);
		DealLog.log("SSN: " + e.SSN);
		DealLog.log("Number: " + e.number);
	}

	//Deserialized Serializable接口实现...
	//Name: Reyan Ali
	//Address:Phokka Kuan, Ambehta Peer
	//SSN: 0
	//Number:101
}
