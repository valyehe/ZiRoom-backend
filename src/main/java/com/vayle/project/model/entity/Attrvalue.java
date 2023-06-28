package com.vayle.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName attrvalue
 */
@TableName(value ="attrvalue")
@Data
public class Attrvalue implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer attrId;

    /**
     * 
     */
    private String valueName;

    /**
     * 
     */
    private Integer valueId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}