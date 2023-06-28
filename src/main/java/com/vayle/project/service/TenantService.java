package com.vayle.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vayle.project.model.entity.Tenant;

import javax.servlet.http.HttpServletRequest;

/**
* @author admin
* @description 针对表【tenant】的数据库操作Service
* @createDate 2023-06-27 14:41:33
*/
public interface TenantService extends IService<Tenant> {

    /**
     * 用户注册
     *
     * @param tenantPhone   用户账户
     * @param tenantPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long tenantRegister(String tenantPhone, String tenantPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param tenantPhone  用户账户
     * @param tenantPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    Tenant tenantLogin(String tenantPhone, String tenantPassword, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    Tenant getLoginTenant(HttpServletRequest request);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    boolean tenantLogout(HttpServletRequest request);
    

}
