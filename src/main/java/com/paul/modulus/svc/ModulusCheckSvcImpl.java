package com.paul.modulus.svc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paul.modulus.chk.ModCheck;
import com.paul.modulus.dao.BankInfoDao;
import com.paul.modulus.dao.ModulusCheckDao;
import com.paul.modulus.exception.ModulusException;
import com.paul.modulus.model.BankInfo;
import com.paul.modulus.model.ModEnum;
import com.paul.modulus.model.SortKeyWeighting;
import com.paul.modulus.model.UkModulusCheckInput;

@Service
public class ModulusCheckSvcImpl implements ModulusCheckSvc{

	private static final Logger logger = LoggerFactory.getLogger(ModulusCheckSvcImpl.class);
	
	@Autowired
	BankInfoDao bankInfoDao;
	
	@Autowired
	ModulusCheckDao modulusCheckDao;
	
	public BankInfo findBankInfoByCode(int code) throws ModulusException{		
		logger.debug("finding BankInfo for code:"+code);		
		BankInfo bankInfo = bankInfoDao.getBankInfoByCode(code);		
		return bankInfo;
	}

	@Override
	public boolean isAccountNumberValid(UkModulusCheckInput input) throws ModulusException{		
		boolean isValid = true;		
		
		//sub sorting code value
		if(modulusCheckDao.sortingCodeSubExists(input.getSortingCode()))
			input.setSortingCode(modulusCheckDao.getSortingCodeSub(input.getSortingCode()));
			
		//mod check
		List<SortKeyWeighting> sortKeyWeights = modulusCheckDao.findSortKeyWeightingForMod(input.getSortingCode());				
		if(sortKeyWeights!=null && !sortKeyWeights.isEmpty()) {			
			SortKeyWeighting skw = sortKeyWeights.get(0);
			if(skw.getModEnum().equals(ModEnum.MOD10)) {
				isValid = ModCheck.performMod10Check(input.getSortAcctAsIntArray(), skw.getWeights());
			}else if(skw.getModEnum().equals(ModEnum.MOD11)) {
				isValid = ModCheck.performMod11Check(input.getSortAcctAsIntArray(), skw.getWeights());
			}else if(skw.getModEnum().equals(ModEnum.DBLAL)) {
				isValid = ModCheck.performDblAlCheck(input.getSortAcctAsIntArray(), skw.getWeights());
			}
		}				
		return isValid;
	}	
}
