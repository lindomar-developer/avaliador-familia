package br.com.digix.avaliadorfamilia.config;

import br.com.digix.avaliadorfamilia.helper.MensagensComum;
import br.com.digix.avaliadorfamilia.service.factory.FabricaCriterioAvaliativoServiceLocator;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Configuration
public class AppConfig {

    @Bean
    MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setCacheSeconds(3600);
        messageSource.setDefaultLocale(Locale.getDefault());
        return messageSource;
    }

    @Bean
    MensagensComum mensagensComum(MessageSource messageSource) {
        return new MensagensComum(messageSource);
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean(){
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource());
        return localValidatorFactoryBean;
    }

    @Bean
    public FactoryBean<?> factoryBean(){
        final ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(FabricaCriterioAvaliativoServiceLocator.class);
        return factoryBean;
    }
}
