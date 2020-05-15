package it.usuratonkachi.staticinjection.utils;

import it.usuratonkachi.staticinjection.bean.RequestBean;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@UtilityClass
public class StaticUtils {

    public String requestValue(){
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(requestAttributes -> ServletRequestAttributes.class.isAssignableFrom(requestAttributes.getClass()))
                .map(requestAttributes -> ((ServletRequestAttributes) requestAttributes))
                .map(ServletRequestAttributes::getRequest)
                .map(HttpServletRequest::getServletContext)
                .map(WebApplicationContextUtils::getWebApplicationContext)
                .map(webApplicationContext -> webApplicationContext.getBean(RequestBean.class))
                .map(RequestBean::getRequestValue)
                .orElseThrow(() -> new RuntimeException("Cannot find any context!"));
    }

}
