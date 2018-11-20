package com.yzf.springboot.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class ResultObject {

    @JSONField(name = "RETURN_CODE")
    private String returnCode;

    @JSONField(name = "RETURN_DATA")
    private Object returnData;

    @JSONField(name = "RETURN_DESC")
    private String returnDesc;

    @JSONField(name = "RETURN_MSG")
    private String returnMsg;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public Object getReturnData() {
        return returnData;
    }

    public void setReturnData(Object returnData) {
        this.returnData = returnData;
    }

    public String getReturnDesc() {
        return returnDesc;
    }

    public void setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    @Override
    public String toString() {
        return "ResultObject{" +
                "returnCode='" + returnCode + '\'' +
                ", returnData='" + returnData + '\'' +
                ", returnDesc='" + returnDesc + '\'' +
                ", returnMsg='" + returnMsg + '\'' +
                '}';
    }
}
