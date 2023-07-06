package com.vayle.project.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.vayle.project.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@EqualsAndHashCode(callSuper = true)
@Data
public class AttributeVo extends PageRequest implements Serializable {

    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer attrId;


    private String attrName;


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
