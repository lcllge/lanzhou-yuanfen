package com.lanzhou.yuanfen.sys.mapper;

import com.lanzhou.yuanfen.sys.entity.Role;
import com.lanzhou.yuanfen.sys.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lcllge
 * @since 2019-12-02
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 获取用户角色关联
     *
     * @param userKey
     * @return
     */
    @Select("SELECT role_key FROM user_role WHERE user_key = #{userKey}")
    List<String> getRoleByUserKey(Long userKey);
}
