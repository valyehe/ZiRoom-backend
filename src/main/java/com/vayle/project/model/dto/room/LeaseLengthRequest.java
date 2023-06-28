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
public class LeaseLengthRequest extends PageRequest implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    private String leaseTime;

    /**
     *
     */
    private Integer status;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}