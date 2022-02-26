package pers.justin.preselectioncourses.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.justin.preselectioncourses.entity.CsCourse;
import pers.justin.preselectioncourses.entity.CsSelection;
import pers.justin.preselectioncourses.service.CsCourseService;
import pers.justin.preselectioncourses.service.CsSelectionService;
import pers.justin.preselectioncourses.utils.RespBean;

/**
 * @author Justin on 2022-02-13 16:10
 */
@RestController
@RequestMapping("/course")
public class CsCourseController {

    @Autowired
    CsCourseService csCourseService;
    @Autowired
    CsSelectionService csSelectionService;

    @GetMapping
    public RespBean queryByPaged(Integer pageNumber, Integer pageSize, String userOrCouName) {
        return RespBean.build().setData(csCourseService.queryByPaged(pageNumber, pageSize, userOrCouName));
    }

    @GetMapping("/my_courses")
    public RespBean queryByMine(Integer pageNumber, Integer pageSize, String userId) {
        return RespBean.build().setData(csCourseService.queryByMine(pageNumber, pageSize, userId));
    }

    @PostMapping
    public RespBean addCourse(@RequestBody CsCourse csCourse) throws JsonProcessingException {
        return RespBean.build().setData(csCourseService.addCourse(csCourse));
    }

    @DeleteMapping("/{id}")
    public RespBean deleteCourse(@PathVariable("id") Integer courseId) {
        if (csCourseService.deleteCourse(courseId)) {
            return RespBean.success("删除课程成功!");
        } else {
            return RespBean.fail("删除课程失败!");
        }
    }

    @PostMapping("/confirm_coverage")
    public RespBean confirmCoverage(@RequestBody CsCourse csCourse) throws JsonProcessingException {
        if (csCourseService.confirmCoverage(csCourse)) {
            return RespBean.success("覆盖数据成功!");
        }
        return RespBean.fail("覆盖数据失败!");
    }

    @PostMapping("/select_course")
    public RespBean addSelectCourse(@RequestBody CsSelection csSelection) {
        if (csSelectionService.addSelectCourse(csSelection)) {
            return RespBean.success("选课成功!");
        } else {
            return RespBean.fail("选课失败!");
        }
    }

    @DeleteMapping("/cancel_select_course")
    public RespBean cancelSelectCourse(CsSelection csSelection) {
        if (csSelectionService.cancelSelectCourse(csSelection)) {
            return RespBean.success("取消选课成功!");
        } else {
            return RespBean.fail("取消选课失败!");
        }
    }

}
