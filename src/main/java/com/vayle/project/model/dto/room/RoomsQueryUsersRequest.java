package com.vayle.project.model.dto.room;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.vayle.project.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 房间请求
 *
 * @author vayle
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoomsQueryUsersRequest extends PageRequest implements Serializable {
    /**
     * 房屋ID
     */
    @TableId(type = IdType.AUTO)
    private Integer roomId;

    /**
     * 房屋名称
     */
    private String roomName;

    /**
     * 房屋面积
     */
    private String roomArea;

    /**
     * 房间编号，合租存在
     */
    private String roomNumber;

    /**
     * 房间数量，合租存在
     */
    private String roomNumbers;

    /**
     * 户型名称
     */
    private String houseType;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 位置
     */
    private String roomLocation;

    /**
     * 电梯有无
     */
    private String elevator;

    /**
     * 建成日期
     */
    private String completion;

    /**
     * 供暖方式
     */
    private String heatingType;

    /**
     * 绿化面积
     */
    private String afforest;

    /**
     * 所在区域
     */
    private String areaLocation;

    /**
     * 地铁线
     */
    private String subway;

    /**
     * 租房类型名称
     */
    private String tenementName;

    /**
     * 房间价格
     */
    private Integer roomPrice;

    /**
     * 特色户型名称
     */
    private String characterName;

    /**
     * 房间朝向
     */
    private String orientationName;

    /**
     * 房源状态名称
     */
    private String statusName;

    /**
     * 优惠活动
     */
    private String specialOffer;

    /**
     * 房间租赁时长
     */
    private Integer roomTime;

    /**
     * 房间视频地址
     */
    private String videoURL;

    /**
     * 展示图片
     */
    private String roomPic;

    /**
     * 中介id
     */
    private Integer uid;

    /**
     * 查询条件
     * */
    private String description;


    /**
     * 名称
     */
    private String uname;

    /**
     * 头像地址
     */
    private String upicture;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}