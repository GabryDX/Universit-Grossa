package it.corso.mvc.universita.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

//	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	private Logger logger = Logger.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(HttpServletRequest request, Exception ex) {
		logger.error("Generic Exception Occurred:: URL="+request.getRequestURL(), ex);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", ex);
		modelAndView.addObject("url", request.getRequestURL());
		
		modelAndView.setViewName("exception");
		return modelAndView;
	}
}
