package com.vayle.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName consulthouse
 */
@TableName(value ="consulthouse")
@Data
public class Consulthouse implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 房屋Id
     */
    private Integer roomId;

    /**
     * 中介ID
     */
    private Integer uid;

    /**
     * 问题
     */
    private String problem;

    /**
     * 咨询电话
     */
    private String cPhone;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}