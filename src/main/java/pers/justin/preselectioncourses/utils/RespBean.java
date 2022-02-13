package pers.justin.preselectioncourses.utils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Justin on 2022-02-12 21:03
 */
@Setter
@Getter
public class RespBean {
    private Integer code = 200;
    private String message;
    private Object data;

    private RespBean() {
    }

    private RespBean(String message) {
        this.message = message;
    }

    private RespBean(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private RespBean(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public static RespBean success(String message) {
        return new RespBean(message);
    }

    public static RespBean success(String message, Object data) {
        return new RespBean(message, data);
    }

    public static RespBean fail(String message) {
        return new RespBean(500, message);
    }

    public static RespBean build() {
        return new RespBean();
    }

    public RespBean setMessage(String message) {
        this.message = message;
        return this;
    }

    public RespBean setData(Object data) {
        this.data = data;
        return this;
    }
}
