package pers.justin.preselectioncourses.controller;

import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.justin.preselectioncourses.entity.CsUser;
import pers.justin.preselectioncourses.service.CsUserService;
import pers.justin.preselectioncourses.utils.Md5Util;
import pers.justin.preselectioncourses.utils.RespBean;

import java.util.Date;

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

    @GetMapping("/query")
    public RespBean queryUser(String roleName, String username) {
        return RespBean.build().setData(userService.queryUser(roleName, username));
    }

    @GetMapping("/query2")
    public RespBean queryUser2(String roleName, String username, String couName) {
        return RespBean.build().setData(userService.queryUser2(roleName, username, couName));
    }

    @PostMapping("/")
    public RespBean addUser(@RequestBody CsUser csUser) {
        if(userService.addUser(csUser)){
            return RespBean.success("操作用户成功!");
        } else {
            return RespBean.success("操作用户失败!");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deleteUser(@PathVariable("id") Integer userId) {
        if(userService.deleteUser(userId)) {
            return RespBean.success("删除用户成功!");
        } else {
            return RespBean.success("删除用户失败!");
        }
    }

}
