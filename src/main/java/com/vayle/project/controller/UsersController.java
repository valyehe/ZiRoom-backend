package com.vayle.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vayle.project.common.BaseResponse;
import com.vayle.project.common.ErrorCode;
import com.vayle.project.common.ResultUtils;
import com.vayle.project.exception.BusinessException;
import com.vayle.project.model.dto.users.UsersRequest;
import com.vayle.project.model.entity.Users;
import com.vayle.project.service.UsersService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 中介接口
 *
 * @author admin
 * @date 2023/06/27
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource
    private UsersService usersService;

    /**
     * 根据 id 获取用户
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<Users> getUserById(int id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Users users = usersService.getById(id);
        return ResultUtils.success(users);
    }

    /**
     * 分页模糊查询
     *
     * @return {@link BaseResponse}<{@link Page}<{@link Users}>>
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<Users>> listUserByPage(UsersRequest usersRequest) {
        long current = 1;
        long size = 10;
        String description = null;
        Users usersQuery = new Users();
        if (usersRequest != null) {
            BeanUtils.copyProperties(usersRequest, usersQuery);
            current = usersRequest.getCurrent();
            size = usersRequest.getPageSize();
            description = usersRequest.getDescription();
        }
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>(usersQuery);
        if (description != null){
            queryWrapper.like("uname",description);
        }
        Page<Users> usersPage = usersService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(usersPage);
    }

    /**
     * 创建用户
     *
     * @param usersAddRequest
     * @return {@link BaseResponse}<{@link Integer}>
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addUser(@RequestBody UsersRequest usersAddRequest) {
        if (usersAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Users users = new Users();
        BeanUtils.copyProperties(usersAddRequest,users);
        boolean result = usersService.save(users);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(users.getUid());
    }

    /**
     * 删除用户
     *
     * @param usersDeleteRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody UsersRequest usersDeleteRequest) {
        if (usersDeleteRequest == null || usersDeleteRequest.getUid() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = usersService.removeById(usersDeleteRequest.getUid());
        return ResultUtils.success(b);
    }

    /**
     * 更新用户
     *
     * @param usersUpdateRequest
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateUser(@RequestBody UsersRequest usersUpdateRequest) {
        if (usersUpdateRequest == null || usersUpdateRequest.getUid() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Users users = new Users();
        BeanUtils.copyProperties(usersUpdateRequest, users);
        boolean result = usersService.updateById(users);
        return ResultUtils.success(result);
    }

}
