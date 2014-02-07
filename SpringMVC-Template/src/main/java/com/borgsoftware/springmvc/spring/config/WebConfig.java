package com.borgsoftware.springmvc.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * The main/root class for Java-based configuration for the web interface.
 * 
 * <p>
 * The @EnableWebMvc annotation configures Spring MVC by registering a
 * RequestMappingHandlerMapping, a RequestMappingHandlerAdapter and an
 * ExceptionHandlerExceptionResolver to support processing requests with
 * annotated controller methods using annotations such as
 * {@code @RequestMapping}, {@code @ExceptionHandler}, and others. It also
 * enables additional features - See the section
 * "Enabling the MVC Java Config or the MVC XML Namespace" in the Spring
 * Framework documentation for more details.
 * 
 * Annotating a configuration class with @EnableWebMvc performs the same
 * function as the <mvc:annotation-driven /> element in an XML-based
 * configuration.
 * 
 * The configuration file servlet-context-javaconfig.xml is imported so that we
 * we have a place to put XML-based configuration, in case we aren't able (or we
 * cannot figure out how) to perform the configuration in Java.
 * 
 */
@Configuration
@EnableWebMvc
@ImportResource("/WEB-INF/spring/appServlet/servlet-context-javaconfig.xml")
public class WebConfig extends WebMvcConfigurerAdapter {

    // 31556926 = 1-year cache period.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/"); // .setCachePeriod(31556926);
    }

    // Resolves views selected for rendering by @Controllers to .jsp resources
    // in the /WEB-INF/views directory.
    //
    // Note: When chaining ViewResolvers, the Javadoc states that an
    // InternalResourceViewResolver always needs to be last, as it will attempt
    // to resolve any view name, no matter whether the underlying resource
    // actually exists.
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver()
    {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

}

// Instead of annotating with @EnableWebMvc a configuration class that extends
// extends WebMvcConfigurerAdapter in order to customize the MVC configuration,
// as is done above, it is possible to omit the @EnableWebMvc annotation, but
// extend WebMvcConfigurationSupport instead, as is done here:
//
//@Configuration
//public class WebMvcConfig extends WebMvcConfigurationSupport {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry reg) {
//        // Equivalent to <mvc:interceptors>
//    }
//
//    @Override
//    @Bean
//    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
//        // Create or let "super" create and customize
//        // RequestMappingHandlerAdapter ...
//        RequestMappingHandlerAdapter handlerAdapter =
//                super.requestMappingHandlerAdapter();
//        return handlerAdapter;
//    }
//
//    @Override
//    @Bean
//    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
//        RequestMappingHandlerMapping handlerMapping =
//                super.requestMappingHandlerMapping();
//        handlerMapping.setUseSuffixPatternMatch(false);
//        handlerMapping.setUseTrailingSlashMatch(false);
//        return handlerMapping;
//    }
//
//}