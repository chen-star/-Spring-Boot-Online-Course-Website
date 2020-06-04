package com.course.server.config;

import com.course.server.util.UuidUtil;
import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

/**
 * @author Alex CHEN
 * @version 1.0
 * @since 2020-06-03 22:41
 */
@Aspect
@Component
public class LogAspect {

    private final static Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * com.course.*.controller..*Controller.*(..))")
    public void controllerPointcut() {}

    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // UUID
        MDC.put("UUID", UuidUtil.getShortUuid());

        // print logs
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        String nameCn = "";
        if (name.contains("list") || name.contains("query")) {
            nameCn = "Query";
        } else if (name.contains("save")) {
            nameCn = "Save";
        } else if (name.contains("delete")) {
            nameCn = "Delete";
        } else {
            nameCn = "Operation";
        }

        // Reflection to get business name
        Class clazz = signature.getDeclaringType();
        Field field;
        String businessName = "";
        try {
            field = clazz.getField("BUSINESS_NAME");
            if (!StringUtils.isEmpty(field)) {
                businessName = (String) field.get(clazz);
            }
        } catch (NoSuchFieldException e) {
            LOG.error("No such Business Name");
        } catch (SecurityException e) {
            LOG.error("Get Business Name Failed", e);
        }

        // print begin
        LOG.info("------------- 【{}】{} Begin -------------", businessName, nameCn);
        LOG.info("URL: {} {}", request.getRequestURL().toString(), request.getMethod());
        LOG.info("Method: {}.{}", signature.getDeclaringTypeName(), name);
        LOG.info("Remote Address: {}", request.getRemoteAddr());

        // print args
        Object[] args = joinPoint.getArgs();
        Object[] arguments  = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }
    }

    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        Gson gson = new Gson();
        LOG.info("Return: {}", gson.toJson(result));
        LOG.info("------------- Time taken：{} ms -------------", System.currentTimeMillis() - startTime);
        return result;
    }
}
