package pers.justin.preselectioncourses.mapper;


import org.apache.ibatis.annotations.Param;
import pers.justin.preselectioncourses.entity.CsCourse;

import java.util.List;

public interface CsCourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsCourse record);

    int insertSelective(CsCourse record);

    CsCourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsCourse record);

    int updateByPrimaryKey(CsCourse record);

    List<CsCourse> queryByPaged();
}