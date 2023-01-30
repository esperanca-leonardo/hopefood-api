package br.com.esperanca.hopefood.core.validations;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValitaionsConfig {

  @Bean
  public LocalValidatorFactoryBean localValidatorFactoryBean(
      MessageSource messageSource) {

    var bean = new LocalValidatorFactoryBean();
    bean.setValidationMessageSource(messageSource);

    return bean;
  }
}
