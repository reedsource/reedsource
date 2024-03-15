package top.reed.model;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("types")
public class Types {
    private Long id;
    private String name;
    private Integer level;
    private String tid;
}
