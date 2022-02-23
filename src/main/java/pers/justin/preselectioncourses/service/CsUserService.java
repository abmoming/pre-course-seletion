package pers.justin.preselectioncourses.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.justin.preselectioncourses.entity.CsUser;
import pers.justin.preselectioncourses.exception.CustomException;
import pers.justin.preselectioncourses.mapper.CsUserMapper;
import pers.justin.preselectioncourses.utils.Md5Util;

import java.util.Date;
import java.util.List;

/**
 * @author Justin on 2022-02-12 23:26
 */
@Service
public class CsUserService {

    @Autowired
    CsUserMapper csUserMapper;

    public CsUser doLogin(String username, String password) {

        password = Md5Util.EncoderByMd5(password);
        CsUser csUser = csUserMapper.selectByUsernameAndPassword(username, password);

        if (ObjectUtils.isEmpty(csUser)) {
            throw new CustomException("用户不存在!");
        }
        // 清空密码
        csUser.setPassword(null);

        return csUser;
    }

    public List<CsUser> queryUser(String roleName, String username) {

        return csUserMapper.queryUser(roleName, username);
    }

    public boolean addUser(CsUser csUser) {

        // 默认添加的用户密码为123
        int count = 0;
        if(ObjectUtils.isEmpty(csUser.getId())){
            csUser.setPassword(Md5Util.EncoderByMd5("123"));
            csUser.setCreateTime(new Date());
            csUser.setIsDelete(false);
            count = csUserMapper.insert(csUser);
        } else {
            count = csUserMapper.updateByPrimaryKeySelective(csUser);
        }

        return count == 1;
    }

    public boolean deleteUser(Integer userId) {
        return csUserMapper.deleteByPrimaryKey(userId) == 1;
    }
}
