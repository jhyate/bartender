package com.bartender.prueba.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bartender.prueba.dto.ResponseDto;
import com.bartender.prueba.service.BartenderService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BartenderControllerTest {

	@Autowired
	private MockMvc mockServer;

	@MockBean
	BartenderService bartenderService;

	private String getRootUrl() {
		return "/prueba/iteration";
	}

	@Test
	public void testBartenderController_ok() throws Exception {
		ResponseDto respose = getResponseDto();
		when(bartenderService.operationBartender(3, 1l))
		.thenReturn(respose);

		mockServer.perform(post(getRootUrl())
				.queryParam("iteration", "3")
				.queryParam("arrayId", "1"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testBartenderController_badRequest() throws Exception {
		mockServer.perform(post(getRootUrl())
				.queryParam("iteration", "3"))
		.andExpect(status().isBadRequest());
	}
	
	private ResponseDto getResponseDto() {
		ResponseDto respose = new ResponseDto();
		List<Integer> number = new ArrayList<>();
		number.add(5);
		number.add(3);
		number.add(6);
		number.add(4);
		number.add(2);
		number.add(7);
		respose.setStatus(HttpStatus.OK.value());
		respose.setReason(HttpStatus.OK.getReasonPhrase());
		respose.setData(number);

		return respose;
	}
}
