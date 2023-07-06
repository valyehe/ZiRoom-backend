package com.vayle.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vayle.project.common.BaseResponse;
import com.vayle.project.common.ErrorCode;
import com.vayle.project.common.ResultUtils;
import com.vayle.project.exception.BusinessException;
import com.vayle.project.model.dto.room.RoomsRequest;
import com.vayle.project.model.entity.Rooms;
import com.vayle.project.model.vo.AttributeVo;
import com.vayle.project.model.vo.RoomsVo;
import com.vayle.project.service.RoomsService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


/**
 * 房子接口
 *
 * @author admin
 * @date 2023/06/27
 */
@RestController
@RequestMapping("/rooms")
public class RoomsController {

    @Resource
    private RoomsService roomsService;
    

    /**
     * 根据 id 获取房子
     *
     */
    @GetMapping("/get")
    public BaseResponse<Rooms> getRoomsById(int roomId) {
        if (roomId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Rooms rooms = roomsService.getById(roomId);
        return ResultUtils.success(rooms);
    }

    /**
     * 根据 id 获取房子中介
     *
     */
    @GetMapping("/get/user")
    public BaseResponse<RoomsVo> getUsersByRoom(@RequestParam("roomId") int roomId) {
        if (roomId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        RoomsVo roomsVo = roomsService.roomsQueryUsers(roomId);
        return ResultUtils.success(roomsVo);
    }

    /**
     * 根据 id 获取title
     *
     * @param roomId
     * @return {@link BaseResponse}<{@link List}<{@link RoomsVo}>>
     */
    @GetMapping("/get/title")
    public BaseResponse<List<RoomsVo>> getTitleByRoomId(@RequestParam("roomId") int roomId) {
        List<RoomsVo> list = roomsService.selectTitleByRoomId(roomId);
        return ResultUtils.success(list);
    }

    /**
     * 根据 id 获取pic
     *
     * @param roomId
     * @return {@link BaseResponse}<{@link List}<{@link RoomsVo}>>
     */
    @GetMapping("/get/pic")
    public BaseResponse<List<RoomsVo>> getPictureByRoomId(@RequestParam("roomId") int roomId) {
        List<RoomsVo> list = roomsService.selectPictureByRoomId(roomId);
        return ResultUtils.success(list);
    }

    /**
     * 根据 id 获取see
     *
     * @param roomId
     * @return {@link BaseResponse}<{@link List}<{@link RoomsVo}>>
     */
    @GetMapping("/get/see")
    public BaseResponse<List<RoomsVo>> getSeeByRoomId(@RequestParam("roomId") int roomId) {
        List<RoomsVo> list = roomsService.selectSeeByRoomId(roomId);
        return ResultUtils.success(list);
    }

    /**
     * 根据 id 获取house
     *
     * @param roomId
     * @return {@link BaseResponse}<{@link List}<{@link RoomsVo}>>
     */
    @GetMapping("/get/house")
    public BaseResponse<List<RoomsVo>> getHouseByRoomId(@RequestParam("roomId") int roomId) {
        List<RoomsVo> list = roomsService.selectHouseByRoomId(roomId);
        return ResultUtils.success(list);
    }

    /**
     * 创建房子
     *
     * @param roomsAddRequest
     * @return {@link BaseResponse}<{@link Integer}>
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addRooms(@RequestBody RoomsRequest roomsAddRequest) {
        if (roomsAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Rooms rooms = new Rooms();
        BeanUtils.copyProperties(roomsAddRequest,rooms);
        boolean result = roomsService.save(rooms);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(rooms.getRoomId());
    }

    /**
     * 删除房子
     *
     * @param roomsDeleteRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteRooms(@RequestBody RoomsRequest roomsDeleteRequest) {
        if (roomsDeleteRequest == null || roomsDeleteRequest.getUid() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = roomsService.removeById(roomsDeleteRequest.getRoomId());
        return ResultUtils.success(b);
    }

    /**
     * 更新房子
     *
     * @param roomsUpdateRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateRooms(@RequestBody RoomsRequest roomsUpdateRequest) {
        if (roomsUpdateRequest == null || roomsUpdateRequest.getUid() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Rooms rooms = new Rooms();
        BeanUtils.copyProperties(roomsUpdateRequest, rooms);
        boolean result = roomsService.updateById(rooms);
        return ResultUtils.success(result);
    }

    /**
     * 分页模糊查询
     *
     * @param tenantRequest
     * @return {@link BaseResponse}<{@link Page}<{@link Rooms}>>
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<Rooms>> listRoomsByPage(RoomsRequest tenantRequest) {
        long current = 1;
        long size = 10;
        String description = null;
        Rooms tenantsQuery = new Rooms();
        if (tenantRequest != null) {
            BeanUtils.copyProperties(tenantRequest, tenantsQuery);
            current = tenantRequest.getCurrent();
            size = tenantRequest.getPageSize();
            description = tenantRequest.getDescription();
        }
        QueryWrapper<Rooms> queryWrapper = new QueryWrapper<>(tenantsQuery);
        if (description != null){
            queryWrapper.like("roomName",description);
        }
        Page<Rooms> tenantPage = roomsService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(tenantPage);
    }

}
