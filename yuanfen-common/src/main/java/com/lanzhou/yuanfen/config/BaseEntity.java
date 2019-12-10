package com.lanzhou.yuanfen.config;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * BaseEntity
 * @author Administrator
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    protected Long createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    protected LocalDateTime createTime;

    /**
     *  最后修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    protected Long updateBy;

    /**
     * 最后修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    protected LocalDateTime updateTime;

}
