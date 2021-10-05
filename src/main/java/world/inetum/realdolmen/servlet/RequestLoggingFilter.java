package world.inetum.realdolmen.servlet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebFilter(urlPatterns = "/*")
public class RequestLoggingFilter extends HttpFilter {

    Logger logger = Logger.getLogger(RequestLoggingFilter.class.getName());

    @Override
    protected void doFilter(
            HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain
    ) throws IOException, ServletException {
        logger.log(
                Level.INFO,
                "Req URI: {0}",
                req.getRequestURI());
        super.doFilter(req, res, chain);
        logger.log(
                Level.INFO,
                "Res status: {0}",
                res.getStatus());
    }
}
