package com.gene.exercise.jpageneexercise;

import java.util.HashMap;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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
import com.gene.exercise.jpageneexercise.repository.ContractRepository;
import com.gene.exercise.jpageneexercise.service.ContractService;


@RestController
@RequestMapping("/api/v1")
public class ContractRESTapi {

	@Autowired
	ContractService contractService;
	
	@PostMapping("contracts")
	ResponseEntity<Object> insert(@Valid @RequestBody Contract contract){
		return new ResponseEntity<Object>(contractService.AddCreated(contract),HttpStatus.OK);
	}
	
	@PutMapping("contracts/{id}")
	ResponseEntity<Object> updateAll(@Valid @RequestBody Contract contract, @PathVariable UUID id) {
		return new ResponseEntity<Object>(contractService.Update(contract, id), HttpStatus.OK);
	}
	
	@PatchMapping("contracts/{id}")
	ResponseEntity<Object> update(@RequestBody Contract contract, @PathVariable UUID id) {
		if(contract.getFirstname() == null && contract.getLastname() == null && contract.getGender() == null && contract.getSubmitedChannel() == null ) {
			return new ResponseEntity<Object>("Message: Body no Content or Variable name wrong", HttpStatus.BAD_REQUEST);
		}
		if(contract.getFirstname() != null){
			if(contract.getFirstname().length() < 4) {
				return new ResponseEntity<Object>("Message: Firstname must have length at least 4", HttpStatus.BAD_REQUEST);
			}
		}
		if(contract.getLastname() != null){
			if(contract.getLastname().length() < 4) {
				return new ResponseEntity<Object>("Message: Lastname must have length at least 4", HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<Object>(contractService.Update(contract, id), HttpStatus.OK);
	}
	
	@GetMapping("contracts")
	ResponseEntity<Object> getContractPage(@RequestParam int page, @RequestParam int limit){		
		return new ResponseEntity<Object>(contractService.GetPage(page, limit), HttpStatus.OK);
		
	}
	
	@GetMapping("contracts/{id}")
	ResponseEntity<Object> getById(@PathVariable UUID id) {
		return  new ResponseEntity<Object>(contractService.GetById(id), HttpStatus.OK);
	}
	
	@DeleteMapping("contracts/{id}")
	ResponseEntity<Object> deleteById(@PathVariable UUID id) {
		contractService.Delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
