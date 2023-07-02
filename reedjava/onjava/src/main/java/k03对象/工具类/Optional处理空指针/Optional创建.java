/*
 * FileName: Optional创建
 * Author:   reedsource
 */
package k03对象.工具类.Optional处理空指针;

import org.junit.Test;
import top.ireed.deal.DealLog;

import java.util.Optional;

/**
 * 功能简述:
 * 〈Optional的创建方法〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class Optional创建 {
    /**
     * 反案例 报异常 NoSuchElementException  没有这样的元素例外
     */
    @Test
    public void whenCreateEmptyOptional_thenNull() {
        Optional<User> emptyOpt = Optional.empty();
        emptyOpt.get();
    }

    /**
     * 不同的元素的创建方法
     */
    @Test
    public void whenCreateOfEmptyOptional_thenNullPointerException() {
        //明确对象不为 null  的时候使用 of()
        User userInfo = new User("张三");
        DealLog.log(userInfo);
        Optional<User> opt = Optional.of(userInfo);


        //当对象为空时  NullPointerException
        try {
            User userInfo1 = null;
            Optional<User> opt1 = Optional.of(userInfo1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        DealLog.log("=======以下=======");
        //如果对象即可能是 null 也可能是非 null，你就应该使用 ofNullable() 方法
        User userInfo1 = null;
        Optional<User> opt1 = Optional.ofNullable(userInfo1);
    }

}
