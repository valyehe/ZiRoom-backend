package com.vayle.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vayle.project.common.BaseResponse;
import com.vayle.project.common.ErrorCode;
import com.vayle.project.common.ResultUtils;
import com.vayle.project.exception.BusinessException;
import com.vayle.project.model.dto.consult.ConsultHouseRequest;
import com.vayle.project.model.entity.Consulthouse;
import com.vayle.project.service.ConsulthouseService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 咨询接口
 *
 * @author admin
 * @date 2023/06/27
 */
@RestController
@RequestMapping("/consultHouse")
public class ConsultHouseController {

    @Resource
    private ConsulthouseService consulthouseService;

    /**
     * 根据 id 获取咨询
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<Consulthouse> getUserById(int id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Consulthouse consulthouse = consulthouseService.getById(id);
        return ResultUtils.success(consulthouse);
    }


    /**
     * 分页获取列表
     *
     * @return {@link BaseResponse}<{@link Page}<{@link Consulthouse}>>
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<Consulthouse>> listUserByPage(ConsultHouseRequest consulthouseRequest) {
        long current = 1;
        long size = 10;
        Consulthouse consultHouseQuery = new Consulthouse();
        if (consulthouseRequest != null) {
            BeanUtils.copyProperties(consulthouseRequest, consultHouseQuery);
            current = consulthouseRequest.getCurrent();
            size = consulthouseRequest.getPageSize();
        }
        QueryWrapper<Consulthouse> queryWrapper = new QueryWrapper<>(consultHouseQuery);
        Page<Consulthouse> consulthousePage = consulthouseService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(consulthousePage);
    }

    /**
     * 创建咨询
     *
     * @param consultHouseAddRequest
     * @return {@link BaseResponse}<{@link Integer}>
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addUser(@RequestBody ConsultHouseRequest consultHouseAddRequest) {
        if (consultHouseAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Consulthouse consulthouse = new Consulthouse();
        BeanUtils.copyProperties(consultHouseAddRequest,consulthouse);
        boolean result = consulthouseService.save(consulthouse);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(consulthouse.getId());
    }

    /**
     * 删除咨询
     *
     * @param consultHouseDeleteRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteTenant(@RequestBody ConsultHouseRequest consultHouseDeleteRequest) {
        if (consultHouseDeleteRequest == null || consultHouseDeleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = consulthouseService.removeById(consultHouseDeleteRequest.getId());
        return ResultUtils.success(b);
    }

    /**
     * 更新咨询
     *
     * @param consultHouseUpdateRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateTenant(@RequestBody ConsultHouseRequest consultHouseUpdateRequest) {
        if (consultHouseUpdateRequest == null || consultHouseUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Consulthouse consulthouse = new Consulthouse();
        BeanUtils.copyProperties(consultHouseUpdateRequest, consulthouse);
        boolean result = consulthouseService.updateById(consulthouse);
        return ResultUtils.success(result);
    }

}
