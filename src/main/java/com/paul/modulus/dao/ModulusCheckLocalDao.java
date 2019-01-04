package com.paul.modulus.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.paul.modulus.exception.ModulusException;
import com.paul.modulus.model.SortKeyWeighting;

@Repository
public class ModulusCheckLocalDao implements ModulusCheckDao{
	
	private static final Logger logger = LoggerFactory.getLogger(ModulusCheckLocalDao.class);
	private static Map<Integer, Integer> scsubListMap = new HashMap<Integer, Integer>();			
	private static List<SortKeyWeighting> sortKeyWeigtingList = new ArrayList<SortKeyWeighting>();
	
	//there can be multiple weighting entries
	@Override
	public List<SortKeyWeighting> findSortKeyWeightingForMod(Integer sortCode) {
		
		 List<SortKeyWeighting> skwList = sortKeyWeigtingList.stream()
				.filter(x -> (sortCode >= x.getStartRange() && sortCode <= x.getEndRange()))
				.collect(Collectors.toList());
		return skwList;
	}

	public void loadSubList() throws ModulusException {		
		Resource resource = new ClassPathResource("/scsubtab.txt");			
		if(!resource.exists())
			throw new ModulusException("Resource for scsubtab does not exist");
					
		try {									
			BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));			
			String lineRead = null;
			String[] subArray;			
			Integer orig, sub;			
			while(true) {
				lineRead = br.readLine();				
				if(lineRead==null)
					break;
				else{
					subArray = lineRead.split(" ");						
					orig = Integer.valueOf(subArray[0].trim());					
					sub = Integer.valueOf(subArray[1].trim());					
					scsubListMap.put(orig, sub);						
				}
			}			
		}catch(IOException ioe) {
			throw new ModulusException("Error reading scsubtab text file");
		}catch(NumberFormatException nfe) {
			throw new ModulusException("Parsing int value in sub list that is not valid");
		}		
	}
	
	
	public boolean sortingCodeSubExists(Integer origVal) {		
		return scsubListMap.containsKey(origVal);
	}

	
	public Integer getSortingCodeSub(Integer origVal) {		
		if (scsubListMap.containsKey(origVal))
			return scsubListMap.get(origVal);
		else 
			return origVal;
	}
	
	public void setSortKeyMapList(List<SortKeyWeighting> list) {
		sortKeyWeigtingList = list;
	}
	
}
