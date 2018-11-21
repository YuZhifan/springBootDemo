package com.yzf.springboot.service.impl;

import com.yzf.springboot.mapper.LinkmanMapper;
import com.yzf.springboot.pojo.dto.LinkmanDTO;
import com.yzf.springboot.pojo.entity.Linkman;
import com.yzf.springboot.service.LinkmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LinkmanServiceImpl implements LinkmanService {

    @Autowired
    private LinkmanMapper linkmanMapper;


    @Override
    public Linkman getLinkman(Linkman linkman) {
        return linkmanMapper.getOne(linkman);
    }

    @Override
    public List<Linkman> getLinkmanList(Linkman linkman) {
        return linkmanMapper.getLinkmanList(linkman);
    }

    @Override
    public Boolean updateLinkman(Linkman linkman) {
        return linkmanMapper.updateLinkman(linkman);
    }

    @Override
    public Boolean deleteLinkman(Linkman linkman) {
        return linkmanMapper.deleteLinkman(linkman);
    }

    @Override
    @Transactional
    public Boolean batchDeleteLinkman(LinkmanDTO linkman) {
        return linkmanMapper.batchDeleteLinkman(linkman);
    }

    @Override
    public Integer insertLinkman(Linkman linkman) {
        return linkmanMapper.insertLinkman(linkman);
    }
}
