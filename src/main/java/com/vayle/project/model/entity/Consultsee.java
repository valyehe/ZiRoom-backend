package com.vayle.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName consultsee
 */
@TableName(value ="consultsee")
@Data
public class Consultsee implements Serializable {
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
     * 约看时间
     */
    private String seeTime;

    /**
     * 约看手机号
     */
    private String seePhone;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}