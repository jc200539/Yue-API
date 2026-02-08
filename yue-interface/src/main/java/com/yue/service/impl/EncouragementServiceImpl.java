package com.yue.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yue.mapper.EncouragementMapper;
import com.yue.model.entity.Encouragement;
import com.yue.service.EncouragementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author RicKey
 * @description 针对表【encouragement(心灵鸡汤)】的数据库操作Service实现
 * @createDate 2024-10-22 22:08:31
 */
@Service
public class EncouragementServiceImpl extends ServiceImpl<EncouragementMapper, Encouragement>
        implements EncouragementService {

    private final EncouragementMapper encouragementMapper;

    @Autowired
    public EncouragementServiceImpl(EncouragementMapper encouragementMapper) {
        this.encouragementMapper = encouragementMapper;
    }


    /**
     * @return
     */
    @Override
    public Encouragement getRandomEncouragement() {
        return encouragementMapper.getRandomEncouragement();
    }
}