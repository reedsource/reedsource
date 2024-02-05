package main;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.ireed.deal.DealDate;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 简述:OnJava实体
 *
 * @author reedsource
 * date 2024/2/4 9:10
 * reedsource@189.cn
 */
@Data
@EqualsAndHashCode
public class OnJava {

    private Long id;//id
    private String name;//名称
    private String cName;//中文名称
    private int num;//总分
    private boolean state;//状态
    private List<Info> infoList;//信息list
    private Map<String, Info> infoMap;//信息map
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date date;//时间

    /**
     * OnJava信息类
     */
    @Data
    public static class Info {
        private String infoId;//信息类id
        private String infoName;//信息类名称
    }

    public OnJava() {
    }

    public OnJava(Long id, String name, String cName, int num, boolean state, Date date) {
        this.id = id;
        this.name = name;
        this.cName = cName;
        this.num = num;
        this.state = state;
        this.date = date;
    }

    @Override
    public String toString() {
        return "OnJava{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cName='" + cName + '\'' +
                ", num=" + num +
                ", state=" + state +
                ", infoList=" + infoList +
                ", infoMap=" + infoMap +
                ", date=" + DealDate.formatDateString(date) +
                '}';
    }
}
