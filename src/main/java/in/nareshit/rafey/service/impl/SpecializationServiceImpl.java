package in.nareshit.rafey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.rafey.entity.Specialization;
import in.nareshit.rafey.exception.SpecializationNotFoundException;
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
//		repository.deleteById(id);
		repository.delete(getOneSpecialization(id));
		
	}

	@Override
	public Specialization getOneSpecialization(Long id) {
//		Optional<Specialization> optional=repository.findById(id);
//		if(optional.isPresent()) {
//			return optional.get();
//		}else {
////			return null;
//			throw new SpecializationNotFoundException(id +" Not Found");
//		}
		return repository.findById(id).orElseThrow(
				()->new SpecializationNotFoundException(id+ " Not Found")
				);
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

	@Override
	public boolean isSpecCodeExistForEdit(String specCode, Long id) {
		return repository.getSpecCodeCountForEdit(specCode,id)>0;
	}
	
	
}
