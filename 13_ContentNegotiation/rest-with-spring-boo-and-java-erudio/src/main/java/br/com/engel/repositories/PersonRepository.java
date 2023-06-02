package br.com.engel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.engel.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {}
