package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web Mvc�ݒ�t�@�C��
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	

	
//    @Autowired
//    private MessageSource messageSource;
//
//    /**
//     * Bean Validator�̃��b�Z�[�W�t�@�C����ValidationMessages����ύX����
//     * @return
//     */
//    @Bean
//    public LocalValidatorFactoryBean validator() {
//        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
//        // BeanValidator�̃��b�Z�[�W�t�@�C����ValidationMessage.properties����f�t�H���g�̃��b�Z�[�W�t�@�C���ύX
//        localValidatorFactoryBean.setValidationMessageSource(messageSource);
//        return localValidatorFactoryBean;
//    }
//
//    @Override
//    public Validator getValidator() {
//        return validator();
//    }
}