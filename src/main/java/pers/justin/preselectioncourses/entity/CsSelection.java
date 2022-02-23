package pers.justin.preselectioncourses.entity;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CsSelection {
    private Integer id;

    private Integer userId;

    private Integer couId;

    private Date createTime;
}