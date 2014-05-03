package com.borgsoftware.springmvc.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
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
// This @PropertySource annotation allows us to use @Value in
// HomeController.java to access property values defined in the properties file
// specified here:
@PropertySource("classpath:propertyfile1.properties")
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * This bean must be declared if any beans associated with this Spring
     * container use {@literal @}Value notation to inject property values stored
     * in a property file specified with a {@literal @}PropertySource
     * annotation. The {@literal @}PropertySource annotation may appear in this
     * configuration class or in another class processed by this container, such
     * as an MVC controller class.
     * 
     * Note that each Spring container needs its own
     * PropertySourcesPlaceholderConfigurer bean if {@literal @}Value is used
     * with beans/classes defined in that container. For example, we need this
     * PropertySourcesPlaceholderConfigurerbean if Spring MVC controllers will
     * use {@literal @}Value, but RootConfig.java *also* needs its own
     * PropertySourcesPlaceholderConfigurer bean for beans/classes that use
     * {@literal @}Value that are defined in that container.
     * 
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

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