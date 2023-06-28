package com.vayle.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName attribute
 */
@TableName(value ="attribute")
@Data
public class Attribute implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer attrId;

    /**
     * 
     */
    private String attrName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}