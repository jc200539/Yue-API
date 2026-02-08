package com.yue.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yue.mapper.CityMapper;
import com.yue.model.entity.City;
import com.yue.service.CityService;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City>
        implements CityService {

}