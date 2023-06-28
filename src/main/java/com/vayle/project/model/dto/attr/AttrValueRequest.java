package com.vayle.project.model.dto.attr;

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
public class AttrValueRequest extends PageRequest implements Serializable {
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