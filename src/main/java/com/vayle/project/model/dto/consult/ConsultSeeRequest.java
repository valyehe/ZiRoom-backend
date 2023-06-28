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
public class ConsultSeeRequest extends PageRequest implements Serializable {
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