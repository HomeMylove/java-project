package com.homemylove.auth;

public interface Authenticator {

    AuthInfo auth(String token);
}
