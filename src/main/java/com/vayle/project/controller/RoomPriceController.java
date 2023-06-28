package com.vayle.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vayle.project.common.BaseResponse;
import com.vayle.project.common.ErrorCode;
import com.vayle.project.common.ResultUtils;
import com.vayle.project.exception.BusinessException;
import com.vayle.project.model.dto.room.RoomPriceRequest;
import com.vayle.project.model.entity.Roomprice;
import com.vayle.project.service.RoompriceService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 房价接口
 *
 * @author admin
 * @date 2023/06/27
 */
@RestController
@RequestMapping("/roomPrice")
public class RoomPriceController {

    @Resource
    private RoompriceService roompriceService;

    /**
     * 根据 id 获取房价
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<Roomprice> getRoomPriceById(int id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Roomprice roomprice = roompriceService.getById(id);
        return ResultUtils.success(roomprice);
    }


    /**
     * 分页获取列表
     *
     * @return {@link BaseResponse}<{@link Page}<{@link Roomprice}>>
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<Roomprice>> listUserByPage(RoomPriceRequest roompriceRequest) {
        long current = 1;
        long size = 10;
        Roomprice roompriceQuery = new Roomprice();
        if (roompriceRequest != null) {
            BeanUtils.copyProperties(roompriceRequest, roompriceQuery);
            current = roompriceRequest.getCurrent();
            size = roompriceRequest.getPageSize();
        }
        QueryWrapper<Roomprice> queryWrapper = new QueryWrapper<>(roompriceQuery);
        Page<Roomprice> roompricePage = roompriceService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(roompricePage);
    }

    /**
     * 创建房价
     *
     * @param roomPriceAddRequest
     * @return {@link BaseResponse}<{@link Integer}>
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addRoomPrice(@RequestBody RoomPriceRequest roomPriceAddRequest) {
        if (roomPriceAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Roomprice roomprice = new Roomprice();
        BeanUtils.copyProperties(roomPriceAddRequest,roomprice);
        boolean result = roompriceService.save(roomprice);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(roomprice.getId());
    }

    /**
     * 删除房价
     *
     * @param roomPriceDeleteRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteTenant(@RequestBody RoomPriceRequest roomPriceDeleteRequest) {
        if (roomPriceDeleteRequest == null || roomPriceDeleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = roompriceService.removeById(roomPriceDeleteRequest.getId());
        return ResultUtils.success(b);
    }

    /**
     * 更新房价
     *
     * @param roomPriceUpdateRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateTenant(@RequestBody RoomPriceRequest roomPriceUpdateRequest) {
        if (roomPriceUpdateRequest == null || roomPriceUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Roomprice roomprice = new Roomprice();
        BeanUtils.copyProperties(roomPriceUpdateRequest, roomprice);
        boolean result = roompriceService.updateById(roomprice);
        return ResultUtils.success(result);
    }

}
