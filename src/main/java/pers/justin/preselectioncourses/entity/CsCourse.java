package pers.justin.preselectioncourses.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class CsCourse extends BaseEntity {
    private Integer id;

    private String name;

    private String classAddress;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date classStartTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date classEndTime;

    private Integer numPeople;

    private Boolean status;

    private Integer userId;

    // 显示课程状态中文
    public String getStatusCn() {
        return this.status ? "已结课" : "未结课";
    }
}