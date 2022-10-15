package com.bartender.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bartender.prueba.entity.BartenderEntity;

/**
 * 
 * Interface BartenderRepository
 * 
 * Repositorio de persistencia para el Entity BartenderEntity
 * 
 * @author jhyate
 * @version 1.0.0
 * 
 */
@Repository
public interface BartenderRepository extends JpaRepository<BartenderEntity, Long> {
	@Query("SELECT b.inputArray FROM BartenderEntity b WHERE b.id = :id")
	String findByInputArray(@Param("id") Long id);	
}
