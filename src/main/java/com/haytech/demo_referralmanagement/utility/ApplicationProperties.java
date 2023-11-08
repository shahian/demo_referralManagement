package com.haytech.demo_referralmanagement.utility;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

@Configuration
@PropertySources({

        @PropertySource(
                value = "classpath:/messages-application.properties",
                encoding = "UTF-8",
                ignoreResourceNotFound = true
        )
})
public class ApplicationProperties {

    private static Environment environment;
    @Autowired
    ApplicationProperties(Environment environment) {
        ApplicationProperties.environment = environment;
    }
    public static String getProperty(String name) {
        return environment.getProperty(name);
    }

    public Integer getCode(String name) {
        return Integer.parseInt(environment.getProperty(name));
    }

}
