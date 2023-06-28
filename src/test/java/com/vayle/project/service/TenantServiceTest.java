package com.vayle.project.service;


import com.vayle.project.model.entity.Tenant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@SpringBootTest
public class TenantServiceTest {

    @Resource
    private TenantService tenantService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Test
    public void tenantRegister() {
        long res4 = tenantService.tenantRegister("1323978", "13237234697", "13237234697");
    }

    @Test
    public void tenantLogin(){
        Tenant tenant = tenantService.tenantLogin("13668362284", "13237234697", httpServletRequest);
        System.out.println(tenant);
        Tenant ctenant = tenantService.getLoginTenant(httpServletRequest);
        System.out.println(ctenant);
    }


}