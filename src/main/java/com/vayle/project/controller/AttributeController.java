package com.vayle.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vayle.project.common.BaseResponse;
import com.vayle.project.common.ErrorCode;
import com.vayle.project.common.ResultUtils;
import com.vayle.project.exception.BusinessException;
import com.vayle.project.model.dto.attr.AttributeRequest;
import com.vayle.project.model.entity.Attribute;
import com.vayle.project.service.AttributeService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 分类接口
 *
 * @author admin
 * @date 2023/06/27
 */
@RestController
@RequestMapping("/attribute")
public class AttributeController {

    @Resource
    private AttributeService attributeService;

    /**
     * 根据 id 获取分类
     *
     * @param attrId
     * @return {@link BaseResponse}<{@link Attribute}>
     */
    @GetMapping("/get")
    public BaseResponse<Attribute> getAttributeById(int attrId) {
        if (attrId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Attribute attribute = attributeService.getById(attrId);
        return ResultUtils.success(attribute);
    }


    /**
     * 分页获取列表
     *
     * @return {@link BaseResponse}<{@link Page}<{@link Attribute}>>
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<Attribute>> listAttributeByPage(AttributeRequest attributeRequest) {
        long current = 1;
        long size = 10;
        Attribute attributeQuery = new Attribute();
        if (attributeRequest != null) {
            BeanUtils.copyProperties(attributeRequest, attributeQuery);
            current = attributeRequest.getCurrent();
            size = attributeRequest.getPageSize();
        }
        QueryWrapper<Attribute> queryWrapper = new QueryWrapper<>(attributeQuery);
        Page<Attribute> attributePage = attributeService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(attributePage);
    }

    /**
     * 创建分类
     *
     * @param attributeAddRequest
     * @return {@link BaseResponse}<{@link Integer}>
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addAttribute(@RequestBody AttributeRequest attributeAddRequest) {
        if (attributeAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Attribute attribute = new Attribute();
        BeanUtils.copyProperties(attributeAddRequest,attribute);
        boolean result = attributeService.save(attribute);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(attribute.getAttrId());
    }

    /**
     * 删除分类
     *
     * @param attributeDeleteRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteAttribute(@RequestBody AttributeRequest attributeDeleteRequest) {
        if (attributeDeleteRequest == null || attributeDeleteRequest.getAttrId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = attributeService.removeById(attributeDeleteRequest.getAttrId());
        return ResultUtils.success(b);
    }

    /**
     * 更新分类
     *
     * @param attributeUpdateRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateAttribute(@RequestBody AttributeRequest attributeUpdateRequest) {
        if (attributeUpdateRequest == null || attributeUpdateRequest.getAttrId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Attribute attribute = new Attribute();
        BeanUtils.copyProperties(attributeUpdateRequest, attribute);
        boolean result = attributeService.updateById(attribute);
        return ResultUtils.success(result);
    }

}
