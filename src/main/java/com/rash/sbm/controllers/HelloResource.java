package com.rash.sbm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.rash.sbm.services.HelloService;

@RestControllerAdvice
@RequestMapping("/hello")
public class HelloResource {

	@Autowired
    private HelloService helloService;

//    //Constructor Injection
//    public HelloResource(HelloService helloService) {
//        this.helloService = helloService;
//    }
	
	@GetMapping
	public String helloWorld() {
		return helloService.getHello();
		//return "Hello World!";
	}

	@GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public InnerClassHello json() {
		return new InnerClassHello("Greetings", "HELLOOOOO!");
	}

    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE,
            	produces = MediaType.APPLICATION_JSON_VALUE)
    public InnerClassPostHello post(@RequestBody InnerClassPostHello hello) {
        return hello;
    }

	
	private class InnerClassHello {

		private String title;
		private String value;

		//Constructor
		public InnerClassHello(String title, String value) {
			this.title = title;
			this.value = value;
		}
		
		@SuppressWarnings("unused")
		public String getTitle() {
			return title;
		}

		@SuppressWarnings("unused")
		public void setTitle(String title) {
			this.title = title;
		}

		@SuppressWarnings("unused")
		public String getValue() {
			return value;
		}

		@SuppressWarnings("unused")
		public void setValue(String value) {
			this.value = value;
		}

	}

    public static class InnerClassPostHello {

        private String title;
        private String value;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public InnerClassPostHello(String title, String value) {
            this.title = title;
            this.value = value;
        }

        public InnerClassPostHello() {
        }
    }
	
}
