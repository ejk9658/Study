package com.cos.jwt.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter1 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        // 토큰 : cos 이걸 만들어줘야 함. id, pw 정상적으로 들어와서 로그인이 완료되면 토큰을 만들어주고 그걸 응답해준다.
        // 요청할 때마다 header 에 Authorization 에 value 값으로 토큰을 가지고 올텐데
        // 그 때 토큰이 넘어오면 이 토큰이 내가 만든 토큰이 맞는지 검증만 하면 됨. (RSA, HS256)
        if(req.getMethod().equals("POST")) {
            System.out.println("POST 요청됨");
            String headerAuth = req.getHeader("Authorization");
            System.out.println(headerAuth);

            if(headerAuth.equals("cos")) {
                chain.doFilter(req, res);
            } else {
                PrintWriter out = res.getWriter();
                System.out.println("인증 안 됨");
            }
        }
        System.out.println("필터1");
        chain.doFilter(req, res);
    }
}
