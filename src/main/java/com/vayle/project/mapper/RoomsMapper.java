package com.vayle.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vayle.project.model.dto.room.RoomsQueryUsersRequest;
import com.vayle.project.model.entity.Rooms;



/**
* @author admin
* @description 针对表【rooms】的数据库操作Mapper
* @createDate 2023-06-28 10:26:33
* @Entity generator.domain.Rooms
*/
public interface RoomsMapper extends BaseMapper<Rooms> {

    RoomsQueryUsersRequest selectUsersByRoomId(int roomId);

}




