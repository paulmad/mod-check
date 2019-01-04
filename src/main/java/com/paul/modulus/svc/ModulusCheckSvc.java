package com.paul.modulus.svc;

import com.paul.modulus.exception.ModulusException;
import com.paul.modulus.model.BankInfo;
import com.paul.modulus.model.UkModulusCheckInput;

public interface ModulusCheckSvc {
	
	public BankInfo findBankInfoByCode(int code) throws ModulusException;
	
	public boolean isAccountNumberValid(UkModulusCheckInput input) throws ModulusException;
}
