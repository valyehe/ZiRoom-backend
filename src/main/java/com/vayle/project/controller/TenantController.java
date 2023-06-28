package com.vayle.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vayle.project.common.BaseResponse;
import com.vayle.project.common.ErrorCode;
import com.vayle.project.common.ResultUtils;
import com.vayle.project.exception.BusinessException;
import com.vayle.project.model.dto.tenant.TenantRequest;
import com.vayle.project.model.dto.users.UsersRequest;
import com.vayle.project.model.entity.Tenant;
import com.vayle.project.model.entity.Users;
import com.vayle.project.service.TenantService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户接口
 *
 * @author admin
 * @date 2023/06/27
 */
@RestController
@RequestMapping("/tenant")
public class TenantController {

    @Resource
    private TenantService tenantService;


    // region 登录相关

    /**
     * 用户注册
     *
     * @return {@link BaseResponse}<{@link Long}>
     */
    @PostMapping("/register")
    public BaseResponse<Long> tenantRegister(@RequestBody TenantRequest tenantRequest) {
        if (tenantRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String tenantPhone = tenantRequest.getTPhone();
        String tenantPassword = tenantRequest.getTPassword();
        String checkPassword = tenantRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(tenantPhone, tenantPassword, checkPassword)) {
            return null;
        }
        long result = tenantService.tenantRegister(tenantPhone, tenantPassword, checkPassword);
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     *
     * @param tenantLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<Tenant> tenantLogin(@RequestBody TenantRequest tenantLoginRequest, HttpServletRequest request) {
        if (tenantLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String tenantPhone = tenantLoginRequest.getTPhone();
        String tenantPassword = tenantLoginRequest.getTPassword();
        if (StringUtils.isAnyBlank(tenantPhone, tenantPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Tenant tenant = tenantService.tenantLogin(tenantPhone, tenantPassword, request);
        return ResultUtils.success(tenant);
    }

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> tenantLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = tenantService.tenantLogout(request);
        return ResultUtils.success(result);
    }

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    @GetMapping("/get/login")
    public BaseResponse<Tenant> getLoginTenant(HttpServletRequest request) {
        Tenant tenant = tenantService.getLoginTenant(request);
        return ResultUtils.success(tenant);
    }

    // endregion

    // region 增删改查

    /**
     * 创建用户
     *
     * @param tenantAddRequest
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addTenant(@RequestBody TenantRequest tenantAddRequest) {
        if (tenantAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Tenant tenant = new Tenant();
        BeanUtils.copyProperties(tenantAddRequest, tenant);
        boolean result = tenantService.save(tenant);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(tenant.getTId());
    }

    /**
     * 删除用户
     *
     * @param tenantDeleteRequest
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteTenant(@RequestBody TenantRequest tenantDeleteRequest) {
        if (tenantDeleteRequest == null || tenantDeleteRequest.getTId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = tenantService.removeById(tenantDeleteRequest.getTId());
        return ResultUtils.success(b);
    }

    /**
     * 更新用户
     *
     * @param tenantUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateTenant(@RequestBody TenantRequest tenantUpdateRequest) {
        if (tenantUpdateRequest == null || tenantUpdateRequest.getTId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Tenant tenant = new Tenant();
        BeanUtils.copyProperties(tenantUpdateRequest, tenant);
        boolean result = tenantService.updateById(tenant);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取用户
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<Tenant> getTenantById(int id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Tenant tenant = tenantService.getById(id);
        return ResultUtils.success(tenant);
    }

    /**
     * 获取用户列表
     *
     * @param tenantQueryRequest
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<List<Tenant>> listTenant(TenantRequest tenantQueryRequest) {
        Tenant tenantQuery = new Tenant();
        if (tenantQueryRequest != null) {
            BeanUtils.copyProperties(tenantQueryRequest, tenantQuery);
        }
        QueryWrapper<Tenant> queryWrapper = new QueryWrapper<>(tenantQuery);
        List<Tenant> tenantList = tenantService.list(queryWrapper);
        return ResultUtils.success(tenantList);
    }

    /**
     * 分页模糊查询
     *
     * @param tenantRequest
     * @return {@link BaseResponse}<{@link Page}<{@link Tenant}>>
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<Tenant>> listTenantByPage(TenantRequest tenantRequest) {
        long current = 1;
        long size = 10;
        String description = null;
        Tenant tenantsQuery = new Tenant();
        if (tenantRequest != null) {
            BeanUtils.copyProperties(tenantRequest, tenantsQuery);
            current = tenantRequest.getCurrent();
            size = tenantRequest.getPageSize();
            description = tenantRequest.getDescription();
        }
        QueryWrapper<Tenant> queryWrapper = new QueryWrapper<>(tenantsQuery);
        if (description != null){
            queryWrapper.like("tNickname",description);
        }
        Page<Tenant> tenantPage = tenantService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(tenantPage);
    }

    // endregion
}
