package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter");

        //ServletRequest은 HttpServletRequest의 부모인터페이스인데 기능이 적어서 HttpServletRequest으로 다운 캐스팅해줘야한다
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        //모든 사용자의 요청URI 로그남기기
        String requestURI = httpRequest.getRequestURI();
        /**
         * getRequestURI가 아닌 getRequestURL로 했더니 로그에 localhost:8080까지 모두 포함이 되어서 나왔다!
         * URI랑 URL과의 차이점에 대해서 다시 생각하기.
         */
        String uuid = UUID.randomUUID().toString();//들어오는 요청들의 구분을 위해서 랜덤생성자 생성
        try {
            log.info("REQUEST [{}][{}]",uuid,requestURI);
            chain.doFilter(request,response); //chain을 통해서 다음 필터를 호출해줘야한다.
        } catch (Exception e){
            throw e;
        } finally {
            log.info("RESPONSE [{}][{}]",uuid,requestURI);
        }

    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
