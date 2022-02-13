package pers.justin.preselectioncourses.mapper;

import pers.justin.preselectioncourses.entity.CsSelection;

public interface CsSelectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSelection record);

    int insertSelective(CsSelection record);

    CsSelection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSelection record);

    int updateByPrimaryKey(CsSelection record);
}