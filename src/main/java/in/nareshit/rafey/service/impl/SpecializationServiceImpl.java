package in.nareshit.rafey.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.rafey.entity.Specialization;
import in.nareshit.rafey.repo.SpecializationRepository;
import in.nareshit.rafey.service.ISpecializationService;

@Service
public class SpecializationServiceImpl implements ISpecializationService{

	@Autowired
	private SpecializationRepository repository;
	
	@Override
	public Long saveSpecialization(Specialization spec) {
		return repository.save(spec).getId();
	}

	@Override
	public List<Specialization> getAllSpecializations() {
		return repository.findAll();
	}

	@Override
	public void removeSpecialization(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public Specialization getOneSpecialization(Long id) {
		Optional<Specialization> optional=repository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}

	@Override
	public void updateSpecialization(Specialization spec) {
		repository.save(spec);
		
	}
	
	@Override
	public boolean isSpecCodeExist(String specCode) {
		Integer count=repository.getSpecCodeCount(specCode);
		boolean exist=count>0?true:false;
		return exist;
		
//		return repository.getSpecCodeCount(specCode)>0;
	}

}
