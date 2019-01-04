package com.paul.modulus.model;

public class SortKeyWeighting {
	
	private Integer startRange;
	private Integer endRange;
	private ModEnum modEnum;
	private int[] weights;
	
	public Integer getStartRange() {
		return startRange;
	}
	public void setStartRange(Integer startRange) {
		this.startRange = startRange;
	}
	public Integer getEndRange() {
		return endRange;
	}
	public void setEndRange(Integer endRange) {
		this.endRange = endRange;
	}
	public ModEnum getModEnum() {
		return modEnum;
	}
	public void setModEnum(ModEnum modEnum) {
		this.modEnum = modEnum;
	}
	public int[] getWeights() {
		return weights;
	}
	public void setWeights(int[] weights) {
		this.weights = weights;
	}

}
