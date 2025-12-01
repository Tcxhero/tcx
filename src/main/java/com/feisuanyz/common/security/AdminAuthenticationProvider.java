package com.feisuanyz.common.security;

import java.util.Collections;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * <p>
 *   后台管理员认证提供者
 * </p>
 * @author tianchunxu
 */
@Component
public class AdminAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // 这里假设有一个方法用于校验管理员用户名和密码
        if (!authenticateAdmin(username, password)) {
            throw new BadCredentialsException("Invalid admin credentials");
        }

        return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    // 模拟校验管理员用户名和密码
    private boolean authenticateAdmin(String username, String password) {
        // 这里应该有实际的管理员认证逻辑
        return "admin".equals(username) && "password".equals(password);
    }
}