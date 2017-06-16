package com.choa.notice;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.choa.board.BoardDTO;
import com.choa.ex4.MyAbstractTest;
import com.choa.util.MakePage;
import com.choa.util.PageMaker;


public class NoticeDAOImplTest extends MyAbstractTest {

	@Autowired
	private NoticeDAOImpl noticeDAO;
	
	@Test
	public void test() throws Exception {
		PageMaker pageMaker = new PageMaker(1, 10);
		
		List<BoardDTO> ar = noticeDAO.boardList(pageMaker.getRowMaker());
		assertEquals(0, ar.size());
		
		/*
		NoticeDTO noticeDTO=noticeDAO.noticeView(23);
		System.out.println(noticeDTO.getTitle());
		Assert.assertNotNull(noticeDTO);//데이터가 null인지 아닌지 확인.
		*/
		
	}
	
	/*@Test
	public void test2() throws Exception {
		
		int result = noticeDAO.noticeDelete(24);
		assertEquals(1, result); //내가 예상한 값이 오는지 확인 1은 희망하는 값, 1이 오길 희망하는 result
	}
*/
}
