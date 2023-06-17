package br.com.engel.unittests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.engel.data.vo.v1.BookVO;
import br.com.engel.data.vo.v1.PersonVO;
import br.com.engel.mapper.DozerMapper;
import br.com.engel.model.Book;
import br.com.engel.model.Person;
import br.com.engel.unittests.mapper.mocks.MockBook;
import br.com.engel.unittests.mapper.mocks.MockPerson;

public class DozerConverterTest {
    
    MockPerson inputPersonObj;
    MockBook inputBookObj;	

    @BeforeEach
    public void setUp() {
    	inputPersonObj = new MockPerson();
    	inputBookObj = new MockBook();
    }

    @Test
    public void parsePersonEntityToVOTest() {
        PersonVO output = DozerMapper.parseObject(inputPersonObj.mockEntity(), PersonVO.class);
        assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("First Name Test0", output.getFirstName());
        assertEquals("Last Name Test0", output.getLastName());
        assertEquals("Addres Test0", output.getAddress());
        assertEquals("Male", output.getGender());
    }
    
    @Test
    public void parseBookEntityToVOTest() {
    	BookVO output = DozerMapper.parseObject(inputBookObj.mockEntity(), BookVO.class );
    	assertEquals(2, output.getKey());
    	assertEquals("Title Test2", output.getTitle());
    	assertEquals("Author test2", output.getAuthor());
    	assertEquals( new Date(2023,9,20), output.getLaunchDate());
    	assertEquals(75.00, output.getPrice());
    }
    
    @Test
    public void parseBookVOToEntityTest() {
    	Book output = DozerMapper.parseObject(inputBookObj.mockVO(), Book.class );
    	assertEquals(2, output.getId());
    	assertEquals("Title Test2", output.getTitle());
    	assertEquals("Author test2", output.getAuthor());
    	assertEquals( new Date(2023,9,20), output.getLaunchDate());
    	assertEquals(75.00, output.getPrice());
    }
    
    @Test
    public void parseBookEntityListToVOListTest() {
    	List<BookVO> outputList = DozerMapper.parseListObjects(inputBookObj.mockEntityList(), BookVO.class);
    	BookVO outputZero = outputList.get(0);
    	assertEquals(0, outputZero.getKey());
    	assertEquals("Title Test0", outputZero.getTitle());
    	assertEquals("Author test0", outputZero.getAuthor());
    	assertEquals( new Date(2023,9,20), outputZero.getLaunchDate());
    	assertEquals(0.00, outputZero.getPrice());
    	
    	BookVO outputSeven = outputList.get(7);
    	assertEquals(7, outputSeven.getKey());
    	assertEquals("Title Test7", outputSeven.getTitle());
    	assertEquals("Author test7", outputSeven.getAuthor());
    	assertEquals( new Date(1993,9,20), outputSeven.getLaunchDate());
    	assertEquals(262.50, outputSeven.getPrice());
    	
    	BookVO outputTwelve = outputList.get(12);
    	assertEquals(12, outputTwelve.getKey());
    	assertEquals("Title Test12", outputTwelve.getTitle());
    	assertEquals("Author test12", outputTwelve.getAuthor());
    	assertEquals( new Date(2023,9,20), outputTwelve.getLaunchDate());
    	assertEquals(450.00, outputTwelve.getPrice());
    }
    
    @Test
    public void parseBookVOListToEntityListTest() {
    	List<Book> outputList = DozerMapper.parseListObjects(inputBookObj.mockVOList(), Book.class);
    	Book outputZero = outputList.get(0);
    	assertEquals(0, outputZero.getId());
    	assertEquals("Title Test0", outputZero.getTitle());
    	assertEquals("Author test0", outputZero.getAuthor());
    	assertEquals( new Date(2023,9,20), outputZero.getLaunchDate());
    	assertEquals(0.00, outputZero.getPrice());
    	
    	Book outputSeven = outputList.get(7);
    	assertEquals(7, outputSeven.getId());
    	assertEquals("Title Test7", outputSeven.getTitle());
    	assertEquals("Author test7", outputSeven.getAuthor());
    	assertEquals( new Date(1993,9,20), outputSeven.getLaunchDate());
    	assertEquals(262.50, outputSeven.getPrice());
    	
    	Book outputTwelve = outputList.get(12);
    	assertEquals(12, outputTwelve.getId());
    	assertEquals("Title Test12", outputTwelve.getTitle());
    	assertEquals("Author test12", outputTwelve.getAuthor());
    	assertEquals( new Date(2023,9,20), outputTwelve.getLaunchDate());
    	assertEquals(450.00, outputTwelve.getPrice());
    }

    @Test
    public void parsePersonEntityListToVOListTest() {
        List<PersonVO> outputList = DozerMapper.parseListObjects(inputPersonObj.mockEntityList(), PersonVO.class);
        PersonVO outputZero = outputList.get(0);
        
        assertEquals(Long.valueOf(0L), outputZero.getKey());
        assertEquals("First Name Test0", outputZero.getFirstName());
        assertEquals("Last Name Test0", outputZero.getLastName());
        assertEquals("Addres Test0", outputZero.getAddress());
        assertEquals("Male", outputZero.getGender());
        
        PersonVO outputSeven = outputList.get(7);
        
        assertEquals(Long.valueOf(7L), outputSeven.getKey());
        assertEquals("First Name Test7", outputSeven.getFirstName());
        assertEquals("Last Name Test7", outputSeven.getLastName());
        assertEquals("Addres Test7", outputSeven.getAddress());
        assertEquals("Female", outputSeven.getGender());
        
        PersonVO outputTwelve = outputList.get(12);
        
        assertEquals(Long.valueOf(12L), outputTwelve.getKey());
        assertEquals("First Name Test12", outputTwelve.getFirstName());
        assertEquals("Last Name Test12", outputTwelve.getLastName());
        assertEquals("Addres Test12", outputTwelve.getAddress());
        assertEquals("Male", outputTwelve.getGender());
    }

    @Test
    public void parsePersonVOToEntityTest() {
        Person output = DozerMapper.parseObject(inputPersonObj.mockVO(), Person.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("First Name Test0", output.getFirstName());
        assertEquals("Last Name Test0", output.getLastName());
        assertEquals("Addres Test0", output.getAddress());
        assertEquals("Male", output.getGender());
    }

    @Test
    public void parsePersonVOListToEntityListTest() {
        List<Person> outputList = DozerMapper.parseListObjects(inputPersonObj.mockVOList(), Person.class);
        Person outputZero = outputList.get(0);
        
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("First Name Test0", outputZero.getFirstName());
        assertEquals("Last Name Test0", outputZero.getLastName());
        assertEquals("Addres Test0", outputZero.getAddress());
        assertEquals("Male", outputZero.getGender());
        
        Person outputSeven = outputList.get(7);
        
        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("First Name Test7", outputSeven.getFirstName());
        assertEquals("Last Name Test7", outputSeven.getLastName());
        assertEquals("Addres Test7", outputSeven.getAddress());
        assertEquals("Female", outputSeven.getGender());
        
        Person outputTwelve = outputList.get(12);
        
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("First Name Test12", outputTwelve.getFirstName());
        assertEquals("Last Name Test12", outputTwelve.getLastName());
        assertEquals("Addres Test12", outputTwelve.getAddress());
        assertEquals("Male", outputTwelve.getGender());
    }
}
