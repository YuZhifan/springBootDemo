package com.yzf.springboot.controller;

import com.yzf.springboot.admin.exception.BizException;
import com.yzf.springboot.pojo.dto.ResultObject;
import com.yzf.springboot.pojo.entity.Hi;
import com.yzf.springboot.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HiController {

    @Autowired
    private HiService hiService;

    @RequestMapping("/hi")
    public Object sayHi() throws Exception {
        ResultObject result = new ResultObject();
        System.out.println("HiController.sayHi");
        List<Hi> list = hiService.sayHi();
//        throw new BizException("sss","sssssss");
//        throw new Exception();
        result.setReturnData(list);
        result.setReturnCode("S000");
        return result;
//        return new ResultObject();
    }

    @RequestMapping("/hi2")
    public Object sayHi2(String name,String key) throws Exception {
        System.out.println(name);
        return name;
    }
}
