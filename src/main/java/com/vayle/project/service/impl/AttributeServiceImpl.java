package com.vayle.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vayle.project.mapper.AttributeMapper;
import com.vayle.project.model.entity.Attribute;
import com.vayle.project.model.vo.AttributeVo;
import com.vayle.project.service.AttributeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author admin
* @description 针对表【attribute】的数据库操作Service实现
* @createDate 2023-06-28 10:26:33
*/
@Service
public class AttributeServiceImpl extends ServiceImpl<AttributeMapper, Attribute>
    implements AttributeService {

    @Resource
    AttributeMapper attributeMapper;

    @Override
    public List<AttributeVo> selectValuesByAttrId() {
        return new ArrayList<>(attributeMapper.selectValuesByAttrId());
    }
}




