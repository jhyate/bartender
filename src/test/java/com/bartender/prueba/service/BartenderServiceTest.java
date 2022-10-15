package com.bartender.prueba.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;

import com.bartender.prueba.dto.ResponseDto;
import com.bartender.prueba.repository.BartenderRepository;
import com.bartender.prueba.service.impl.BartenderServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BartenderServiceTest {
	
	@InjectMocks
	@Qualifier("bartenderServiceImpl")
	private BartenderServiceImpl bartenderService;
	
	@Mock
	private BartenderRepository bartenderRepository;
	
	public static final String STATUS = "status";
	
	@After
	public void endTest() {
		reset(bartenderRepository);
	}
	 
	@Test
	public void testBartenderService_ok() {
		Integer iteration = 3;
		Long arrayId = 1l;
		when(bartenderRepository.findByInputArray(arrayId)).thenReturn("2,3,4,5,6,7");
		ResponseDto response = bartenderService.operationBartender(iteration, arrayId);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		Assertions.assertThat(response).isNotNull().hasFieldOrPropertyWithValue(STATUS, HttpStatus.OK.value());
	}
	
	@Test
	public void testBartenderService_errorArrayIdInvalid() {
		Integer iteration = 3;
		Long arrayId = 0l;
		ResponseDto response = bartenderService.operationBartender(iteration, arrayId);
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		Assertions.assertThat(response).isNotNull().hasFieldOrPropertyWithValue(STATUS, HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void testBartenderService_errorIterationInvalid() {
		Integer iteration = 0;
		Long arrayId = 1l;
		ResponseDto response = bartenderService.operationBartender(iteration, arrayId);
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		Assertions.assertThat(response).isNotNull().hasFieldOrPropertyWithValue(STATUS, HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void testBartenderService_errorServiceInternal() {
		Integer iteration = 3;
		Long arrayId = 1l;
		when(bartenderRepository.findByInputArray(arrayId)).thenReturn("");
		ResponseDto response = bartenderService.operationBartender(iteration, arrayId);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
		Assertions.assertThat(response).isNotNull().hasFieldOrPropertyWithValue(STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
}
