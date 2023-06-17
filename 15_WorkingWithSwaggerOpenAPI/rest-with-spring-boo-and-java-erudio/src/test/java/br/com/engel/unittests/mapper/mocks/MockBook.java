package br.com.engel.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.engel.data.vo.v1.BookVO;
import br.com.engel.model.Book;

public class MockBook {
	
	public BookVO mockVO() {
		return mockVO(2);
	}
	
	public Book mockEntity() {
		return mockEntity(2);
	}
	
	public List<BookVO> mockVOList(){
		List<BookVO> books = new ArrayList<>();
		for (int i = 0; i <14; i++) {
			books.add(mockVO(i));
		}
		return books;
	}
	
	public List<Book> mockEntityList(){
		List<Book> books = new ArrayList<>();
		for (int i = 0; i <14; i++) {
			books.add(mockEntity(i));
		}
		return books;
	}
	

	public BookVO mockVO(Integer number) {
		BookVO book = new BookVO();
		book.setTitle("Title Test" + number);
		book.setAuthor("Author test" + number);
		book.setLaunchDate(new Date());
		book.setPrice(number * 37.50);
		book.setKey(number);
		return book;
	}

	public Book mockEntity(Integer number) {
		Book book = new Book();
		book.setId(number);
		book.setTitle("Title Test" + number);
		book.setAuthor("Author test" + number);
		book.setLaunchDate(new Date());
		book.setPrice(number * 37.50);

		return book;
	}
}
