package com.vayle.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vayle.project.service.UsersService;
import com.vayle.project.model.entity.Users;
import com.vayle.project.mapper.UsersMapper;
import org.springframework.stereotype.Service;


/**
 * @author admin
 * @date 2023/06/27
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService {

}




