package com.vayle.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vayle.project.model.entity.Rooms;
import com.vayle.project.model.vo.AttributeVo;
import com.vayle.project.model.vo.RoomsVo;

import java.util.List;


/**
* @author admin
* @description 针对表【rooms】的数据库操作Service
* @createDate 2023-06-28 10:26:33
*/
public interface RoomsService extends IService<Rooms> {

    RoomsVo roomsQueryUsers(int roomId);

    List<RoomsVo> selectTitleByRoomId(int roomId);
    List<RoomsVo> selectPictureByRoomId(int roomId);
    List<RoomsVo> selectSeeByRoomId(int roomId);
    List<RoomsVo> selectHouseByRoomId(int roomId);


}
