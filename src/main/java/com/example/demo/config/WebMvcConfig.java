package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web Mvc設定ファイル
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	

	
//    @Autowired
//    private MessageSource messageSource;
//
//    /**
//     * Bean ValidatorのメッセージファイルをValidationMessagesから変更する
//     * @return
//     */
//    @Bean
//    public LocalValidatorFactoryBean validator() {
//        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
//        // BeanValidatorのメッセージファイルをValidationMessage.propertiesからデフォルトのメッセージファイル変更
//        localValidatorFactoryBean.setValidationMessageSource(messageSource);
//        return localValidatorFactoryBean;
//    }
//
//    @Override
//    public Validator getValidator() {
//        return validator();
//    }
}