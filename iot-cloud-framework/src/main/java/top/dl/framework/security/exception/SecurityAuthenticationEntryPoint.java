package top.dl.framework.security.exception;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import top.dl.framework.common.exception.ErrorCode;
import top.dl.framework.common.utils.HttpContextUtils;
import top.dl.framework.common.utils.JsonUtils;
import top.dl.framework.common.utils.Result;

import java.io.IOException;
/**
 * @author: minder
 * @createTime: 2025/04/25 9:04
 * @description:
 **/
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());

        response.getWriter().print(JsonUtils.toJsonString(Result.error(ErrorCode.UNAUTHORIZED)));
    }
}
