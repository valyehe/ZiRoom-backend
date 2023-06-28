package com.vayle.project.model.dto.tenant;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.vayle.project.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户注册请求体
 *
 * @author vayle
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantRequest extends PageRequest implements Serializable {

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


    private String checkPassword;

    /**
     * 查询条件
     * */
    private String description;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
