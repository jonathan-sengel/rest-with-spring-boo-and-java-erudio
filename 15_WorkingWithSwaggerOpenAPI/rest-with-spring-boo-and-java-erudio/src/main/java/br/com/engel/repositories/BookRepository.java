package br.com.engel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engel.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

}
