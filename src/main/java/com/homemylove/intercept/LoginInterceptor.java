package com.homemylove.intercept;

import com.homemylove.auth.AuthInfo;
import com.homemylove.auth.Authenticator;
import com.homemylove.mdc.MDCKey;
import com.homemylove.mdc.MDCScope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private Authenticator authenticator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try(MDCScope mdcScope = new MDCScope()) {
            String token = request.getHeader("token");
            token = StringUtils.isEmpty(token) ? request.getHeader(HttpHeaders.AUTHORIZATION) : token;
            if(StringUtils.isEmpty(token)){
                return true;
//                throw new RuntimeException("未携带 token");
            }
            AuthInfo authInfo = authenticator.auth(token);
            mdcScope.put(MDCKey.USER_ID,String.valueOf(authInfo.getId()));
            mdcScope.put(MDCKey.USER_NAME,authInfo.getUsername());
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
