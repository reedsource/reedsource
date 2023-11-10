package top.reed.model;


import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    /**
     * 字段判断规则, 常用于更新时忽略某个字段更新 如创建时间
     */
    @TableField(value = "types", updateStrategy = FieldStrategy.NEVER)
    private Long types;
}
