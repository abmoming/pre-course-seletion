package pers.justin.preselectioncourses.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pers.justin.preselectioncourses.utils.RespBean;

/**
 * @author Justin on 2022-02-13 10:33
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局处理自定义异常
     * @param e 异常
     */
    @ExceptionHandler(CustomException.class)
    public RespBean exception(Exception e) {
        if (e instanceof CustomException) {
            return RespBean.fail(e.getMessage());
        } else {
            return RespBean.fail("其他异常");
        }
    }
}
