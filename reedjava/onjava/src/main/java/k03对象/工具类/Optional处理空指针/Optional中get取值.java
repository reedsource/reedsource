/*
 * FileName: Optional中get取值
 * Author:   reedsource
 */
package k03对象.工具类.Optional处理空指针;

import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * 功能简述:
 * 〈访问 Optional 对象的值〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class Optional中get取值 {
    /**
     * 从 Optional 实例中取回实际值对象的方法之一是使用 get() 方法：
     * 方法会在值为 null 的时候抛出异常
     */
    @Test
    public void get值() {
        String name = null;
        Optional<String> opt = Optional.ofNullable(name);

        assertEquals("John", opt.get());
    }

    /**
     * 要避免异常，你可以选择首先验证是否有值
     */
    @Test
    public void get值子值() {
        User user = new User("john@gmail.com", "1234");
        Optional<User> opt = Optional.of(user);

        //验证方法1  断言一个条件为真。如果不是，它就会抛出一个AssertionFailedError。
        assertTrue(opt.isPresent());

        assertEquals(user.getEmail(), opt.get().getEmail());

        //验证方法2  该方法除了执行检查，还接受一个Consumer(消费者) 参数，
        //如果对象不是空的，就对执行传入的 Lambda 表达式
        opt.ifPresent(u -> assertEquals(user.getEmail(), u.getEmail()));
    }
}
