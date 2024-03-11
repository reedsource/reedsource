package org.top.reed.document;


import lombok.Data;

/**
 * es文档
 */
@Data
public class EsDocument {
    private String id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 城市
     */
    private String city;

}
