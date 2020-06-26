package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;


@WebFilter(filterName = "CodingFilter",initParams = {@WebInitParam(name = "encoding",value = "utf-8")},urlPatterns = "/*")
public class CodingFilter implements Filter {
    String encoding=null;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if(encoding!=null){
            req.setCharacterEncoding(encoding);
            resp.setContentType("text/html;charset="+encoding);
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
    }

}
