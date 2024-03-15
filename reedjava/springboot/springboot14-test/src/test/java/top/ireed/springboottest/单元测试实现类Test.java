package top.ireed.springboottest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.mockito.Mockito.*;

//特殊的工具类想要mock需要注入
@PrepareForTest(静态工具类.class)
public class 单元测试实现类Test {
    @Mock
    单元测试接口2 d2;
    @InjectMocks
    top.ireed.springboottest.单元测试实现类 单元测试实现类;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test有返回值接口() throws Exception {
        //多次调用且要求返回值不同的情况,可以写入不同的入参,会自动匹配
        when(d2.我是接口2有返回值接口(anyString())).thenReturn("我是接口2有返回值接口Response");

        String result = 单元测试实现类.有返回值接口("msg");
        Assert.assertEquals("我是有返回值接口实现类msg", result);
    }

    @Test
    public void test无返回值接口() throws Exception {
        单元测试实现类.无返回值接口("msg");
    }

    @Test
    public void test无返回值接口测试有返回值工具类() throws Exception {
        单元测试实现类.无返回值接口测试有返回值工具类("msg");
    }

    @Test
    public void test无返回值接口测试无返回值工具类() throws Exception {

        //注意, 部分情况下对于无返回值的静态工具,可以直接mock通过
        mock(静态工具类.class);
        单元测试实现类.无返回值接口测试无返回值工具类("msg");
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: https://weirddev.com/forum#!/testme