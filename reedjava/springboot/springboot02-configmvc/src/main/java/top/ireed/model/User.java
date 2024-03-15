/*
 * FileName: User
 * Author:   reedsource
 */
package top.ireed.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能简述:
 * 〈实体〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/27 17:22
 * reedsource@189.cn
 * <p>
 * 将ireed配置文件映射为user对象
 */
@ConfigurationProperties(prefix = "ireed")
@Component("user")
@Data
public class User {
	private String name;
	private String add;
}
