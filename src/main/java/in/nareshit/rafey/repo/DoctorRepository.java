package in.nareshit.rafey.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.rafey.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	
}
