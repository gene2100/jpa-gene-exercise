package com.gene.exercise.jpageneexercise.service;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gene.exercise.jpageneexercise.entity.Contract;
import com.gene.exercise.jpageneexercise.repository.ContractRepository;

@Service
public class ContractService {

	@Autowired
	ContractRepository contractRepository;
	
	public Contract AddCreated(Contract contract) {
		Contract savedContract = contractRepository.save(contract);
		
		return savedContract;
	}
	
	public Contract Update(Contract contract, UUID id) {
		Contract savedContract = contractRepository.findById(id).get();
		if(contract.getFirstname() != null) {
			savedContract.setFirstname(contract.getFirstname());
		}
		if(contract.getLastname() != null) {
			savedContract.setLastname(contract.getLastname());
		}
		if(contract.getGender() != null) {
			savedContract.setGender(contract.getGender());
		}
		if(contract.getSubmitedChannel() != null) {
			savedContract.setSubmitedChannel(contract.getSubmitedChannel());
		}
		contractRepository.save(savedContract);
		
		return savedContract;
	}
	
	public Object GetPage(int page, int limit) {
		PageRequest pageRequest = PageRequest.of(page,limit);
		
		Long totalData = contractRepository.count();
		int totalPage = (int)Math.ceil(((double)contractRepository.count()/limit));
		
		HashMap<String,Object> returnContent = new HashMap<String,Object>();
		returnContent.put("data",contractRepository.findAll(pageRequest).getContent());
		returnContent.put("totalPage",totalPage);
		returnContent.put("totalData",totalData);
		
		return returnContent;
	}
	
	public Contract GetById(UUID id) {
		return contractRepository.findById(id).get();
	}
	
	public void Delete(UUID id) {
		contractRepository.deleteById(id);
	}
	
	
}
