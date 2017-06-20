package com.choa.notice;


import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import com.choa.board.BoardDTO;
import com.choa.ex4.MyAbstractTest;
import com.choa.util.ListInfo;
import com.choa.util.PageMaker;


public class NoticeDAOImplTest extends MyAbstractTest {

	@Autowired
	private NoticeDAOImpl dao;
	
	@Test
	public void connectionTest() throws Exception{
		ListInfo listInfo = new ListInfo();
		listInfo.setFind("choa");
		listInfo.setSearch("writer");
		int count =dao.boardCount(listInfo);
		
		System.out.println(count);
	
				
		assertNotEquals(0, count);
	}
	
	/*@Test
	public void countTest() throws Exception{
		int count = dao.boardCount();
		assertNotEquals(0, count);
	}*/
}
