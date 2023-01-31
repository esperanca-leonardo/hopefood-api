package br.com.esperanca.hopefood.core.validators;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidatorConfig {

  @Bean
  public LocalValidatorFactoryBean localValidatorFactoryBean(
      MessageSource messageSource) {

    var bean = new LocalValidatorFactoryBean();
    bean.setValidationMessageSource(messageSource);

    return bean;
  }
}
