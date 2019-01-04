package com.paul.modulus.chk;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.paul.modulus.dao.ModulusCheckDao;
import com.paul.modulus.exception.ModulusException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModChckTest {
	
	@Autowired
	ModulusCheckDao modulusCheckDao;
		
	@Test
	public void testSumString() {
		String val = "123456789";		
		int sum = ModCheck.sumStringChars(val);
		//1+2+3+4+5+6+7+8+9=45
		assert(sum==(1+2+3+4+5+6+7+8+9));			
	}
	
	@Test
	public void testPerformMod10CheckNotValid() {		
		
		int[] acct = {1,6,1,1,0,0,2,7,2,5,2,3,2,1};
		int[] weight = {0,0,6,5,4,3,2,7,6,5,4,3,2,1};
		try {
			boolean isValid = ModCheck.performMod10Check(acct, weight);
			assertFalse(isValid);
			
		}catch(ModulusException me) {
			fail(me.getMessage());
		}		
	}
	
	@Test
	public void testPerformMod10CheckValid() {			
		int[] acct = {0,8,9,9,9,9,6,6,3,7,4,9,5,8};
		int[] weight = {0,0,0,0,0,0,7,1,3,7,1,3,7,1};
		try {
			boolean isValid = ModCheck.performMod10Check(acct, weight);
			assertTrue(isValid);
			
		}catch(ModulusException me) {
			fail(me.getMessage());
		}		
	}
	
	@Test
	public void testPerformMod11Check() {			
		int[] acct = {1,0,7,9,9,9,8,8,8,3,7,4,9,1};
		int[] weight = {0,0,0,0,0,0,8,7,6,5,4,3,2,1};		
		try {
			boolean isValid = ModCheck.performMod11Check(acct, weight);
			assertTrue(isValid);			
		}catch(ModulusException me) {
			fail(me.getMessage());
		}		
	}
	
	@Test
	public void testPerformMod11CheckNotValid() {			
		int[] acct = {1,0,7,9,9,9,8,8,8,3,7,4,9,1};
		int[] weight = {0,0,0,0,0,7,8,7,6,5,4,3,2,1};		
		try {
			boolean isValid = ModCheck.performMod11Check(acct, weight);
			assertFalse(isValid);			
		}catch(ModulusException me) {
			fail(me.getMessage());
		}		
	}
	
	@Test
	public void testPerformModDblAlCheck() {			
		int[] acct = {2,0,2,9,5,9,6,3,7,4,8,4,7,2};
		int[] weight = {2,1,2,1,2,1,2,1,2,1,2,1,2,1};
		try {
			boolean isValid = ModCheck.performDblAlCheck(acct, weight);
			assertTrue(isValid);			
		}catch(ModulusException me) {
			fail(me.getMessage());
		}		
	}

	@Test
	public void testPerformModDblAlCheckNotValid() {			
		int[] acct = {2,0,2,9,5,9,6,3,7,4,8,4,7,2};
		int[] weight = {2,1,2,1,2,1,2,1,2,1,2,2,2,1};
		try {
			boolean isValid = ModCheck.performDblAlCheck(acct, weight);
			assertFalse(isValid);			
		}catch(ModulusException me) {
			fail(me.getMessage());
		}		
	}
}
