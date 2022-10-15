package com.bartender.prueba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Clase BartenderEntity
 * 
 * Clase asociada a la tabla arrays de la base de datos bartender
 * 
 * @author jhyate
 * @version 1.0.0
 * 
 */
@Data
@Entity
@Table(name = "arrays")
public class BartenderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "input_array")
	private String inputArray;
}
