package com.choa.freeboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.choa.board.BoardDAO;
import com.choa.board.BoardDTO;
import com.choa.notice.NoticeDTO;
import com.choa.util.DBConnector;
import com.choa.util.RowMaker;

@Repository
public class FreeboardDAOImpl implements BoardDAO {

	@Autowired
	private DataSource dataSource;

	
	
	@Override
	public List<BoardDTO> boardList(RowMaker rowMaker) throws Exception {
		List<BoardDTO> ar = new ArrayList<BoardDTO>();
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
	
		String sql ="select * from "
				+ "(select rownum R, F.* from "
				+ "(select * from freeboard order by num desc) F) "
				+ "where R between ? and ?";
		
		st = con.prepareStatement(sql);
		
		st.setInt(1, rowMaker.getStartRow());
		st.setInt(2, rowMaker.getLastRow());
		rs = st.executeQuery();
		
		while(rs.next()){
			
			FreeboardDTO freeboardDTO = new FreeboardDTO();
			
			freeboardDTO.setNum(rs.getInt("num"));
			freeboardDTO.setWriter(rs.getString("writer"));
			freeboardDTO.setTitle(rs.getString("title"));
			freeboardDTO.setContents(rs.getString("contents"));
			freeboardDTO.setHit(rs.getInt("hit"));
			freeboardDTO.setReg_date(rs.getDate("reg_date"));
			freeboardDTO.setRef(rs.getInt("ref"));
			freeboardDTO.setStep(rs.getInt("step"));
			freeboardDTO.setDepth(rs.getInt("depth"));
			ar.add(freeboardDTO);	
		}

	
		DBConnector.disConnect(rs, st, con);
		
		
		return ar;
	}

	@Override
	public BoardDTO boardView(int num) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		FreeboardDTO freeboardDTO = null;
		
		String sql = "select * from freeboard where num=?";
		st = con.prepareStatement(sql);
		st.setInt(1, num);
		
		rs = st.executeQuery();
		
		if(rs.next()){
			freeboardDTO = new FreeboardDTO();			
			freeboardDTO.setNum(rs.getInt("num"));
			freeboardDTO.setWriter(rs.getString("writer"));
			freeboardDTO.setTitle(rs.getString("title"));
			freeboardDTO.setContents(rs.getString("contents"));
			freeboardDTO.setHit(rs.getInt("hit"));
			freeboardDTO.setReg_date(rs.getDate("reg_date"));
		}
		
		//close
		DBConnector.disConnect(rs, st, con);
		
		return freeboardDTO;
	}

	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result=0;
		
		String sql ="insert into freeboard values(notice_seq.nextval,?,?,?,0,sysdate)";
		
		st = con.prepareStatement(sql);
		
		st.setString(1, boardDTO.getWriter());
		st.setString(2, boardDTO.getTitle());
		st.setString(3, boardDTO.getContents());
		result = st.executeUpdate();
		
		DBConnector.disConnect(st, con);

		return result;
	}

	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		int result = 0;
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		
		String sql = "update freeboard set title=?,contents=? where num=?";
		
		
			st = con.prepareStatement(sql);
			st.setString(1, boardDTO.getTitle());
			st.setString(2, boardDTO.getContents());
			st.setInt(3, boardDTO.getNum());
			result = st.executeUpdate();
		
			DBConnector.disConnect(st, con);
		
		
		return result;
	}

	@Override
	public int boardDelete(int num) throws Exception {
		int result = 0;
		Connection con =dataSource.getConnection();
		PreparedStatement st = null;
		
		String sql = "delete freeboard where num=?";
		
			st = con.prepareStatement(sql);
			st.setInt(1, num);
			result = st.executeUpdate();
		
			DBConnector.disConnect(st, con);
		
		
		return result;
	}

	@Override
	public int boardCount() throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String sql = "select nvl(count(num), 0) from freeboard";
		
		st = con.prepareStatement(sql);
		
		rs = st.executeQuery();
		rs.next();
		int result = rs.getInt(1);
		
		DBConnector.disConnect(rs, st, con);
		
		return result;
	}

	@Override
	public void boardHit(int num) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
		
}
