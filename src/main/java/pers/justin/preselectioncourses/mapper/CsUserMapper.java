package pers.justin.preselectioncourses.mapper;


import org.apache.ibatis.annotations.Param;
import pers.justin.preselectioncourses.entity.CsUser;

public interface CsUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsUser record);

    int insertSelective(CsUser record);

    CsUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsUser record);

    int updateByPrimaryKey(CsUser record);

    CsUser selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}