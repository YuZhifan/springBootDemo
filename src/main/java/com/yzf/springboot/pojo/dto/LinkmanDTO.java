package com.yzf.springboot.pojo.dto;

import com.yzf.springboot.pojo.entity.Linkman;

import java.util.List;

public class LinkmanDTO extends Linkman{

    List<String> idList;

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }
}
