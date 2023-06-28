package com.vayle.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vayle.project.common.BaseResponse;
import com.vayle.project.common.ErrorCode;
import com.vayle.project.common.ResultUtils;
import com.vayle.project.exception.BusinessException;
import com.vayle.project.model.dto.room.LeaseLengthRequest;
import com.vayle.project.model.entity.Leaselength;
import com.vayle.project.service.LeaselengthService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 最少居住接口
 *
 * @author admin
 * @date 2023/06/27
 */
@RestController
@RequestMapping("/leaseLength")
public class LeaseLengthController {

    @Resource
    private LeaselengthService leaselengthService;

    /**
     * 根据 id 获取最少居住
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<Leaselength> getLeaseLengthById(int id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Leaselength leaselength = leaselengthService.getById(id);
        return ResultUtils.success(leaselength);
    }


    /**
     * 分页获取列表
     *
     * @return {@link BaseResponse}<{@link Page}<{@link Leaselength}>>
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<Leaselength>> listLeaseLengthByPage(LeaseLengthRequest leaselengthRequest) {
        long current = 1;
        long size = 10;
        Leaselength leaselengthQuery = new Leaselength();
        if (leaselengthRequest != null) {
            BeanUtils.copyProperties(leaselengthRequest, leaselengthQuery);
            current = leaselengthRequest.getCurrent();
            size = leaselengthRequest.getPageSize();
        }
        QueryWrapper<Leaselength> queryWrapper = new QueryWrapper<>(leaselengthQuery);
        Page<Leaselength> leaselengthPage = leaselengthService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(leaselengthPage);
    }

    /**
     * 创建最少居住
     *
     * @param leaseLengthAddRequest
     * @return {@link BaseResponse}<{@link Integer}>
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addLeaseLength(@RequestBody LeaseLengthRequest leaseLengthAddRequest) {
        if (leaseLengthAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Leaselength leaselength = new Leaselength();
        BeanUtils.copyProperties(leaseLengthAddRequest,leaselength);
        boolean result = leaselengthService.save(leaselength);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(leaselength.getId());
    }

    /**
     * 删除最少居住
     *
     * @param leaseLengthDeleteRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteLeaseLength(@RequestBody LeaseLengthRequest leaseLengthDeleteRequest) {
        if (leaseLengthDeleteRequest == null || leaseLengthDeleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = leaselengthService.removeById(leaseLengthDeleteRequest.getId());
        return ResultUtils.success(b);
    }

    /**
     * 更新最少居住
     *
     * @param leaseLengthUpdateRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateLeaseLength(@RequestBody LeaseLengthRequest leaseLengthUpdateRequest) {
        if (leaseLengthUpdateRequest == null || leaseLengthUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Leaselength leaselength = new Leaselength();
        BeanUtils.copyProperties(leaseLengthUpdateRequest, leaselength);
        boolean result = leaselengthService.updateById(leaselength);
        return ResultUtils.success(result);
    }

}
