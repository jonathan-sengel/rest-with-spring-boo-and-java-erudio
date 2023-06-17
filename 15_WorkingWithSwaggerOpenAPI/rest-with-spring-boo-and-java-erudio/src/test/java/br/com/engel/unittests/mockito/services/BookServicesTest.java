package br.com.engel.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.engel.data.vo.v1.BookVO;
import br.com.engel.exceptions.RequiredObjectIsNullException;
import br.com.engel.model.Book;
import br.com.engel.repositories.BookRepository;
import br.com.engel.services.BookServices;
import br.com.engel.unittests.mapper.mocks.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServicesTest {
	
	MockBook input;
	
	@InjectMocks
	private BookServices service;
	
	@Mock
	BookRepository repository;
	
	@BeforeEach
	void setUpMocks() throws Exception{
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testFindById() {
		Book entity = input.mockEntity(1);
		entity.setId(1);
		
		when(repository.findById(1)).thenReturn(Optional.of(entity));
		BookVO result = service.findById(1);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Title Test1", result.getTitle());
		assertEquals("Author test1", result.getAuthor());
		assertNotNull(result.getLaunchDate());
		assertEquals(37.50, result.getPrice());
	}
	
	@Test
	void testCreate() throws CloneNotSupportedException{
		Book entity = input.mockEntity(1);
		entity.setId(null);
		Book persisted = entity.clone();
		persisted.setId(1);
		
		BookVO vo = input.mockVO(1);
		vo.setKey(null);
		
		when(repository.save(entity)).thenReturn(persisted);
		BookVO result = service.create(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Title Test1", result.getTitle());
		assertEquals("Author test1", result.getAuthor());
		assertNotNull(result.getLaunchDate());
		assertEquals(37.50, result.getPrice());
	}

	@Test
	void testCreateWithNullBook() throws CloneNotSupportedException{
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testUpdate() throws CloneNotSupportedException {
		Book entity = input.mockEntity(1);
		entity.setId(1);
		Book persisted = entity.clone();
		persisted.setId(1);
		
		BookVO vo = input.mockVO(1);
		vo.setKey(1);
		
		when(repository.findById(1)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		
		BookVO result = service.update(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Title Test1", result.getTitle());
		assertEquals("Author test1", result.getAuthor());
		assertNotNull(result.getLaunchDate());
		assertEquals(37.50, result.getPrice());
	}
	
	@Test
	void testUpdateWithNullBook() throws CloneNotSupportedException {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testDelete() {
		Book entity = input.mockEntity(1);
		entity.setId(1);
		
		when(repository.findById(1)).thenReturn(Optional.of(entity));
		service.delete(1);
	}
	
	@Test
	void testFindAll() {
		List<Book> entites = input.mockEntityList();
		
		when(repository.findAll()).thenReturn(entites);
		List<BookVO> books = service.findAll();
		
		assertNotNull(books);
		assertEquals(14, books.size());
		
		for(int i = 0; i < books.size(); i++) {
			assertNotNull(books.get(i));
			assertNotNull(books.get(i).getKey());
			assertNotNull(books.get(i).getLinks());
			assertTrue(books.get(i).toString().contains("links: [</api/book/v1/" + i + ">;rel=\"self\"]"));
			assertEquals("Title Test" + i, books.get(i).getTitle());
			assertEquals("Author test" + i, books.get(i).getAuthor());
			assertNotNull(books.get(i).getLaunchDate());
			assertEquals(i * 37.50, books.get(i).getPrice());
		}
	}
}

