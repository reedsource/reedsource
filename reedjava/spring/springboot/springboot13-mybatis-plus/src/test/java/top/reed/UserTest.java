package top.reed;

import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.reed.Mapper.UserMapper;
import top.reed.model.User;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class UserTest {

    @Resource
    private UserMapper userMapper;

    @Test
    void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.isTrue(5 == userList.size(), "");
        userList.forEach(System.out::println);
    }



}
