package com.vayle.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vayle.project.common.ErrorCode;
import com.vayle.project.common.RandomStr;
import com.vayle.project.exception.BusinessException;
import com.vayle.project.mapper.TenantMapper;
import com.vayle.project.model.entity.Tenant;
import com.vayle.project.service.TenantService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.vayle.project.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author admin
* @description 针对表【tenant】的数据库操作Service实现
* @createDate 2023-06-27 14:41:33
*/
@Service
@Slf4j
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant>
    implements TenantService {

    @Resource
    private TenantMapper tenantMapper;

    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "DZ";

    @Override
    public long tenantRegister(String tenantPhone, String tenantPassword, String checkPassword) {
        // 1. 校验
        if (StringUtils.isAnyBlank(tenantPhone, tenantPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (tenantPhone.length() < 11) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "手机号过短");
        }
        if (tenantPassword.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码过短");
        }
        // 密码和校验密码相同
        if (!tenantPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入的密码不一致");
        }
        synchronized (tenantPassword.intern()) {
            // 账户不能重复
            QueryWrapper<Tenant> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("tPhone", tenantPhone);
            long count = tenantMapper.selectCount(queryWrapper);
            if (count > 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
            }
            // 2. 加密
            String encryptPassword = DigestUtils.md5DigestAsHex((SALT + tenantPassword).getBytes());
            // 3. 插入数据
            RandomStr randomStr = new RandomStr();
            String randomString = randomStr.getRandomString();
            Tenant tenant = new Tenant();
            tenant.setTPhone(tenantPhone);
            tenant.setTNickname("自如客"+randomString);
            tenant.setTPassword(encryptPassword);
            boolean saveResult = this.save(tenant);
            if (!saveResult) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
            }
            return tenant.getTId();
        }
    }

    @Override
    public Tenant tenantLogin(String tenantPhone, String tenantPassword, HttpServletRequest request) {
        // 1. 校验
        if (StringUtils.isAnyBlank(tenantPhone, tenantPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (tenantPhone.length() < 11) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号错误");
        }
        if (tenantPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + tenantPassword).getBytes());
        // 查询用户是否存在
        QueryWrapper<Tenant> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tPhone", tenantPhone);
        queryWrapper.eq("tPassword", encryptPassword);
        Tenant tenant = tenantMapper.selectOne(queryWrapper);
        // 用户不存在
        if (tenant == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
        }
        // 3. 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, tenant);
        return tenant;
    }


    @Override
    public Tenant getLoginTenant(HttpServletRequest request) {
        // 先判断是否已登录
        Object tenantObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        Tenant currentTenant = (Tenant) tenantObj;
        if (currentTenant == null || currentTenant.getTId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        long tenantId = currentTenant.getTId();
        currentTenant = this.getById(tenantId);
        if (currentTenant == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentTenant;
    }

    @Override
    public boolean tenantLogout(HttpServletRequest request) {
        if (request.getSession().getAttribute(USER_LOGIN_STATE) == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "未登录");
        }
        // 移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }

    @Override
    public int tenantUpdate(Tenant tenant, HttpServletRequest request) {

        Integer tId = tenant.getTId();
        String tNickname = tenant.getTNickname();
        String tenantPhone = tenant.getTPhone();
        String tEmail = tenant.getTEmail();
        String tenantPassword = tenant.getTPassword();
        if (StringUtils.isAnyBlank(tenantPhone, tenantPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (tenantPhone.length() < 11) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号错误");
        }
        if (tenantPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
        }
        synchronized (tenantPassword.intern()) {
            // 账户不能重复
            QueryWrapper<Tenant> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("tPhone", tenantPhone);
            long count = tenantMapper.selectCount(queryWrapper);
            if (count > 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
            }
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + tenantPassword).getBytes());

        Tenant updateTenant = new Tenant();
        updateTenant.setTId(tId);
        updateTenant.setTNickname(tNickname);
        updateTenant.setTPhone(tenantPhone);
        updateTenant.setTEmail(tEmail);
        updateTenant.setTPassword(encryptPassword);

        return tenantMapper.updateById(updateTenant);
    }


}




