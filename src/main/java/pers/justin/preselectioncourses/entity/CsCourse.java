package pers.justin.preselectioncourses.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CsCourse extends BaseEntity {
    private Integer id;

    private String name;

    private String classAddress;

    private Date classTime;

    private Integer numPeople;

    private Boolean status;

    // 显示课程状态中文
    public String getStatusCn() {
        return this.status ? "已结课" : "未结课";
    }
}