package com.yzf.springboot.mapper;

import com.yzf.springboot.pojo.entity.Linkman;
import com.yzf.springboot.pojo.entity.User;

import java.util.List;

public interface LinkmanMapper {

    List<Linkman> getList();

    Linkman getOne(Linkman linkman);

    List<Linkman> getLinkmanList(Linkman linkman);

    Boolean updateLinkman(Linkman linkman);

    Boolean deleteLinkman(Linkman linkman);

    Integer insertLinkman(Linkman linkman);

    Boolean batchDeleteLinkman(List<String> id);
}
