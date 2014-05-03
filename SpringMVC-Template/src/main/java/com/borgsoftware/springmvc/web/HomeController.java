package com.borgsoftware.springmvc.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.borgsoftware.springmvc.spring.web.PropertyTest;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
    @Inject
    private PropertyTest propertyTest;

    @Value("${propfile1.propkey1}")
    private String injectedInHomeController;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model) {
        String greetings = "Greetings, Spring MVC, you hoser!";
        model.addAttribute("message", greetings);

        model.addAttribute("PropFile1_PropVal1", propertyTest.getPropFile1_PropVal1());
        model.addAttribute("PropFile1_PropVal2", propertyTest.getPropFile1_PropVal2());
        model.addAttribute("PropFile2_PropVal1", propertyTest.getPropFile2_PropVal1());
        model.addAttribute("PropFile2_PropVal2", propertyTest.getPropFile2_PropVal2());
        model.addAttribute("injectedInHomeControllerModelAttr", this.injectedInHomeController);

        return "test";
    }
	
}
