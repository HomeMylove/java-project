package com.homemylove.auth;

import com.homemylove.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class AuthenticatorImpl implements Authenticator{

    @Value("${jwt.auth.secret}")
    private String SECRET_KEY;

    @Override
    public AuthInfo auth(String token) {
        String authToken;
        int index = token.indexOf(" ");
        if(index == -1){
            authToken = token;
        }else {
            String tokenType = token.substring(0,index);
            if(!"Bearer".equals(tokenType)){
                // Todo 统一异常处理
                throw new RuntimeException(String.format("无法识别的的token类型[%s]",tokenType));
            }else {
                authToken = token.substring(index).trim();
            }
        }
        return JwtUtil.verifyToken(authToken,SECRET_KEY);
    }
}
