package com.paul.modulus.dao;

import com.paul.modulus.exception.ModulusException;
import com.paul.modulus.model.BankInfo;

public interface BankInfoDao {
	
	public BankInfo getBankInfoByCode(int code)throws ModulusException;
	
	public int getBankInfoCount() throws ModulusException;	
}
