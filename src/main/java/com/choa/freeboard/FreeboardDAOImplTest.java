package com.choa.freeboard;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.choa.board.BoardDTO;
import com.choa.util.PageMaker;

public class FreeboardDAOImplTest {

	private FreeboardDAOImpl dao;
	
	@Test
	public void test() throws Exception {
		PageMaker pageMaker = new PageMaker(1, 20);
		List<BoardDTO> ar = dao.boardList(pageMaker.getRowMaker());
		
		assertNotEquals(0, ar.size());
		
	}
	
	public void countTest() throws Exception{
		int count = dao.boardCount();
		assertNotEquals(0, count);
		
	}

}
