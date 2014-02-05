package com.borgsoftware.springmvc.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * The main/root class for Java-based configuration for the web interface.
 * 
 * The @EnableWebMvc annotation configures Spring MVC by registering a
 * RequestMappingHandlerMapping, a RequestMappingHandlerAdapter and an
 * ExceptionHandlerExceptionResolver to support processing requests with
 * annotated controller methods using annotations such as @RequestMapping,
 * @ExceptionHandler, and others. It also enables additional features - See the
 * section "Enabling the MVC Java Config or the MVC XML Namespace" in the Spring
 * Framework documentation for more details.
 * 
 * Annotating a configuration class with @EnableWebMvc performs the same
 * function as the <mvc:annotation-driven /> element in an XML-based
 * configuration.
 * 
 */
@Configuration
// @EnableWebMvc
// TODO Uncomment @EnableWebMvc after I remove "<annotation-driven />" from
// servlet-context-javaconfig.xml!
@ImportResource("/WEB-INF/spring/appServlet/servlet-context-javaconfig.xml")
public class WebConfig {

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