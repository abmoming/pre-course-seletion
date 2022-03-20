package pers.justin.preselectioncourses.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CsUser extends BaseEntity {
    private Integer id;

    private String username;

    private String userCode;

    private String classes;

    private String password;

    private String roleName;
}