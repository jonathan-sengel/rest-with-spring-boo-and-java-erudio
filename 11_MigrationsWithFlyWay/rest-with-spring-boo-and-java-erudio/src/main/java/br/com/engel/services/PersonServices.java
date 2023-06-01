package br.com.engel.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.engel.data.vo.v1.PersonVO;
import br.com.engel.exceptions.ResourceNotFoundException;
import br.com.engel.mapper.DozerMapper;
import br.com.engel.model.Person;
import br.com.engel.repositories.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository repository;

	public List<PersonVO> findAll() {
		logger.info("Finding all people!");
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}

	public PersonVO findById(Long id) {
		logger.info("Finding one person!");
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return DozerMapper.parseObject(entity, PersonVO.class);
	}

	public PersonVO create(PersonVO personVO) {
		logger.info("Creating one person!");
		if (personVO.getId() != null)
			throw new ResourceNotFoundException("ID is not allowed!");

		Person entity = DozerMapper.parseObject(personVO, Person.class);
		PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}

	public PersonVO update(PersonVO personVO) {
		logger.info("Updating one person!");

		Person entity = repository.findById(personVO.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		entity.setFirstName(personVO.getFirstName());
		entity.setLastName(personVO.getLastName());
		entity.setAddress(personVO.getAddress());
		entity.setGender(personVO.getGender());

		return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
	}

	public void delete(Long id) {
		logger.info("Deleting one person!");
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
}
