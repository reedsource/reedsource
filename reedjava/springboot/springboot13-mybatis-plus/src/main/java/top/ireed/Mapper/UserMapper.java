package top.ireed.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.ireed.model.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {


    @Select("select * from reedsource.user where id = #{id}")
    User queryById(@Param("id") Long id);

}
