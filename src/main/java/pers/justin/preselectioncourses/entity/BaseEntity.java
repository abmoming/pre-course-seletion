package pers.justin.preselectioncourses.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Justin on 2022-02-12 14:38
 */
@Setter
@Getter
public class BaseEntity {

    private String creator;
    private Boolean isDelete;
    private Date createTime;
    private Date deleteTime;
}
