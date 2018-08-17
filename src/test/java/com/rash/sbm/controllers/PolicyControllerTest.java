package com.rash.sbm.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(PolicyController.class)
public class PolicyControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void test() {
		final int policyNumber = 5;
		
//		mvc.perform(get(PolicyController.URL, policyNumber).accept(MediaType.APPLICATION_JSON_UTF8))
//			.andExpect(statu)
	}
	
}
