package com.paul.modulus.model;

import com.paul.modulus.exception.ModulusException;

public class UkModulusCheckInput {

	private Integer sortingCode;	
	private Integer accountNumber;

	public Integer getSortingCode() {
		return sortingCode;
	}

	public void setSortingCode(Integer sortingCode) {
		this.sortingCode = sortingCode;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String concatSortCandAcctNumAsString() {		
		StringBuilder sb = new StringBuilder();		
		sb.append(sortingCode.toString().toCharArray());
		sb.append(accountNumber.toString().toCharArray());		
		return sb.toString();
	}
	
	public int[] getSortAcctAsIntArray() throws ModulusException {		
		String sortAcctStr = concatSortCandAcctNumAsString();		
		if(sortAcctStr.length()!=14)
			throw new ModulusException("SortCode + AccountNum does not contain correct length of 14");
				
		int[] sortAcct = new int[sortAcctStr.length()];
		for(int i=0; i<sortAcctStr.length(); i++) 
			sortAcct[i] = Character.getNumericValue((sortAcctStr.charAt(i)));
		
		return sortAcct;
	}
}
