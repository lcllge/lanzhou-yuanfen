package com.lanzhou.yuanfen.sys.mapper;

import com.lanzhou.yuanfen.sys.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lcllge
 * @since 2019-12-02
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    /**
     * 根据用户名获取权限列表
     *
     * @param userKey
     * @return
     */
    @Select("SELECT e.code FROM permission e RIGHT JOIN (SELECT DISTINCT(c.perm_key) FROM (SELECT a.user_key,b.* FROM user_role a LEFT JOIN role_permission b ON a.role_key = b.role_key) c WHERE c.user_key = #{userKey}) d ON e.perm_key = d.perm_key WHERE e.code IS NOT NULL")
    List<String> getPermByUserKey(Long userKey);

    /**
     * 连表查询email和permKey的Map集合
     * @return
     */
    @Select("SELECT f.`code`,e.email FROM permission f RIGHT JOIN (SELECT d.email,c.perm_key FROM `user` d LEFT JOIN (SELECT a.user_key,b.* FROM user_role a LEFT JOIN role_permission b ON a.role_key = b.role_key) c ON c.user_key = d.user_key) e ON e.perm_key = f.perm_key")
    List<Map<String, String>> getEmailPermMap();

}
