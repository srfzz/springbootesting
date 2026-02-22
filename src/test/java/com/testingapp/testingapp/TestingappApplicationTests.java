package com.testingapp.testingapp;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class TestingappApplicationTests {

	@Test
//	@Disabled
	void contextLoads() {
		log.info("contextLoads");
	}
	@BeforeEach
public void setUpBeforeClass() throws Exception {
		log.info("setUpBeforeClass");
	}
	@AfterEach
	public void setUpAfterClass() throws Exception {
		log.info("setUp aftere Class AfterClass");
	}
	@Test
	@DisplayName("This is Test for Even and Odd")
	public void testEvenOrOdd() {
		log.info("This is aprogram to chcek if the  even ad odd number is working perfectly or not");
		int number=5;
		if(number%2==0)
		{
			System.out.println("even");
		}else{
			System.out.println("odd");
		}
	}

	@Test
	public void testTwoNumber()
	{
		int a=5;
		int b=5;
		int result=addTwoNumbers(a,b);
		//Assertions.assertEquals(10,result);
		Assertions.assertThat(result).isEqualTo(10).isCloseTo(10, Offset.offset(1));
	}

	int addTwoNumbers(int a, int b) {
		return a+b;
	}

}
