package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Rq.Rq;

public class MyFilter implements Filter {
	
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest httpRequest = (HttpServletRequest) request;
    	HttpServletResponse httpResponse = (HttpServletResponse) response;
    	
    	Rq rq = new Rq(httpRequest, httpResponse);
    	
    	rq.init();
    	
        chain.doFilter(request, response);

        // 응답 후에 수행할 작업을 작성합니다.
        // 예를 들어, 응답 수정, 로깅 등을 수행할 수 있습니다.
    }

    @Override
    public void destroy() {
        // 필터 제거 시 수행할 작업 (선택 사항)
    }
}