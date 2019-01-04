package com.paul.modulus.dao;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.paul.modulus.svc.ModulusCheckSvc;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ModulusCheckApplicationTests {
	
	@Autowired
	private ModulusCheckSvc modulusCheckSvc;
		
	@Test
	public void contextLoads() {		
		assertNotNull(modulusCheckSvc);
	}
}
