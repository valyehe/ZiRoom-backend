package com.vayle.project.service;

import com.vayle.project.model.entity.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UsersServiceTest {

    @Resource
    private UsersService usersService;

    @Test
    public void get(){
        Users users = usersService.getById(1);
        Assertions.assertNotNull(users);
    }

}