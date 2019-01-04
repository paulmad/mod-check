package com.paul.modulus.dao;

import java.util.List;

import com.paul.modulus.exception.ModulusException;
import com.paul.modulus.model.SortKeyWeighting;

public interface ModulusCheckDao {

	public void loadSubList() throws ModulusException;
	
	public boolean sortingCodeSubExists(Integer origVal);
	
	public Integer getSortingCodeSub(Integer origVal);
	
	public List<SortKeyWeighting> findSortKeyWeightingForMod(Integer sortCode);
	
	public void setSortKeyMapList(List<SortKeyWeighting> list);
	
}
