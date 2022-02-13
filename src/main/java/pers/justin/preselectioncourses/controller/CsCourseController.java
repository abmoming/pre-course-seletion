package pers.justin.preselectioncourses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.justin.preselectioncourses.entity.CsCourse;
import pers.justin.preselectioncourses.service.CsCourseService;
import pers.justin.preselectioncourses.utils.RespBean;

/**
 * @author Justin on 2022-02-13 16:10
 */
@RestController
@RequestMapping("/course")
public class CsCourseController {

    @Autowired
    CsCourseService csCourseService;

    @GetMapping
    public RespBean queryByPaged(Integer pageNumber, Integer pageSize) {
        return RespBean.build().setData(csCourseService.queryByPaged(pageNumber, pageSize));
    }

    @PostMapping
    public RespBean addCourse(@RequestBody CsCourse csCourse) {
        if (csCourseService.addCourse(csCourse)) {
            return RespBean.success("添加课程成功!");
        } else {
            return RespBean.fail("添加课程失败!");
        }
    }
}
