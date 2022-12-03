package com.csy.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextUtil.applicationContext == null){
            SpringContextUtil.applicationContext = applicationContext;
        }
    }

    public static <T> T getBean(Class<T> beanType){
        return applicationContext.getBean(beanType);
    }

    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }
}
