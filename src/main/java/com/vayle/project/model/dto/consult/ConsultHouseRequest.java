package com.vayle.project.model.dto.consult;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.vayle.project.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 请求
 *
 * @author vayle
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConsultHouseRequest extends PageRequest implements Serializable {
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