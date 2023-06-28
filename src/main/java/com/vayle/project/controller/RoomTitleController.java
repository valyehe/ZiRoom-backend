package com.vayle.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vayle.project.common.BaseResponse;
import com.vayle.project.common.ErrorCode;
import com.vayle.project.common.ResultUtils;
import com.vayle.project.exception.BusinessException;
import com.vayle.project.model.dto.room.RoomTitleRequest;
import com.vayle.project.model.dto.tenant.TenantRequest;
import com.vayle.project.model.entity. Roomtitle;
import com.vayle.project.model.entity.Tenant;
import com.vayle.project.service. RoomtitleService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 房屋标签接口
 *
 * @author admin
 * @date 2023/06/27
 */
@RestController
@RequestMapping("/roomTitle")
public class RoomTitleController {

    @Resource
    private RoomtitleService roomtitleService;

    /**
     * 根据 id 获取房屋标签
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse< Roomtitle> getRoomTitleById(int id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
         Roomtitle roomtitle = roomtitleService.getById(id);
        return ResultUtils.success(roomtitle);
    }


    /**
     * 分页获取列表
     *
     * @param roomtitleRequest
     * @return {@link BaseResponse}<{@link Page}< {@link Roomtitle}>>
     */
    @GetMapping("/list/page")
    public BaseResponse<Page< Roomtitle>> listRoomTitleByPage(RoomTitleRequest roomtitleRequest) {
        long current = 1;
        long size = 10;
         Roomtitle roomtitleQuery = new  Roomtitle();
        if (roomtitleRequest != null) {
            BeanUtils.copyProperties(roomtitleRequest, roomtitleQuery);
            current = roomtitleRequest.getCurrent();
            size = roomtitleRequest.getPageSize();
        }
        QueryWrapper< Roomtitle> queryWrapper = new QueryWrapper<>(roomtitleQuery);
        Page< Roomtitle> roomtitlePage = roomtitleService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(roomtitlePage);
    }

    /**
     * 创建房屋标签
     *
     * @param roomTitleAddRequest
     * @return {@link BaseResponse}<{@link Integer}>
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addRoomTitle(@RequestBody  RoomTitleRequest roomTitleAddRequest) {
        if (roomTitleAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
         Roomtitle roomtitle = new  Roomtitle();
        BeanUtils.copyProperties(roomTitleAddRequest,roomtitle);
        boolean result = roomtitleService.save(roomtitle);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(roomtitle.getId());
    }

    /**
     * 删除房屋标签
     *
     * @param roomTitleDeleteRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteRoomTitle(@RequestBody  RoomTitleRequest roomTitleDeleteRequest) {
        if (roomTitleDeleteRequest == null || roomTitleDeleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = roomtitleService.removeById(roomTitleDeleteRequest.getId());
        return ResultUtils.success(b);
    }

    /**
     * 更新房屋标签
     *
     * @param roomTitleUpdateRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateRoomTitle(@RequestBody  RoomTitleRequest roomTitleUpdateRequest) {
        if (roomTitleUpdateRequest == null || roomTitleUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
         Roomtitle roomtitle = new  Roomtitle();
        BeanUtils.copyProperties(roomTitleUpdateRequest, roomtitle);
        boolean result = roomtitleService.updateById(roomtitle);
        return ResultUtils.success(result);
    }

}
