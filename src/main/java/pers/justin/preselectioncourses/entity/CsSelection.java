package pers.justin.preselectioncourses.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CsSelection {
    private Integer id;

    private Integer stuId;

    private Integer couId;

    private Date createTime;
}