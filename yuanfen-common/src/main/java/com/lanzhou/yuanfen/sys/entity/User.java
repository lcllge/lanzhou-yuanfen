package com.lanzhou.yuanfen.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.lanzhou.yuanfen.config.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lcllge
 * @since 2019-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_key", type = IdType.AUTO)
    private Long userKey;

    private String avatar;

    private String code;

    private String smsCode;

    private String emlCode;

    private String email;

    private String mobile;

    private String username;

    private String password;

    private LocalDateTime lastLoginTime;

}
