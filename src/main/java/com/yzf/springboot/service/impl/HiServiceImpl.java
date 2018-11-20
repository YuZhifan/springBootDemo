package com.yzf.springboot.service.impl;

import com.yzf.springboot.mapper.HiMapper;
import com.yzf.springboot.pojo.entity.Hi;
import com.yzf.springboot.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HiServiceImpl implements HiService {

    @Autowired
    private HiMapper hiMapper;

    @Override
    public List<Hi> sayHi() {
        System.out.println("HiServiceImpl.sayHi");
        return hiMapper.getList();
    }
}
