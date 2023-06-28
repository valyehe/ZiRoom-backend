package com.vayle.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vayle.project.common.BaseResponse;
import com.vayle.project.common.ErrorCode;
import com.vayle.project.common.ResultUtils;
import com.vayle.project.exception.BusinessException;
import com.vayle.project.model.dto.room.RoomPictureRequest;
import com.vayle.project.model.entity.Roompicture;
import com.vayle.project.service.RoompictureService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 租房图片接口
 *
 * @author admin
 * @date 2023/06/27
 */
@RestController
@RequestMapping("/roomPicture")
public class RoomPictureController {

    @Resource
    private RoompictureService roompictureService;

    /**
     * 根据 id 获取租房图片
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<Roompicture> getRoomPictureById(int id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Roompicture roompicture = roompictureService.getById(id);
        return ResultUtils.success(roompicture);
    }


    /**
     * 分页获取列表
     *
     * @return {@link BaseResponse}<{@link Page}<{@link Roompicture}>>
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<Roompicture>> listRoomPictureByPage(RoomPictureRequest roompictureRequest) {
        long current = 1;
        long size = 10;
        Roompicture roompictureQuery = new Roompicture();
        if (roompictureRequest != null) {
            BeanUtils.copyProperties(roompictureRequest, roompictureQuery);
            current = roompictureRequest.getCurrent();
            size = roompictureRequest.getPageSize();
        }
        QueryWrapper<Roompicture> queryWrapper = new QueryWrapper<>(roompictureQuery);
        Page<Roompicture> roompicturePage = roompictureService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(roompicturePage);
    }

    /**
     * 创建租房图片
     *
     * @param roomPictureAddRequest
     * @return {@link BaseResponse}<{@link Integer}>
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addRoomPicture(@RequestBody RoomPictureRequest roomPictureAddRequest) {
        if (roomPictureAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Roompicture roompicture = new Roompicture();
        BeanUtils.copyProperties(roomPictureAddRequest,roompicture);
        boolean result = roompictureService.save(roompicture);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(roompicture.getId());
    }

    /**
     * 删除租房图片
     *
     * @param roomPictureDeleteRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteRoomPicture(@RequestBody RoomPictureRequest roomPictureDeleteRequest) {
        if (roomPictureDeleteRequest == null || roomPictureDeleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = roompictureService.removeById(roomPictureDeleteRequest.getId());
        return ResultUtils.success(b);
    }

    /**
     * 更新租房图片
     *
     * @param roomPictureUpdateRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateRoomPicture(@RequestBody RoomPictureRequest roomPictureUpdateRequest) {
        if (roomPictureUpdateRequest == null || roomPictureUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Roompicture roompicture = new Roompicture();
        BeanUtils.copyProperties(roomPictureUpdateRequest, roompicture);
        boolean result = roompictureService.updateById(roompicture);
        return ResultUtils.success(result);
    }

}
