package br.com.engel.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engel.controllers.BookController;
import br.com.engel.data.vo.v1.BookVO;
import br.com.engel.exceptions.RequiredObjectIsNullException;
import br.com.engel.exceptions.ResourceNotFoundException;
import br.com.engel.mapper.DozerMapper;
import br.com.engel.model.Book;
import br.com.engel.repositories.BookRepository;

@Service
public class BookServices {

	private Logger logger = Logger.getLogger(BookServices.class.getName());

	@Autowired
	BookRepository repository;

	public List<BookVO> findAll() {
		logger.info("Finding all books");
		List<BookVO> books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
		books.forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
		return books;
	}

	public BookVO findById(Integer id) {
		logger.info("Finding one book!");
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		BookVO vo = DozerMapper.parseObject(entity, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo;
	}

	public BookVO create(BookVO bookVO) {
		if (bookVO == null)
			throw new RequiredObjectIsNullException();
		
		logger.info("Creating one book!");
		if (bookVO.getKey() != null)
			throw new ResourceNotFoundException("ID is not allowed!");
		
		Book entity = DozerMapper.parseObject(bookVO, Book.class);
		BookVO vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public BookVO update(BookVO bookVO) {
		if (bookVO == null)
			throw new RequiredObjectIsNullException();
		
		Book entity = repository.findById(bookVO.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		logger.info("Updating one book!");
		entity.setAuthor(bookVO.getAuthor());
		entity.setLaunchDate(bookVO.getLaunchDate());
		entity.setPrice(bookVO.getPrice());
		entity.setTitle(bookVO.getTitle());
		
		BookVO vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public void delete(Integer id) {
		logger.info("Deleting one book!");
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}

}
