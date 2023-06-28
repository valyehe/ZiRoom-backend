package com.vayle.project.model.dto.users;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.vayle.project.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;


/**
 * 用户查询请求
 *
 * @author vayle
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UsersRequest extends PageRequest implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer uid;

    /**
     * 名称
     */
    private String uname;

    /**
     * 头像地址
     */
    private String upicture;


    /**
     * 查询条件
     * */
    private String description;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}