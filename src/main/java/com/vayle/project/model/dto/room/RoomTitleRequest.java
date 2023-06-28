package com.vayle.project.model.dto.room;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.vayle.project.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 房间请求
 *
 * @author vayle
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoomTitleRequest extends PageRequest implements Serializable {
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