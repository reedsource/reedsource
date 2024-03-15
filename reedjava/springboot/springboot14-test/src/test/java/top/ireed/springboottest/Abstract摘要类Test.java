package top.ireed.springboottest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;

public class Abstract摘要类Test {

    //这里必须引用实现类
    @InjectMocks
    Abstract摘要类 abstract摘要类 = PowerMockito.spy(new Abstract摘要类实现类());

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void t1() throws Exception {
        abstract摘要类.摘要类2();
    }

}