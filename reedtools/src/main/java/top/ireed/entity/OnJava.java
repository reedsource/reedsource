package top.ireed.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 简述:通用案例OnJava实体
 *
 * @author reedsource
 * date 2024/2/4 9:10
 * reedsource@189.cn
 */

public class OnJava {

    private Long id;//id
    private String name;//名称
    private String cName;//中文名称
    private int num;//总分
    private boolean state;//状态
    private Objects object;//数据
    private List<Info> list;//信息list
    private Map<String, Info> map;//信息map
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private Date date;//时间

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
                ", object=" + object +
                ", list=" + list +
                ", map=" + map +
                ", date=" + date +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Objects getObject() {
        return object;
    }

    public void setObject(Objects object) {
        this.object = object;
    }

    public List<Info> getList() {
        return list;
    }

    public void setList(List<Info> list) {
        this.list = list;
    }

    public Map<String, Info> getMap() {
        return map;
    }

    public void setMap(Map<String, Info> map) {
        this.map = map;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * OnJava信息类
     */
    public static class Info {
        private String infoId;//信息类id
        private String infoName;//信息类名称

        public String getInfoId() {
            return infoId;
        }

        public void setInfoId(String infoId) {
            this.infoId = infoId;
        }

        public String getInfoName() {
            return infoName;
        }

        public void setInfoName(String infoName) {
            this.infoName = infoName;
        }
    }
}
