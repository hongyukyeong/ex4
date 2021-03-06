package com.choa.freeboard;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.choa.board.BoardDTO;
import com.choa.board.BoardService;
import com.choa.util.MakePage;
import com.choa.util.PageMaker;

@Service
public class FreeboardServiceImpl implements BoardService  {
	
	@Inject
	private FreeboardDAOImpl freeboardDAOImpl;
	
	@Override
	public List<BoardDTO> boardList(int curPage) throws Exception {
		
		PageMaker pageMaker = new PageMaker(curPage);
		MakePage makePage = pageMaker.getMakePage(30);
		
		return freeboardDAOImpl.boardList(pageMaker.getRowMaker());
	}

	@Override
	public BoardDTO boardView(int num) throws Exception {
		// TODO Auto-generated method stub
		return freeboardDAOImpl.boardView(num);
	}

	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return freeboardDAOImpl.boardWrite(boardDTO);
	}

	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return freeboardDAOImpl.boardUpdate(boardDTO);
	}

	@Override
	public int boardDelete(int num) throws Exception {
		// TODO Auto-generated method stub
		return freeboardDAOImpl.boardDelete(num);
	}

	
	public int freeboardReply(FreeboardDTO freeboardDTO) throws Exception{
		return freeboardDAOImpl.boardReply(freeboardDTO);
	}

	
	
}
