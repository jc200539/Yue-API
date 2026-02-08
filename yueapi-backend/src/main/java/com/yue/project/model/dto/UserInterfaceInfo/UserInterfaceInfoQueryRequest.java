package com.yue.project.model.dto.UserInterfaceInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.yue.project.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询请求
 *
 * @author yupi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInterfaceInfoQueryRequest extends PageRequest implements Serializable {

    /**
     * 主键
     */

    private Long id;

    /**
     * 调用用户Id
     */
    private Long user_id;

    /**
     * 接口Id
     */
    private Long interface_info_id;

    /**
     * 总调用次数
     */
    private Integer total_num;

    /**
     * 剩余调用次数
     */
    private Integer left_num;

    /**
     * 0-正常 ，1-禁用
     */
    private Integer status;



    /**
     * 是否删除(0-未删, 1-已删)
     */
    @TableLogic
    private Integer is_delete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}