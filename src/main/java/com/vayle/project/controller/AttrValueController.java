package com.vayle.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vayle.project.common.BaseResponse;
import com.vayle.project.common.ErrorCode;
import com.vayle.project.common.ResultUtils;
import com.vayle.project.exception.BusinessException;
import com.vayle.project.model.dto.attr.AttrValueRequest;
import com.vayle.project.model.entity.Attrvalue;
import com.vayle.project.service.AttrvalueService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 特征接口
 *
 * @author admin
 * @date 2023/06/27
 */
@RestController
@RequestMapping("/attrValue")
public class AttrValueController {

    @Resource
    private AttrvalueService attrvalueService;

    /**
     * 根据 id 获取特征
     *
     * @param id
     * @return {@link BaseResponse}<{@link Attrvalue}>
     */
    @GetMapping("/get")
    public BaseResponse<Attrvalue> getAttrValueById(int id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Attrvalue attrvalue = attrvalueService.getById(id);
        return ResultUtils.success(attrvalue);
    }

    /**
     * 分页获取列表
     *
     * @param attrvalueRequest
     * @return {@link BaseResponse}<{@link Page}<{@link Attrvalue}>>
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<Attrvalue>> listAttrValueByPage(AttrValueRequest attrvalueRequest) {
        long current = 1;
        long size = 10;
        Attrvalue attrValueQuery = new Attrvalue();
        if (attrvalueRequest != null) {
            BeanUtils.copyProperties(attrvalueRequest, attrValueQuery);
            current = attrvalueRequest.getCurrent();
            size = attrvalueRequest.getPageSize();
        }
        QueryWrapper<Attrvalue> queryWrapper = new QueryWrapper<>(attrValueQuery);
        Page<Attrvalue> attrvaluePage = attrvalueService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(attrvaluePage);
    }

    /**
     * 创建特征
     *
     * @param attrValueAddRequest
     * @return {@link BaseResponse}<{@link Integer}>
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addAttrValue(@RequestBody AttrValueRequest attrValueAddRequest) {
        if (attrValueAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Attrvalue attrvalue = new Attrvalue();
        BeanUtils.copyProperties(attrValueAddRequest,attrvalue);
        boolean result = attrvalueService.save(attrvalue);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(attrvalue.getId());
    }

    /**
     * 删除特征
     *
     * @param attrValueDeleteRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteAttrValue(@RequestBody AttrValueRequest attrValueDeleteRequest) {
        if (attrValueDeleteRequest == null || attrValueDeleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = attrvalueService.removeById(attrValueDeleteRequest.getId());
        return ResultUtils.success(b);
    }

    /**
     * 更新特征
     *
     * @param attrValueUpdateRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateAttrValue(@RequestBody AttrValueRequest attrValueUpdateRequest) {
        if (attrValueUpdateRequest == null || attrValueUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Attrvalue attrvalue = new Attrvalue();
        BeanUtils.copyProperties(attrValueUpdateRequest, attrvalue);
        boolean result = attrvalueService.updateById(attrvalue);
        return ResultUtils.success(result);
    }

}
