package com.yzf.springboot.controller;

import com.yzf.springboot.pojo.dto.ResultObject;

public class BaseController {

    protected ResultObject renderSuccess(Object obj, String desc) {
        ResultObject result = new ResultObject();
        result.setReturnCode("S000");
        result.setReturnData(obj);
        result.setReturnDesc(desc);
        return result;
    }

    protected ResultObject renderSuccess(Object obj) {
        return renderSuccess(obj, "操作成功");
    }


    protected ResultObject renderSuccess() {
        return renderSuccess(null);
    }

    protected ResultObject renderFail(String msg) {
        ResultObject result = new ResultObject();
        result.setReturnCode("F000");
        result.setReturnMsg(msg);
        return result;
    }

    protected ResultObject renderFail() {
        return renderFail(null);
    }
}
