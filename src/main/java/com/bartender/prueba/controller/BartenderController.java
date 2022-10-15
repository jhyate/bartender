package com.bartender.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartender.prueba.dto.ResponseDto;
import com.bartender.prueba.service.BartenderService;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase BartenderController
 * 
 * API para la funcionalidad de la aplicación
 * 
 * @author jhyate
 * @version 1.0.0
 *
 */
@RestController
@Slf4j
@RequestMapping("/prueba")
public class BartenderController {
	
	@Autowired
	BartenderService bartenderService;
	
	/**
	 * 
	 * Servicio encargado de realizar las operaciones
	 * 
	 * @param iteration parametro que indica la cantidad de iteraciones.
	 * @param arrayId parametro que indica el id del arreglo de BD.
	 * @return ResponseDto objeto de respuesta.
	 */
	@PostMapping("/iteration")
	public ResponseDto operationBartender(@RequestParam Integer iteration, @RequestParam Long arrayId) {
		log.info("Execute service operationBartender interation {} to array {}", iteration, arrayId);
		return bartenderService.operationBartender(iteration, arrayId);
	}
}
