package com.bartender.prueba.dto;

import java.util.List;

import lombok.Data;

/**
 * Clase ResponseDto
 * 
 * Objeto de transferencia de datos para el mapeo de la respuesta
 * 
 * @author Jose Yate
 * @version 1.0.0
 * 
 */
@Data
public class ResponseDto {
	private Integer status;
	private String reason;
	private List<Integer> data;
}
