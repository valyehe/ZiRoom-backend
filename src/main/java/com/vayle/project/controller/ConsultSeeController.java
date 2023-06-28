package com.vayle.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vayle.project.common.BaseResponse;
import com.vayle.project.common.ErrorCode;
import com.vayle.project.common.ResultUtils;
import com.vayle.project.exception.BusinessException;
import com.vayle.project.model.dto.consult.ConsultSeeRequest;
import com.vayle.project.model.entity.Consultsee;
import com.vayle.project.service.ConsultseeService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 预约接口
 *
 * @author admin
 * @date 2023/06/27
 */
@RestController
@RequestMapping("/consultSee")
public class ConsultSeeController {

    @Resource
    private ConsultseeService consultseeService;

    /**
     * 根据 id 获取预约
     *
     * @param id
     * @return {@link BaseResponse}<{@link Consultsee}>
     */
    @GetMapping("/get")
    public BaseResponse<Consultsee> getConsultSeeById(int id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Consultsee consultsee = consultseeService.getById(id);
        return ResultUtils.success(consultsee);
    }


    /**
     * 分页获取列表
     *
     * @return {@link BaseResponse}<{@link Page}<{@link Consultsee}>>
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<Consultsee>> listConsultSeeByPage(ConsultSeeRequest consultseeRequest) {
        long current = 1;
        long size = 10;
        Consultsee consultSeeQuery = new Consultsee();
        if (consultseeRequest != null) {
            BeanUtils.copyProperties(consultseeRequest, consultSeeQuery);
            current = consultseeRequest.getCurrent();
            size = consultseeRequest.getPageSize();
        }
        QueryWrapper<Consultsee> queryWrapper = new QueryWrapper<>(consultSeeQuery);
        Page<Consultsee> consultseePage = consultseeService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(consultseePage);
    }

    /**
     * 创建预约
     *
     * @param consultSeeAddRequest
     * @return {@link BaseResponse}<{@link Integer}>
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addConsultSee(@RequestBody ConsultSeeRequest consultSeeAddRequest) {
        if (consultSeeAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Consultsee consultsee = new Consultsee();
        BeanUtils.copyProperties(consultSeeAddRequest,consultsee);
        boolean result = consultseeService.save(consultsee);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(consultsee.getId());
    }

    /**
     * 删除预约
     *
     * @param consultSeeDeleteRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteConsultSee(@RequestBody ConsultSeeRequest consultSeeDeleteRequest) {
        if (consultSeeDeleteRequest == null || consultSeeDeleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = consultseeService.removeById(consultSeeDeleteRequest.getId());
        return ResultUtils.success(b);
    }

    /**
     * 更新预约
     *
     * @param consultSeeUpdateRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateConsultSee(@RequestBody ConsultSeeRequest consultSeeUpdateRequest) {
        if (consultSeeUpdateRequest == null || consultSeeUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Consultsee consultsee = new Consultsee();
        BeanUtils.copyProperties(consultSeeUpdateRequest, consultsee);
        boolean result = consultseeService.updateById(consultsee);
        return ResultUtils.success(result);
    }

}
