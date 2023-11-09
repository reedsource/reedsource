package top.reed.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.reed.model.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
