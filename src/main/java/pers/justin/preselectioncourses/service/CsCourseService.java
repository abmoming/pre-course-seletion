package pers.justin.preselectioncourses.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.justin.preselectioncourses.entity.CsCourse;
import pers.justin.preselectioncourses.mapper.CsCourseMapper;

import java.util.Date;
import java.util.List;

/**
 * @author Justin on 2022-02-13 16:12
 */
@Service
public class CsCourseService {

    @Autowired
    CsCourseMapper csCourseMapper;

    public Boolean addCourse(CsCourse csCourse) {
        csCourse.setCreateTime(new Date());
        csCourse.setIsDelete(false);
        return csCourseMapper.insert(csCourse) == 1;
    }

    public PageInfo<CsCourse> queryByPaged(Integer pageNumber, Integer pageSize) {
        // 使用PageHelper分页插件
        if(ObjectUtils.isNotEmpty(pageNumber) || ObjectUtils.isNotEmpty(pageSize)){
            PageHelper.startPage(pageNumber, pageSize);
        }
        List<CsCourse> csCourses = csCourseMapper.queryByPaged();
        return PageInfo.of(csCourses);
    }
}
