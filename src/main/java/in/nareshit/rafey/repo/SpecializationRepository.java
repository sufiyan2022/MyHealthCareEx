package in.nareshit.rafey.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.rafey.entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization, Long>{

	@Query("select count(specCode) from Specialization where specCode=:specCode")
	Integer getSpecCodeCount(String specCode);
}
