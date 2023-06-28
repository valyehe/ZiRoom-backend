package com.vayle.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName roomtitle
 */
@TableName(value ="roomtitle")
@Data
public class Roomtitle implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 房间ID
     */
    private Integer roomId;

    /**
     * 房间|房屋标签名称
     */
    private String titleName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}