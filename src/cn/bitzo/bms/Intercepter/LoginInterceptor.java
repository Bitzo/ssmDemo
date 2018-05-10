package cn.bitzo.bms.Intercepter;

import cn.bitzo.bms.utils.TokenUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String method = httpServletRequest.getMethod();
        System.out.println("lanjieqi");
        if (method.contains("OPTIONS")) {
            return true;
        }

        String[] values = httpServletRequest.getParameterValues("token");
        if (values == null) {
            return false;
        }
        String token = values[0];
        TokenUtil tokenUtil = TokenUtil.getInstance();
        String data = tokenUtil.verifyToken(token);
        if (data == null) {
            httpServletResponse.setStatus(403);
            return false;
        } else {
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}