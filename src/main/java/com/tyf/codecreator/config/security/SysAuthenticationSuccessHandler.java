package com.tyf.codecreator.config.security;

import com.tyf.codecreator.code.dao.UserRepository;
import com.tyf.codecreator.code.entity.User;
import com.tyf.codecreator.code.service.MenuService;
import com.tyf.codecreator.utils.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Component
public class SysAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final static Logger logger = LoggerFactory.getLogger(SysAuthenticationSuccessHandler.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MenuService menuService;

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = userRepository.findUserByUsername(SecurityUtil.getCurUserName());
        session.setMaxInactiveInterval(60 * 60 * 8);
        /**
         * 将当前用户的用户信息以及权限信息存入session中
         */
        session.setAttribute(SecurityUtil.CURRENT_USER_SESSION, user);
        session.setAttribute(SecurityUtil.CURRENT_USER_MENU, menuService.getMenusByUserId(user.getId()));
        redirectStrategy.sendRedirect(request,response,"/index");
        logger.info("用户-"+user.getUsername()+"-登录成功!");
    }


    @Override
    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    @Override
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
}
