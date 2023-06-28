package com.vayle.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName tenant
 */
@TableName(value ="tenant")
@Data
public class Tenant implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer tId;

    /**
     * 昵称
     */
    private String tNickname;

    /**
     * 手机号
     */
    private String tPhone;

    /**
     * 邮箱
     */
    private String tEmail;

    /**
     * 密码
     */
    private String tPassword;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}