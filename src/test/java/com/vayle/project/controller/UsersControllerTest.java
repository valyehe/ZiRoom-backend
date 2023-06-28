package com.vayle.project.controller;


import com.vayle.project.common.BaseResponse;
import com.vayle.project.model.dto.users.UsersRequest;
import com.vayle.project.model.entity.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UsersControllerTest {

    @Resource
    private UsersController usersController;

    @Test
    public void getUserById() {
        BaseResponse<Users> user = usersController.getUserById(1);
        Assertions.assertNotNull(user);
    }

    @Test
    public void listUserByPage() {
        UsersRequest usersRequest = new UsersRequest();
        usersController.listUserByPage(usersRequest);
    }
}