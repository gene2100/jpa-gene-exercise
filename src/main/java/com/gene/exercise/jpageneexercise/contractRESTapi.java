package com.gene.exercise.jpageneexercise;

import java.util.HashMap;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gene.exercise.jpageneexercise.entity.Contract;
import com.gene.exercise.jpageneexercise.repository.contractRepository;


@RestController
@RequestMapping("/chatrin-app/api")
public class contractRESTapi {

	@Autowired
	contractRepository contractRepository;
	
	@PostMapping("v1/contracts")
	Contract insert(@Valid @RequestBody Contract contract){
		Contract savedContract = contractRepository.save(contract);
		
		return savedContract;
	}
	
	@PutMapping("v1/contracts/{id}")
	Contract updateAll(@Valid @RequestBody Contract contract, @PathVariable UUID id) {
		Contract savedContract = contractRepository.findById(id).get();
		savedContract.update(contract);
		contractRepository.save(savedContract);
		
		return savedContract;
	}
	
	@PatchMapping("v1/contracts/{id}")
	Contract update(@RequestBody Contract contract, @PathVariable UUID id) {
		Contract savedContract = contractRepository.findById(id).get();
		savedContract.update(contract);
		contractRepository.save(savedContract);
		
		return savedContract;	
	}
	
	@GetMapping("v1/contracts")
	Object getContractPage(@RequestParam int page, @RequestParam int limit){
		
		PageRequest pageRequest = PageRequest.of(page,limit);
		
		Long totalData = contractRepository.count();
		int totalPage = (int)Math.ceil(((double)contractRepository.count()/limit));
		
		HashMap<String,Object> returnContent = new HashMap<String,Object>();
		returnContent.put("data",contractRepository.findAll(pageRequest).getContent());
		returnContent.put("totalPage",totalPage);
		returnContent.put("totalData",totalData);
		
		
		return returnContent;
		
	}
	
	@GetMapping("v1/contracts/{id}")
	Contract getById(@PathVariable UUID id) {
		return contractRepository.findById(id).get();
	}
	
	@DeleteMapping("v1/contracts/{id}")
	ResponseEntity<Object> deleteById(@PathVariable UUID id) {
		contractRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
}
