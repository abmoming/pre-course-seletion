package pers.justin.preselectioncourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.justin.preselectioncourses.entity.CsCourse;
import pers.justin.preselectioncourses.entity.CsSelection;
import pers.justin.preselectioncourses.mapper.CsSelectionMapper;

import java.util.Date;

/**
 * @author Justin on 2022-02-16 09:38
 */
@Service
public class CsSelectionService {

    @Autowired
    CsSelectionMapper csSelectionMapper;

    /**
     * 给课程绑定任课教师
     * @param csSelection 关联数据
     */
    public void bindUser(CsSelection csSelection) {
        csSelectionMapper.insert(csSelection);
    }

    /**
     * 通过userId和courseId删除用户与课程的绑定关系
     * @param csSelection 关联数据
     */
    public void unbindUser(CsSelection csSelection) {
        csSelectionMapper.deleteByUIdAndCId(csSelection);
    }

    public String getUsernameByCourse(CsCourse csCourse) {
        return csSelectionMapper.getUsernameByCourse(csCourse);
    }

    public Boolean addSelectCourse(CsSelection csSelection) {
        return csSelectionMapper.insert(csSelection) == 1;
    }

    public Boolean cancelSelectCourse(CsSelection csSelection) {
        return csSelectionMapper.deleteByUIdAndCId(csSelection) == 1;
    }

    public Integer selectCourseCount(int id) {
        return csSelectionMapper.selectCourseCount(id);
    }
}
