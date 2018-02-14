package it.corso.mvc.dao;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Repository;

import it.corso.mvc.model.book.Book;

@Repository
public class BookDao implements IBookDao {

	@Override
	public Book getBook(String isbn) {
		String[] titoli = {"Spring book", "Another Spring book", "Titolo bello", "Titolo bellissimo", "Titolo figo", "Titolo migliore di sempre"};
		
		int randomNum = ThreadLocalRandom.current().nextInt(0, titoli.length);
		
		isbn += randomNum;
		
		Book b = new Book();
		b.setIsbn(isbn);
		b.setTitle(titoli[randomNum]);
		return b;
	}

}
