package top.kass.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final String[] IGNORE_URI = {"/login", "/register"};
    private static final String[] UNLOGIN_URI = {"/login", "/register"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        String url = request.getRequestURI();
        HttpSession session = request.getSession();

        if (url.startsWith("/api")) {
            return true;
        }

        if (session.getAttribute("id") != null) {
            for (String s : UNLOGIN_URI) {
                if (url.equals(s)) {
                    response.sendRedirect("/");
                    return false;
                }
            }

        } else {
            boolean flag = false;
            for (String s : IGNORE_URI) {
                if (url.equals(s)) {
                    flag = true;
                }
            }
            if (!flag) {
                response.sendRedirect("/login");
                return false;
            }
        }
        return true;
    }

}