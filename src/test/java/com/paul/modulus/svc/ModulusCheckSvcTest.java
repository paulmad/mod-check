package com.paul.modulus.svc;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.paul.modulus.dao.ModulusCheckDao;
import com.paul.modulus.exception.ModulusException;
import com.paul.modulus.model.BankInfo;
import com.paul.modulus.model.ModEnum;
import com.paul.modulus.model.SortKeyWeighting;
import com.paul.modulus.model.UkModulusCheckInput;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModulusCheckSvcTest {
	private static final Logger logger = LoggerFactory.getLogger(ModulusCheckSvcTest.class);
	
	@Autowired 
	private ModulusCheckSvc modulusCheckSvc;
	
	@Autowired
	private ModulusCheckDao modulusCheckDao;
	
	@Test
	public void testGetValidBankInfoByCode() {		
		try {
			BankInfo bankInfo = modulusCheckSvc.findBankInfoByCode(113717);						
			logger.debug("test bankinfo"+bankInfo.getUKSortCode());
			assert(bankInfo.getUKSortCode()==113717);
		}catch(NullPointerException npe) {
			fail("Bank info is null");
		}catch(ModulusException me) {
			fail(me.getMessage());
		}
	}
	
	@Test
	public void testNullBankInfo() {
		try {
			BankInfo bankInfo = modulusCheckSvc.findBankInfoByCode(1);			
			assertNull(bankInfo);
		}catch(ModulusException me) {
			fail(me.getMessage());
		}
	}
	
	@Test
	public void testValidMod11() {
		UkModulusCheckInput input = new UkModulusCheckInput();
		input.setSortingCode(107999);
		input.setAccountNumber(88837491);
		try {
			boolean isValid = modulusCheckSvc.isAccountNumberValid(input);
			assertTrue(isValid);
		}catch(ModulusException me) {
			fail(me.getMessage());
		}
	}
	
	@Test
	public void testNotValidMod11() {
		UkModulusCheckInput input = new UkModulusCheckInput();
		input.setSortingCode(107999);
		input.setAccountNumber(88836491);
		try {
			boolean isValid = modulusCheckSvc.isAccountNumberValid(input);
			assertFalse(isValid);
		}catch(ModulusException me) {
			fail(me.getMessage());
		}
	}

	@Test
	public void testValidDblAl() {
		UkModulusCheckInput input = new UkModulusCheckInput();
		input.setSortingCode(202959);
		input.setAccountNumber(63748472);
		try {
			boolean isValid = modulusCheckSvc.isAccountNumberValid(input);
			assertTrue(isValid);
		}catch(ModulusException me) {
			fail(me.getMessage());
		}
	}
	
	@Test
	public void testNotValidDblAl() {
		UkModulusCheckInput input = new UkModulusCheckInput();
		input.setSortingCode(202959);
		input.setAccountNumber(63748482);
		try {
			boolean isValid = modulusCheckSvc.isAccountNumberValid(input);
			assertFalse(isValid);
		}catch(ModulusException me) {
			fail(me.getMessage());
		}
	}
	
	@Before
	public void loadSubList() {
		try {
			modulusCheckDao.loadSubList();
			assert(true);
		}catch(ModulusException me) {
			fail(me.getMessage());
		}
	}	

	@Before
	public void buildTestSortKeyList() {
		List<SortKeyWeighting> skwList = new ArrayList<SortKeyWeighting>();		
		skwList.add(buildSKWEntry(100000, 108099, "MOD11", new int[] {0,0,0,0,0,0,8,7,6,5,4,3,2,1}));		
		skwList.add(buildSKWEntry(200516, 203767, "DBLAL", new int[] {2,1,2,1,2,1,2,1,2,1,2,1,2,1}));		
		skwList.add(buildSKWEntry(401951, 404374, "MOD11", new int[] {0,0,0,0,0,0,0,7,6,5,4,3,2,1}));
		skwList.add(buildSKWEntry(406420, 406420, "MOD10", new int[] {0,0,0,0,0,0,8,7,6,5,4,3,2,1}));
		skwList.add(buildSKWEntry(608301, 608301, "MOD10", new int[] {0,0,0,0,0,0,7,1,3,7,1,3,7,1}));		
		modulusCheckDao.setSortKeyMapList(skwList);		
	}
	
	public SortKeyWeighting buildSKWEntry(int sr, int er, String me, int[] weights) {
		SortKeyWeighting skw = new SortKeyWeighting();		
		skw.setStartRange(sr);
		skw.setEndRange(er);
		skw.setModEnum(ModEnum.valueOf(me));		
		skw.setWeights(weights);		
		return skw;
	}
}
