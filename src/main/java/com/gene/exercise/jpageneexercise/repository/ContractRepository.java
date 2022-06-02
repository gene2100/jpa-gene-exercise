package com.gene.exercise.jpageneexercise.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gene.exercise.jpageneexercise.entity.Contract;

public interface ContractRepository extends JpaRepository<Contract, UUID> {

}
