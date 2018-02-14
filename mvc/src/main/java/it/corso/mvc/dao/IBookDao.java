package it.corso.mvc.dao;

import it.corso.mvc.model.book.Book;

public interface IBookDao {

	public Book getBook(String isbn);
}
