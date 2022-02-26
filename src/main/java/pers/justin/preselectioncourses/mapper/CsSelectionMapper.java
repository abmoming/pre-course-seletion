package pers.justin.preselectioncourses.mapper;

import org.apache.ibatis.annotations.Param;
import pers.justin.preselectioncourses.entity.CsCourse;
import pers.justin.preselectioncourses.entity.CsSelection;

public interface CsSelectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSelection record);

    int insertSelective(CsSelection record);

    CsSelection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSelection record);

    int updateByPrimaryKey(CsSelection record);

    int deleteByUIdAndCId(CsSelection csSelection);

    String getUsernameByCourse(CsCourse csCourse);

    Integer selectCourseCount(int id);
}