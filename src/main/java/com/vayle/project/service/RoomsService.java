package com.vayle.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vayle.project.model.dto.room.RoomsQueryUsersRequest;
import com.vayle.project.model.entity.Rooms;



/**
* @author admin
* @description 针对表【rooms】的数据库操作Service
* @createDate 2023-06-28 10:26:33
*/
public interface RoomsService extends IService<Rooms> {

    RoomsQueryUsersRequest roomsQueryUsersRequest(int roomId);

}
