package com.yzf.springboot.admin.exception;

import com.alibaba.fastjson.JSONObject;
import com.yzf.springboot.pojo.dto.ResultObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理
 *
 * @date 2017年10月25日
 */
public class CommonExceptionHandler implements HandlerExceptionResolver {

    /**
     * 定义记录日志信息
     */
    protected Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handlerMethod,
                                         Exception ex) {
        // log记录异常
        logger.error("Handler execution resulted in exception", ex);
        ResultObject result = new ResultObject();
        try {
            if (ex instanceof BizException) {
                result.setReturnCode(((BizException) ex).getCode());
                result.setReturnMsg(((BizException) ex).getMsg());
            } else {
                result.setReturnCode("E000");
                result.setReturnMsg(ex.getStackTrace().toString());
            }
            // 设置json头
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(JSONObject.toJSONString(result));
        } catch (Exception invocationEx) {
            logger.error("Acquiring ModelAndView for Exception [" + ex + "] resulted in an exception.", invocationEx);
        }
        // 不能直接返回null
        return new ModelAndView();
    }

}
