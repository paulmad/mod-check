package com.paul.modulus.dao;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.paul.modulus.exception.ModulusException;
import com.paul.modulus.model.BankInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankInfoDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(BankInfoDaoTest.class);
	
	@Autowired 
	private BankInfoDao bankInfoDao;
	
	@Test
	public void getBankInfoByCode() {
		try {
			BankInfo bankInfo = bankInfoDao.getBankInfoByCode(900017);
			logger.debug(bankInfo.getName()+" "+bankInfo.getCity()+" "+bankInfo.getCountry()+" "+bankInfo.getStreeAddress());
			assert(bankInfo.getUKSortCode()==900017);
		}catch(ModulusException me) {
			fail(me.getMessage());
		}
	}
	
	@Test
	public void testGetNull() {
		try {
			BankInfo bankInfo = bankInfoDao.getBankInfoByCode(-1);
			assertNull(bankInfo);
		}catch(ModulusException me) {
			fail(me.getMessage());
		}		
	}
}
