package com.vayle.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vayle.project.mapper.RoomsMapper;
import com.vayle.project.model.entity.Rooms;
import com.vayle.project.model.vo.RoomsVo;
import com.vayle.project.service.RoomsService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author admin
* @description 针对表【rooms】的数据库操作Service实现
* @createDate 2023-06-28 10:26:33
*/
@Service
public class RoomsServiceImpl extends ServiceImpl<RoomsMapper, Rooms>
    implements RoomsService {

    @Resource
    RoomsMapper roomsMapper;

    @Override
    public RoomsVo roomsQueryUsers(int roomId) {
        return roomsMapper.selectUsersByRoomId(roomId);
    }

    @Override
    public List<RoomsVo> selectTitleByRoomId(int roomId) {
        return new ArrayList<>(roomsMapper.selectTitleByRoomId(roomId));
    }

    @Override
    public List<RoomsVo> selectPictureByRoomId(int roomId) {
        return new ArrayList<>(roomsMapper.selectPictureByRoomId(roomId));
    }

    @Override
    public List<RoomsVo> selectSeeByRoomId(int roomId) {
        return new ArrayList<>(roomsMapper.selectSeeByRoomId(roomId));
    }

    @Override
    public List<RoomsVo> selectHouseByRoomId(int roomId) {
        return new ArrayList<>(roomsMapper.selectHouseByRoomId(roomId));
    }
}




