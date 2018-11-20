package com.yzf.springboot.controller;

import com.yzf.springboot.admin.exception.BizException;
import com.yzf.springboot.pojo.entity.Linkman;
import com.yzf.springboot.pojo.entity.User;
import com.yzf.springboot.service.LinkmanService;
import com.yzf.springboot.service.UserService;
import com.yzf.springboot.util.JWTUtil;
import com.yzf.springboot.util.LoginUserHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/linkman")
@RestController
public class LinkmanController extends BaseController {

    @Autowired
    private LinkmanService linkmanService;

    @RequestMapping(value = "/list")
    public Object list(HttpServletRequest request, Linkman linkman) throws Exception {
        String userId = LoginUserHelper.getLoginUserId(request);
        linkman.setUid(userId);
        return renderSuccess(linkmanService.getLinkmanList(linkman));
    }

    @RequestMapping(value = "/add")
    public Object add(HttpServletRequest request, @RequestBody Linkman linkman) throws Exception {
        String userId = LoginUserHelper.getLoginUserId(request);
        linkman.setUid(userId);
        return renderSuccess(linkmanService.insertLinkman(linkman));
    }

    @RequestMapping(value = "/delete")
    public Object delete(HttpServletRequest request, @RequestBody Linkman linkman) throws Exception {
        if (StringUtils.isBlank(linkman.getId())) {
            throw new BizException("F000", "param[id] is need");
        }
        String userId = LoginUserHelper.getLoginUserId(request);
        linkman.setUid(userId);
        return renderSuccess(linkmanService.deleteLinkman(linkman));
    }

    @RequestMapping(value = "/update")
    public Object update(HttpServletRequest request, @RequestBody Linkman linkman) throws Exception {
        if (StringUtils.isBlank(linkman.getId())) {
            throw new BizException("F000", "param[id] is need");
        }
        String userId = LoginUserHelper.getLoginUserId(request);
        linkman.setUid(userId);
        return renderSuccess(linkmanService.updateLinkman(linkman));
    }
}
