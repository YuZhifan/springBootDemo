package com.yzf.springboot.service;


import com.yzf.springboot.pojo.entity.Linkman;

import java.util.List;

public interface LinkmanService {

    Linkman getLinkman(Linkman linkman);

    List<Linkman> getLinkmanList(Linkman linkman);

    Boolean updateLinkman(Linkman linkman);

    Boolean deleteLinkman(Linkman linkman);

    Boolean batchDeleteLinkman(List<String> id);

    Integer insertLinkman(Linkman linkman);

}
