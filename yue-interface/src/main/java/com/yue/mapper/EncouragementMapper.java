package com.yue.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yue.model.entity.Encouragement;

/**
 * 接口信息
 */
public interface EncouragementMapper extends BaseMapper<Encouragement> {

    Encouragement getRandomEncouragement();
}