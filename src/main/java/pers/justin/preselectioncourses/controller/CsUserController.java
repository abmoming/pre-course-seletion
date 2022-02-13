package pers.justin.preselectioncourses.controller;

import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.justin.preselectioncourses.service.CsUserService;
import pers.justin.preselectioncourses.utils.RespBean;

/**
 * @author Justin on 2022-02-12 23:14
 */
@RestController
@RequestMapping("/user")
public class CsUserController {

    @Autowired
    CsUserService userService;

    @PostMapping("/login")
    public RespBean doLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password){

        return RespBean.success("登录成功", userService.doLogin(username, password));
    }

    // 登出直接在前端解决
}
