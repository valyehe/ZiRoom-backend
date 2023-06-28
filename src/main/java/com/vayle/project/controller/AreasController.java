package com.vayle.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vayle.project.common.BaseResponse;
import com.vayle.project.common.ErrorCode;
import com.vayle.project.common.ResultUtils;
import com.vayle.project.exception.BusinessException;
import com.vayle.project.model.dto.attr.AreasRequest;
import com.vayle.project.model.entity.Areas;
import com.vayle.project.service.AreasService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 地区接口
 *
 * @author admin
 * @date 2023/06/27
 */
@RestController
@RequestMapping("/areas")
public class AreasController {

    @Resource
    private AreasService areasService;

    /**
     * 根据 id 获取
     *
     * @param id
     * @return {@link BaseResponse}<{@link Areas}>
     */
    @GetMapping("/get")
    public BaseResponse<Areas> getAreasById(int id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Areas areas = areasService.getById(id);
        return ResultUtils.success(areas);
    }


    /**
     * 分页获取列表
     *
     * @param areasRequest
     * @return {@link BaseResponse}<{@link Page}<{@link Areas}>>
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<Areas>> listAreasByPage(AreasRequest areasRequest) {
        long current = 1;
        long size = 10;
        Areas areasQuery = new Areas();
        if (areasRequest != null) {
            BeanUtils.copyProperties(areasRequest, areasQuery);
            current = areasRequest.getCurrent();
            size = areasRequest.getPageSize();
        }
        QueryWrapper<Areas> queryWrapper = new QueryWrapper<>(areasQuery);
        Page<Areas> areasPage = areasService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(areasPage);
    }

    /**
     * 创建地区
     *
     * @param areasAddRequest
     * @return {@link BaseResponse}<{@link Integer}>
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addAreas(@RequestBody AreasRequest areasAddRequest) {
        if (areasAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Areas areas = new Areas();
        BeanUtils.copyProperties(areasAddRequest,areas);
        boolean result = areasService.save(areas);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(areas.getId());
    }

    /**
     * 删除地区
     *
     * @param areasDeleteRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteAreas(@RequestBody AreasRequest areasDeleteRequest) {
        if (areasDeleteRequest == null || areasDeleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = areasService.removeById(areasDeleteRequest.getId());
        return ResultUtils.success(b);
    }

    /**
     * 更新地区
     *
     * @param areasUpdateRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateAreas(@RequestBody AreasRequest areasUpdateRequest) {
        if (areasUpdateRequest == null || areasUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Areas areas = new Areas();
        BeanUtils.copyProperties(areasUpdateRequest, areas);
        boolean result = areasService.updateById(areas);
        return ResultUtils.success(result);
    }

}
