package com.paul.modulus.chk;

import com.paul.modulus.exception.ModulusException;

public class ModCheck {

	private static int modulus10=10;
	private static int modulus11=11;
	
	public static boolean performMod10Check(int[] sortAcctNum, int[] weightVals) throws ModulusException{
		return performCheck (sortAcctNum, weightVals, modulus10);
	}

	public static boolean performMod11Check(int[] sortAcctNum, int[] weightVals) throws ModulusException{
		return performCheck (sortAcctNum, weightVals, modulus11);
	}
	
	private static boolean performCheck(int[] sortAcctNum, int[] weightVals, int modulus) throws ModulusException{
		//only handling base case
		if(sortAcctNum.length!=14 || weightVals.length!=14)
			throw new ModulusException("invalid input for doing modulus check");
		
		int sum=0;		
		for(int i=0; i<14; i++) 
			sum = sum + (sortAcctNum[i] * weightVals[i]);
				
		return (sum % modulus == 0);			
	}
	
	public static boolean performDblAlCheck(int[] sortAcctNum, int[] weightVals) throws ModulusException{
		
		if(sortAcctNum.length!=14 || weightVals.length!=14)
			throw new ModulusException("invalid input for doing modulus check");
					
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<sortAcctNum.length; i++) {			
			sb.append(String.valueOf((sortAcctNum[i] * weightVals[i])));
		}	
				
		return (sumStringChars(sb.toString()) % modulus10 == 0);
	}
	
	public static int sumStringChars(String input) {
		int sum=0;		
		for(int i=0; i<input.length(); i++) {
			sum = sum + Character.getNumericValue((input.charAt(i)));
		}		
		return sum;
	}
		
}
