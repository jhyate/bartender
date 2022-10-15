package com.bartender.prueba.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bartender.prueba.dto.ResponseDto;
import com.bartender.prueba.repository.BartenderRepository;
import com.bartender.prueba.service.BartenderService;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase BartenderServiceImpl
 * 
 * Clase encargada de las operaciones del arreglo extraído de base de datos
 * 
 * @author jhyate
 * @version 1.0.0
 *
 */
@Slf4j
@Service
public class BartenderServiceImpl implements BartenderService {

	@Autowired
	BartenderRepository bartenderRepository;

	@Override
	public ResponseDto operationBartender(Integer iteration, Long arrayId) {
		ResponseDto response = validParameters(iteration, arrayId);

		try {
			// validacion de parametros
			if (response.getStatus() == HttpStatus.BAD_REQUEST.value()) {
				return response;
			}

			String inputArray = bartenderRepository.findByInputArray(arrayId);
			log.info("The returned array is {}", inputArray);
			List<Integer> inputList = Arrays.stream(inputArray.split(",")).map(Integer::valueOf).collect(Collectors.toList());
			List<Integer> r = getOperation(inputList, iteration);
			response.setReason(HttpStatus.OK.getReasonPhrase());
			log.info("response {}", r);
			response.setData(r);

		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setReason(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
			log.error("Error running service operationBartender", e);
		}

		return response;
	}

	/**
	 * Método encargado de validar los parámetros recibidos
	 * 
	 * @param iteration parámetro para validar si es mayor a 0.
	 * @param arrayId parámetro para validar si se encuentra dentro de la cantidad de arreglos de la BD.
	 * @return ResponseDto Objeto que devuelve el estado de respuesta.
	 * 
	 */
	private ResponseDto validParameters(Integer iteration, Long arrayId) {
		log.info("The parameters to validate are iteration {}, arrayId {}", iteration, arrayId);
		ResponseDto response = new ResponseDto();
		response.setStatus(HttpStatus.OK.value());

		if (iteration == 0) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setReason("The number of iterations is not correct, it must be greater than 0");
		} else if (arrayId == 0 || arrayId > 5) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setReason("The entered ID is not correct the ID must not be greater than 1 and less than 5");
		}

		return response;
	}

	/**
	 * Método encargado de devolver el arreglo de respuesta
	 * 
	 * @param data parámetro que tiene el arreglo retornado de BD.
	 * @param iteration parámetro que indica la cantidad de iteraciones.
	 * @return List<Integer> Lista de números que devuelve el ResponseDto.
	 * 
	 */
	private List<Integer> getOperation(List<Integer> data, Integer iteration) {
		List<Integer> p = getPrimeNumbers(iteration);
		List<Integer> r = new ArrayList<>();
		List<Integer> a = new ArrayList<>();
		for (int i = 0; i < iteration; i++) {
			for (int t = 0; t < data.size(); t ++) {
				if (data.get(t) % p.get(i) == 0) {
					log.info("this number is prime {}", data.get(t));
					r.add(0, data.get(t));
					if (!a.isEmpty() && a.contains(data.get(t))) {
						a.remove(a.indexOf(data.get(t)));
					}
					data.remove(t);
				} else {
					if (!a.contains(data.get(t))) {
						log.info("this number is not prime {}", data.get(t));
						a.add(data.get(t));
					}					
				}
			}

		}

		r.addAll(a);
		return r;
	}

	/**
	 * Método encargado de traer el arreglo de números primos
	 * según el número de iteraciones
	 * 
	 * @param iteration parámetro que indica la cantidad de iteraciones..
	 * @return List<Integer> Lista de número primos.
	 * 
	 */
	private List<Integer> getPrimeNumbers(Integer iteration) {
		int delimiter = iteration;
		int count = 0;
		List<Integer> response = new ArrayList<>();
		for (int i = 0; i <= delimiter; i++) {
			if (numberIsPrime(i)) {
				response.add(i);
				count++;
				if (count == iteration) {
					break;
				}
			} else {
				delimiter++;				
			}
			
		}
		log.info("Prime numbers generically according to the number of iteration {}", response);
		return response;
	}

	/**
	 * Método encargado de validar si el número
	 * Es primo o no.
	 * 
	 * @param n parámetro que indica el número a validar.
	 * @return boolean Con el que se valida si es o no primo.
	 */
	private boolean numberIsPrime(Integer n) {
		if (n == 0 || n == 1 || n == 4) {
			return false;
		}
		
		for (int i = 2; i < n / 2; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
