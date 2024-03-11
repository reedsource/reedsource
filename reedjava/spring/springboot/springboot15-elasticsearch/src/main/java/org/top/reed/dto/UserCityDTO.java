package org.top.reed.dto;

import lombok.Data;

/**
 * 功能简述:〈数据汇总实体〉
 *
 * @author reedsource
 * date 2024/3/8 16:58
 * reedsource@189.cn
 */
@Data
public class UserCityDTO {
    /**
     * 城市
     */
    private String city;
    /**
     * 用户数
     */
    private Long count;
    /**
     * 平均年龄
     */
    private Double avgAge;

}
