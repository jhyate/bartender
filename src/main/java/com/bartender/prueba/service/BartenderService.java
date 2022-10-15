package com.bartender.prueba.service;

import com.bartender.prueba.dto.ResponseDto;

public interface BartenderService {
	
	/**
	 * Método encargado de inicializar el flujo
	 * 
	 * @param iteration parámetro que indica la cantidad de iteraciones.
	 * @param arrayId parámetro que indica el ID del arreglo de BD.
	 * @return ResponseDto Objeto de respuesta.
	 * 
	 */
	ResponseDto operationBartender(Integer iteration, Long arrayId);
}
