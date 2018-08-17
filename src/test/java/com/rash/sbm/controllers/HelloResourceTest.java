package com.rash.sbm.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.rash.sbm.services.HelloService;

@RunWith(SpringJUnit4ClassRunner.class)
public class HelloResourceTest {

	private MockMvc mockMvc;
	
	@Mock
	private HelloService helloService;
	
	@InjectMocks //This annotation will inject all the above Mocks into the constructor of the "HelloResource" class
	private HelloResource helloResource;
	
	@Before
	public void setUp() throws Exception {
		//Create an instance of the MockMvc object
		mockMvc = MockMvcBuilders.standaloneSetup(helloResource).build();
	}
	
	@Test
	public void testHelloWorld() throws Exception {
		
		//Test with Mockito
		Mockito.when(helloService.getHello()).thenReturn("Hello World from HelloService!");
		
		//Test with MockMvc
		mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Hello World from HelloService!"));
				//.andExpect(MockMvcResultMatchers.content().string("Hello World!"));
		
		Mockito.verify(helloService).getHello();	//Verify is always the last stap => after a 'Mockito.when' 
													//and a 'Mock' like 'mockMvc.perform'  
	}
	
	@Test
	public void testHelloWorldJson() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/hello/json").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Greetings")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.value", Matchers.is("HELLOOOOO!")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(2))); //The json string has 2 variables
	}

    @Test
    public void testPost() throws Exception {
    	//Provide the title and string variables
        String json = "{\n" +
                "  \"title\": \"Greetings\",\n" +
                "  \"value\": \"Hello World Post!\"\n" +
                "}";
        mockMvc.perform(post("/hello/post")					//MockMvcRequestBuilders can be removed due to static imports (above)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())					//MockMvcResultMatchers can be removed due to static imports (above)
                .andExpect(jsonPath("$.title", Matchers.is("Greetings")))
                .andExpect(jsonPath("$.value", Matchers.is("Hello World Post!")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }
	
}
