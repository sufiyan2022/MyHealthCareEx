package in.nareshit.rafey.specialization;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import in.nareshit.rafey.entity.Specialization;
import in.nareshit.rafey.repo.SpecializationRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
public class SpecializatonRepositoryTest {

	@Autowired
	private SpecializationRepository repository;
	
	/**
	 *  Test save operation
	 */
	
	@Test
	@Order(1)
	public void testSpecCreate() {
		Specialization spec=new Specialization(null, "CRDLS", "Cardiologists"
				, "They’re experts on the heart and blood vessels.");
		spec=repository.save(spec);
		assertNotNull(spec.getId(),"Spec is not created!!!");
		
	}
	
	/*
	 * Test display all operation
	 */
	
	@Test
	@Order(2)
	public void testSpecFetchAll() {
		List<Specialization> list= repository.findAll();
		assertNotNull(list);
		if(list.isEmpty()) {
			fail("No data Exists in Database");
		}
	}
}
