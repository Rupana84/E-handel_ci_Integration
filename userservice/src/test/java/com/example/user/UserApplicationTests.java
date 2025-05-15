	package com.example.user;

	import org.junit.jupiter.api.Test;
	import org.springframework.beans.factory.annotation.Value;
	import org.springframework.boot.test.context.SpringBootTest;
	import org.springframework.test.context.ActiveProfiles;

	@ActiveProfiles("test")
	@SpringBootTest
	class UserApplicationTests {


		//test
	    private String productServiceUrl;

		@Test
		void contextLoads() {
		}

	}
