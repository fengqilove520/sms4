package top.fqq.sms4.filter;

import lombok.extern.slf4j.Slf4j;
import top.fqq.sms4.support.WrapperedRequest;
import top.fqq.sms4.support.WrapperedResponse;
import top.fqq.sms4.utils.SM4Utils;
import top.fqq.sms4.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: fqq
 * @Date: 2019/8/15 10:39
 * @Description:
 */
@Slf4j
public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig var1) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //获取请求头
        if ("true".equals(request.getHeader("ajax"))) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String requestBody = WebUtils.getRequestBody(request);
            log.info("请求密文" + requestBody);
            SM4Utils sm4 = new SM4Utils();
            sm4.setSecretKey("11HDESaAhiHHugDz");
            requestBody.getBytes("UTF-8");
            requestBody = sm4.decryptData_ECB(requestBody);
            log.info("请求明文" + requestBody);
            WrapperedResponse wrapResponse = new WrapperedResponse(response);
            WrapperedRequest wrapRequest = new WrapperedRequest(request, requestBody);
            filterChain.doFilter(wrapRequest, wrapResponse);
            byte[] data = wrapResponse.getResponseData();
            String dataRes = new String(data, "utf-8");
            log.info("返回明文" + dataRes);
            // 加密返回报文
            String   dataResMw = sm4.encryptData_ECB(dataRes);
            log.info("返回密文" + dataResMw);
            WebUtils.jsonResponse(dataResMw, response);
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }


}
