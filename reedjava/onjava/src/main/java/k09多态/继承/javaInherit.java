/*
 * FileName: javaInherit
 * Author:   reedsource
 */
package main.java.k09多态.继承;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈继承是java面向对象编程技术的一块基石，因为它允许创建分等级层次的类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/9 22:57
 * reedsource@189.cn
 * <p>
 * 继承就是子类继承父类的特征和行为，使得子类对象（实例）具有父类的实例域和方法，或子类从父类继承方法，使得子类具有父类相同的行为。
 * <p>
 * 子类拥有父类非 private 的属性、方法。
 * <p>
 * 子类可以拥有自己的属性和方法，即子类可以对父类进行扩展。
 * <p>
 * 子类可以用自己的方式实现父类的方法。
 * <p>
 * Java 的继承是单继承，但是可以多重继承，单继承就是一个子类只能继承一个父类，多重继承就是，例如 A 类继承 B 类，B 类继承 C 类，所以按照关系就是 C 类是 B 类的父类，B 类是 A 类的父类，这是 Java 继承区别于 C++ 继承的一个特性。
 * <p>
 * 提高了类之间的耦合性（继承的缺点，耦合度高就会造成代码之间的联系越紧密，代码独立性越差）。
 */
public class javaInherit {
    public static void main(String[] args) {
        Animal a = new Animal();
        a.eat();
        Dog d = new Dog();
        d.eatTest();
    }

/*
继承关键字
继承可以使用 extends 和 implements 这两个关键字来实现继承，
而且所有的类都是继承于 java.lang.Object，
当一个类没有继承的两个关键字，则默认继承object（这个类在 java.lang 包中，所以不需要 import）祖先类。

extends关键字
在 Java 中，类的继承是单一继承，也就是说，一个子类只能拥有一个父类，所以 extends 只能继承一个类。

public class Animal {
    private String name;
    private int id;
    public Animal(String myName, String myid) {
        //初始化属性值
    }
    public void eat() {  //吃东西方法的具体实现  }
    public void sleep() { //睡觉方法的具体实现  }
}

public class Penguin  extends  Animal{
}

implements关键字
使用 implements 关键字可以变相的使java具有多继承的特性，
使用范围为类继承接口的情况，可以同时继承多个接口（接口跟接口之间采用逗号分隔）。

public interface A {
    public void eat();
    public void sleep();
}

public interface B {
    public void show();
}

public class C implements A,B {
}

*/

}


class Animal {
    void eat() {
        DealLog.log("animal : eat");
    }
}

/**
 * super关键字与this关键字
 * <p>
 * super关键字：我们可以通过super关键字来实现对父类成员的访问，用来引用当前对象的父类。
 * this关键字：指向自己的引用。
 */
class Dog extends Animal {
    @Override
    void eat() {
        DealLog.log("dog : eat");
    }

    void eatTest() {
        this.eat();   // this 调用自己的方法
        super.eat();  // super 调用父类方法
    }
}

/*
final 关键字声明类可以把类定义为不能继承的，即最终类；或者用于修饰方法，该方法不能被子类重写
声明类：
final class 类名 {//类体}

声明方法：
修饰符(public/private/default/protected) final 返回值类型 方法名(){//方法体}

注:实例变量也可以被定义为 final，被定义为 final 的变量不能被修改。
被声明为 final 类的方法自动地声明为 final，但是实例变量并不是 final



*/