package it.corso.mvc.controller.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.corso.mvc.dao.IBookDao;
import it.corso.mvc.model.book.Book;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private IBookDao bookDao;
	private String[] array = {"book", "springbook", "hello"};

	@RequestMapping(value="print",method = RequestMethod.GET)
	public String printBook() {
		return "book";
	}
	
	@RequestMapping(value="get",method = RequestMethod.GET)
	public ModelAndView getBook() {
		Book b = bookDao.getBook("1234");
//		Book b = new Book();
//		b.setIsbn("1234");
//		b.setTitle("Spring book");
		ModelAndView modelAndView = new ModelAndView("springbook", "book", b);
		return modelAndView;
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public String getBookArray(@PathVariable int id) {
		return array[id];
	}
	
}
