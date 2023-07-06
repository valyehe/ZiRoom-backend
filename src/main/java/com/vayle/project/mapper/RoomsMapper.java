package com.vayle.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vayle.project.model.entity.Rooms;
import com.vayle.project.model.vo.AttributeVo;
import com.vayle.project.model.vo.RoomsVo;

import java.util.List;


/**
* @author admin
* @description 针对表【rooms】的数据库操作Mapper
* @createDate 2023-06-28 10:26:33
* @Entity generator.domain.Rooms
*/
public interface RoomsMapper extends BaseMapper<Rooms> {

    RoomsVo selectUsersByRoomId(int roomId);

    List<RoomsVo> selectTitleByRoomId(int roomId);
    List<RoomsVo> selectPictureByRoomId(int roomId);
    List<RoomsVo> selectSeeByRoomId(int roomId);
    List<RoomsVo> selectHouseByRoomId(int roomId);

}




