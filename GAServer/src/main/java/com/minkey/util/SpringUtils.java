package com.minkey.util;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.annotation.Annotation;
import java.util.Map;

public class SpringUtils {
	static ApplicationContext ctx = null;
	
	/**
	 * 提供给web启动时候设置上下文
	 * @param ctx
	 */
	public static void setCtx(ApplicationContext ctx) {
		SpringUtils.ctx = ctx;
	}

	public static Object getBean(String beanName){
		return ctx.getBean(beanName);
	}
	
	public static <T> T getBean(Class<T> beanClass){
		return (T)ctx.getBean(beanClass);
	}
	
	public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotation){
		return ctx.getBeansWithAnnotation(annotation);
	}
	
	public static  <T> Map<String, T> getBeansOfType(Class<T> beanClass){
		return ctx.getBeansOfType(beanClass);
	}
	
	  /**
     * @desc 向spring容器注册bean
     * @param beanName
     * @param beanDefinition
     */
    public static void registerBean(String beanName, BeanDefinition beanDefinition) {
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) ctx;
        BeanDefinitionRegistry beanDefinitonRegistry = (BeanDefinitionRegistry) configurableApplicationContext
                .getBeanFactory();
        beanDefinitonRegistry.registerBeanDefinition(beanName, beanDefinition);
    }
    
    public static void destory() {
    	 ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) ctx;
    	 configurableApplicationContext.close();
    }

}
