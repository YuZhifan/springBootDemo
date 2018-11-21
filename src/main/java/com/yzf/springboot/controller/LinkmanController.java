package com.yzf.springboot.controller;

import com.yzf.springboot.admin.exception.BizException;
import com.yzf.springboot.pojo.dto.LinkmanDTO;
import com.yzf.springboot.pojo.entity.Linkman;
import com.yzf.springboot.service.LinkmanService;
import com.yzf.springboot.util.LoginUserHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(description = "联系人相关")
@RequestMapping(value = "/linkman")
@RestController
public class LinkmanController extends BaseController {

    @Autowired
    private LinkmanService linkmanService;

    @ApiOperation(value = "查询联系人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "linkman", value = "{\"name\":\"\"}")
    })
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Object list(HttpServletRequest request, @RequestBody Linkman linkman) throws Exception {
        String userId = LoginUserHelper.getLoginUserId(request);
        linkman.setUid(userId);
        return renderSuccess(linkmanService.getLinkmanList(linkman));
    }

    @ApiOperation(value = "查询联系人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "linkman", value = "{\"id\":\"\"}")
    })
    @RequestMapping(value = "/getOne", method = RequestMethod.POST)
    public Object getOne(HttpServletRequest request, @RequestBody Linkman linkman) throws Exception {
        String userId = LoginUserHelper.getLoginUserId(request);
        linkman.setUid(userId);
        return renderSuccess(linkmanService.getLinkman(linkman));
    }

    @ApiOperation(value = "添加联系人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "linkman", value = "{\"qq\":\"\",\"phone\":\"\",\"name\":\"\"}")
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(HttpServletRequest request, @RequestBody Linkman linkman) throws Exception {
        String userId = LoginUserHelper.getLoginUserId(request);
        linkman.setUid(userId);
        return renderSuccess(linkmanService.insertLinkman(linkman));
    }

    @ApiOperation(value = "删除联系人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "linkman", value = "{\"id\":\"\"}")
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(HttpServletRequest request, @RequestBody Linkman linkman) throws Exception {
        if (StringUtils.isBlank(linkman.getId())) {
            throw new BizException("F000", "param[id] is need");
        }
        String userId = LoginUserHelper.getLoginUserId(request);
        linkman.setUid(userId);
        return renderSuccess(linkmanService.deleteLinkman(linkman));
    }

    @ApiOperation(value = "删除联系人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "linkman", value = "{\"idList\":[\"1\",\"2\"]}")
    })
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    public Object deleteBatch(HttpServletRequest request, @RequestBody LinkmanDTO linkman) throws Exception {
        if (null == linkman.getIdList() || linkman.getIdList().size() < 1) {
            throw new BizException("F000", "param[idList] is need");
        }
        String userId = LoginUserHelper.getLoginUserId(request);
        linkman.setUid(userId);
        return renderSuccess(linkmanService.batchDeleteLinkman(linkman));
    }

    @ApiOperation(value = "修改联系人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "linkman", value = "{\"qq\":\"\",\"phone\":\"\",\"name\":\"\",\"id\":\"\"}")
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(HttpServletRequest request, @RequestBody Linkman linkman) throws Exception {
        if (StringUtils.isBlank(linkman.getId())) {
            throw new BizException("F000", "param[id] is need");
        }
        String userId = LoginUserHelper.getLoginUserId(request);
        linkman.setUid(userId);
        return renderSuccess(linkmanService.updateLinkman(linkman));
    }
}
