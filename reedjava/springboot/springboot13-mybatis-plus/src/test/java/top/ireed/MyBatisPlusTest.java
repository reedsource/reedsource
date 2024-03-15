package top.ireed;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.ireed.Mapper.UserMapper;
import top.ireed.model.User;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class MyBatisPlusTest {

    @Resource
    private UserMapper userMapper;

    @Test
    void testSelectAll() {
        System.out.println(("----- 查询user全表 ----------------------------------------"));
        List<User> userList = userMapper.selectList(null);
        Assert.isTrue(5 == userList.size(), "");
        userList.forEach(System.out::println);
    }


    @Test
    void testSelect() {
        System.out.println(("----- 查询 ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.isTrue(5 == userList.size(), "");
        userList.forEach(System.out::println);


        System.out.println("\r\n----- 注解sql打印一个 ------");
        User user = userMapper.queryById(1L);
        System.out.println(user);


        System.out.println("\r\n----- 条件构造器 ------");


    }

    @Test
    void testUpdate() {
        System.out.println(("----- 更新 ------"));
        //UPDATE user SET age=?, email=? WHERE (name = ?)

        //方式一：
        //创建User对象
        User user = new User();
        user.setAge(333);
        user.setEmail("张三创建User对象修改@qq.com");

        userMapper.update(user, new UpdateWrapper<User>().eq("name", "张三"));

        testSelectAll();

        //方式二：
        //不创建User对象
        userMapper.update(null, new UpdateWrapper<User>()
                .set("age", 3333).set("email", "不创建User对象@qq.com").eq("name", "张三"));

        testSelectAll();

        //方式三：
        //lambda条件构造器 创建User对象
        User user1 = new User();
        user1.setAge(33333);
        user1.setEmail("lambda条件构造器 创建User对象@qq.com");

        userMapper.update(user1, new LambdaUpdateWrapper<User>().eq(User::getName, "张三"));

        testSelectAll();

        //方式四：
        //lambda条件构造器 不创建User对象
        userMapper.update(null, new LambdaUpdateWrapper<User>()
                .set(User::getAge, 333333).set(User::getEmail, "lambda条件构造器 不创建User对象@qq.com").eq(User::getName, "张三"));

        testSelectAll();


    }


}
