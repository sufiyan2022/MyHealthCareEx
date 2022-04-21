package in.nareshit.rafey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.rafey.entity.Doctor;
import in.nareshit.rafey.exception.DoctorNotFoundException;
import in.nareshit.rafey.repo.DoctorRepository;
import in.nareshit.rafey.service.IDoctorService;

@Service
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private DoctorRepository repository;
	
	@Override
	public Long saveDoctor(Doctor doc) {
		return repository.save(doc).getId();
	}

	@Override
	public List<Doctor> getAllDoctors() {
		return repository.findAll();
	}

	@Override
	public void removeDoctor(Long id) {
		repository.delete(getOneDoctor(id));

	}

	@Override
	public Doctor getOneDoctor(Long id) {
		return repository.findById(id).orElseThrow(
				()->new DoctorNotFoundException(id+ ", not Exist."));
	}

	@Override
	public void updateDoctor(Doctor doc) {
		if(repository.existsById(doc.getId()))
		repository.save(doc);
		else 
			throw new DoctorNotFoundException(doc.getId()+ ", not Exist.");

	}

}
