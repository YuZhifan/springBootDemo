package com.yzf.springboot.controller;

import com.yzf.springboot.pojo.dto.ResultObject;
import com.yzf.springboot.pojo.entity.Hi;
import com.yzf.springboot.service.HiService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "测试controller")
@RestController
public class HiController extends BaseController {

    /**
     * 测试从Spring Cloud Config获取的配置文件参数
     * 如不需加载则注释掉
     */
    @Value("${foo}")
    private String strGetFromSpringCloudConfig;

    @Autowired
    private HiService hiService;

    @RequestMapping(value = "/hi")
    public Object sayHi() throws Exception {
        ResultObject result = new ResultObject();
        System.out.println("HiController.sayHi");
        System.out.println("strGetFromSpringCloudConfig:" + strGetFromSpringCloudConfig);
        List<Hi> list = hiService.sayHi();
        return renderSuccess(list);
    }

}
