package com.paul.modulus.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.paul.modulus.exception.ModulusException;
import com.paul.modulus.model.BankInfo;

@Repository
public class BankInfoLocalDao implements BankInfoDao{
	
	private static final Logger logger = LoggerFactory.getLogger(BankInfoLocalDao.class);
	private static Map<Integer, BankInfo> bankInfos = new HashMap<Integer, BankInfo>();

	static {
		try {
			loadBankInfoFromFile();
		}catch(Exception e) {			
			logger.error("error loading file", e);
		}
	}
	
	public static void loadBankInfoFromFile() throws ModulusException{
		Resource resource = new ClassPathResource("/bankinfo.txt");	
		
		if(!resource.exists())
			throw new ModulusException("Resource for bankInfo does not exist");
					
		try {									
			BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));			
			String lineRead = null;
			String[] bankInfoArray;			
			Integer code;
			BankInfo bankInfo;
			while(true) {
				lineRead = br.readLine();
				if(lineRead==null)
					break;
				else{
					bankInfoArray = lineRead.split("\t");										
					code = Integer.valueOf(bankInfoArray[0].trim());					
					bankInfo = new BankInfo();
					bankInfo.setUKSortCode(code.intValue());
					bankInfo.setName(bankInfoArray[1]);
					bankInfo.setStreeAddress(bankInfoArray[2]);
					bankInfo.setCity(bankInfoArray[3]);
					bankInfo.setCountry(bankInfoArray[4]);
					bankInfos.put(code, bankInfo);						
				}
			}				
		}catch(IOException ioe) {
			throw new ModulusException("Error reading bank info text file");
		}catch(NumberFormatException nfe) {
			throw new ModulusException("Parsing int value that is not valid");
		}		
	}
	
	public BankInfo getBankInfoByCode(int code)throws ModulusException {		
		if(bankInfos.isEmpty()) 
			throw new ModulusException("Failed to load BankInfo file");		
		
		BankInfo bankInfo = bankInfos.get(code);				
		return bankInfo;		
	}

	public int getBankInfoCount()throws ModulusException{
		return bankInfos.size();
	}

}
