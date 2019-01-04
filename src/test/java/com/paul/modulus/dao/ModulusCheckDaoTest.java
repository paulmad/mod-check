package com.paul.modulus.dao;

import org.junit.Before;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.paul.modulus.exception.ModulusException;
import com.paul.modulus.model.ModEnum;
import com.paul.modulus.model.SortKeyWeighting;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModulusCheckDaoTest {
	
	@Autowired
	ModulusCheckDao modulusCheckDao;
	
	@Before
	public void testLoadSubList() {
		try {
			modulusCheckDao.loadSubList();
		}catch(ModulusException me) {
			fail(me.getMessage());
		}
	}
	
	@Test
	public void testFindSortingCodeVal() {
		Integer origVal = 938616;
		try {
			assertTrue(modulusCheckDao.sortingCodeSubExists(origVal));
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSubSortCode() {
		Integer origVal = 938616;
		try {
			assertTrue(modulusCheckDao.getSortingCodeSub(origVal)==938068);
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSubSortCodeNotFound() {
		Integer origVal = 111111;
		try {
			//code unchanged
			assertTrue(modulusCheckDao.getSortingCodeSub(origVal)==origVal);
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}		

	@Test
	public void findSKW(){
		List<SortKeyWeighting> skwList = modulusCheckDao.findSortKeyWeightingForMod(100000);
		assertNotNull(skwList.get(0));
	}
	
	@Before
	public void buildTestSortKeyList() {
		List<SortKeyWeighting> skwList = new ArrayList<SortKeyWeighting>();		
		skwList.add(buildSKWEntry(100000, 101099, "MOD11", new int[] {0,0,0,0,0,0,8,7,6,5,4,3,2,1}));
		skwList.add(buildSKWEntry(400516, 401054, "DBLAL", new int[] {2,1,2,1,2,1,2,1,2,1,2,1,2,1}));
		skwList.add(buildSKWEntry(401951, 404374, "MOD11", new int[] {0,0,0,0,0,0,0,7,6,5,4,3,2,1}));
		skwList.add(buildSKWEntry(406420, 406420, "MOD10", new int[] {0,0,0,0,0,0,8,7,6,5,4,3,2,1}));
		skwList.add(buildSKWEntry(608301, 608301, "MOD10", new int[] {0,0,0,0,0,0,7,1,3,7,1,3,7,1}));		
		modulusCheckDao.setSortKeyMapList(skwList);
		
	}
	
	public SortKeyWeighting buildSKWEntry(int sr, int er, String me, int[] weights) {
		SortKeyWeighting skw = new SortKeyWeighting();		
		skw.setStartRange(sr);
		skw.setEndRange(er);
		skw.setModEnum(ModEnum.valueOf(me));		
		skw.setWeights(weights);		
		return skw;
	}

}
