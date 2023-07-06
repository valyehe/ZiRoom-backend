package com.vayle.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vayle.project.model.entity.Attribute;
import com.vayle.project.model.vo.AttributeVo;

import java.util.List;
import java.util.Map;


/**
* @author admin
* @description 针对表【attribute】的数据库操作Service
* @createDate 2023-06-28 10:26:33
*/
public interface AttributeService extends IService<Attribute> {

    List<AttributeVo> selectValuesByAttrId();

}
