package com.borgsoftware.springmvc.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.borgsoftware.springmvc.spring.web.PropertyTest;

/**
 * The main/root class for Java-based configuration for the root Spring
 * container shared by all servlets and filters.
 */
@Configuration
@ImportResource("/WEB-INF/spring/root-context-javaconfig.xml")
// This is for a *single* properties file:
// @PropertySource("classpath:propertyfile1.properties")
// This is for *multiple* properties files:
// @PropertySources({
// @PropertySource("classpath:propertyfile1.properties"),
// @PropertySource("classpath:propertyfile2.properties")
// })
public class RootConfig {

    @Value("${propfile1.propkey1}")
    private String propFile1_PropVal1;

    @Value("${propfile1.propkey2}")
    private String propFile1_PropVal2;

    @Value("${propfile2.propkey1}")
    private String propFile2_PropVal1;

    @Value("${propfile2.propkey2}")
    private String propFile2_PropVal2;

    /**
     * This bean must be declared if any beans associated with this Spring
     * container use {@literal @}Value notation to inject property values stored
     * in a property file specified with a {@literal @}PropertySource annotation
     * above. The {@literal @}PropertySource annotation may appear in this
     * configuration class or in another class processed by this container.
     * 
     * Note that each Spring container needs its own
     * PropertySourcesPlaceholderConfigurer bean if {@literal @}Value is used
     * with beans/classes defined in that container. For example, we need this
     * PropertySourcesPlaceholderConfigurerbean if beans created by this
     * container use {@literal @}Value, but WeConfig.java *also* needs its own
     * PropertySourcesPlaceholderConfigurer bean for beans/classes that use
     * {@literal @}Value that are defined in that container, e.g., MVC
     * controller classes/beans.
     * 
     * @return
     */
    // @Bean
    // public static PropertySourcesPlaceholderConfigurer
    // propertySourcesPlaceholderConfigurer() {
    // return new PropertySourcesPlaceholderConfigurer();
    // }

    /**
     * This is an alternate way to set up a PropertySourcesPlaceholderConfigurer
     * bean, but here we specify the property files in the bean definition and
     * not in one or more @PropertySource entries above.
     * 
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer myPropertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
        Resource[] resourceLocations = new Resource[] {
                new ClassPathResource("propertyfile1.properties"),
                new ClassPathResource("propertyfile2.properties"),
        };
        p.setLocations(resourceLocations);
        return p;
    }

    /* TEST INJECTING PROPERTY VALUES HERE AS WELL AS IN HomeController.java */
    @Bean
    public PropertyTest propertyTest() {
        final PropertyTest p = new PropertyTest();
        p.setPropFile1_PropVal1(this.propFile1_PropVal1);
        p.setPropFile1_PropVal2(this.propFile1_PropVal2);
        p.setPropFile2_PropVal1(this.propFile2_PropVal1);
        p.setPropFile2_PropVal2(this.propFile2_PropVal2);
        return p;
    }

}
