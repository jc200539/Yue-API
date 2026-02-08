package com.yue.apicommon.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 接口信息
 * @TableName interface_info
 */
@TableName(value ="interface_info")
@Data
public class InterfaceInfo implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 接口地址
     */
    private String url;

    /**
     * 请求头
     */

    @TableField("request_params")
    private String requestParams;


    @TableField("request_header")
    private String requestHeader;

    /**
     * 响应头
     */
    @TableField("response_header")
    private String responseHeader;

    /**
     * 接口状态（0-关闭，1-开启）
     */

    private Integer status;

    /**
     * 请求类型
     */
    private String method;

    /**
     * 创建人
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 每次调用的费用
     */
    private BigDecimal costPerCall;

    /**
     * 接口sdk
     */

    private String sdk;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 是否删除(0-未删, 1-已删)
     */
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}