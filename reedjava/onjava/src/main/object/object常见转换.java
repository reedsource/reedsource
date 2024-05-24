package main.object;

import java.io.*;

public class object常见转换 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 假设我们已经有了一个对象
        Object originalObject = "Hello, World!";

        // 序列化对象
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(originalObject);
        oos.flush();
        byte[] bytes = bos.toByteArray();

        // 反序列化对象
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object deserializedObject = ois.readObject();

        // 输出反序列化后的对象
        System.out.println(deserializedObject);
    }
}
