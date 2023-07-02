/*
 * FileName: Optional转换方法Map和flatMap
 * Author:   reedsource
 */
package k03对象.工具类.Optional处理空指针;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * 功能简述:
 * 〈转换值〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class Optional转换方法Map和flatMap {

    // map() 对值应用(调用)作为参数的函数，然后将返回的值包装在 Optional 中。
    // 这就使对返回值进行链试调用的操作成为可能
    @Test
    public void whenMap_thenOk() {
        User user = new User("anna@gmail.com", "1234");
        String email = Optional.of(user)
                .map(User::getEmail).orElse("default@gmail.com");

        assertEquals(email, user.getEmail());
    }

    //flatMap() 也需要函数作为参数，并对值调用这个函数，然后直接返回结果
    @Test
    public void whenFlatMap_thenOk() {
        User user = new User("anna@gmail.com", "1234");
        user.setPosition("Developer");

        //既然 getter 方法返回 String 值的 Optional，
        //你可以在对 User 的 Optional 对象调用 flatMap() 时，用它作为参数。
        // 其返回的值是解除包装的 String 值
        String position = Optional.of(user)
                .flatMap(User::getPosition).orElse("default");

        if (user.getPosition().isPresent()) {
            assertEquals(position, user.getPosition().get());
        }
    }
}
