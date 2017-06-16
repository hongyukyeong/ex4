package com.choa.ex4;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choa.board.BoardDTO;
import com.choa.notice.NoticeDTO;
import com.choa.notice.NoticeServiceImpl;

@Controller
@RequestMapping(value="/notice/**")
public class NoticeController {
	
	@Inject //inject는 type으로 찾음 bean 에서 만든 것을 넣어라
	private NoticeServiceImpl noticeService; 
	
	
	//list
	@RequestMapping(value="noticeList", method=RequestMethod.GET)
	public String noticeList(Model model, @RequestParam(defaultValue="1") Integer curPage) throws Exception{
		
		List<BoardDTO> ar = noticeService.boardList(curPage);
		model.addAttribute("list", ar);
		model.addAttribute("board","notice");
		return "board/boardList";
	}
	
	
	
	
	//view
	@RequestMapping(value="noticeView", method=RequestMethod.GET)
	public void noticeView(Integer num, Model model) throws Exception{
		
		BoardDTO boardDTO=noticeService.boardView(num);
		model.addAttribute("view", boardDTO);
	}
	
	//writeForm
	@RequestMapping(value="noticeWrite", method=RequestMethod.GET)
	public void noticeWrite(Model model){
		model.addAttribute("path", "Write");
		
	}
	//write
	@RequestMapping(value="noticeWrite", method=RequestMethod.POST)
	public String noticeWrite(NoticeDTO noticeDTO, Model model, RedirectAttributes rd) throws Exception{
		
		int result=noticeService.boardWrite(noticeDTO);
		String message ="FAIL";
		
		if(result>0){
			message="SUCCESS";
		}
		
		
		/*redirect로 보낼 때 message도 보내고 싶고*/
		/**/
		rd.addFlashAttribute("message", message);
		
		return "redirect:noticeList?curPage=2";
		
		/*redirect로 보내는 방법*/
		/*return "redirect:/"; 바로 홈으로 가는 주소 */
		
		
	}
	
	//updateForm
	@RequestMapping(value="noticeUpdate", method=RequestMethod.GET)
	public String noticeUpdate(Integer num, Model model) throws Exception{
		
			BoardDTO boardDTO = noticeService.boardView(num);
			model.addAttribute("dto", boardDTO);
			model.addAttribute("path", "Update");
			
			return "notice/noticeWrite";
	}
	
	//update
	@RequestMapping(value="noticeUpdate" , method=RequestMethod.POST)
	public String noticeUpdate(BoardDTO boardDTO, RedirectAttributes rd, Model model) throws Exception{
		int result= noticeService.boardUpdate(boardDTO);
		String message ="UPDATE FAIL";
		
		if(result>0){
			message="UPDATE SUCCESS";			
		}
		
		rd.addFlashAttribute("message", message);
		return "redirect:noticeList";
		
		//if문 성공 실패
	}
		
	//delete
	@RequestMapping(value="noticeDelete", method=RequestMethod.GET)
	public String noticeDelete(Integer num, RedirectAttributes rd) throws Exception{
		int result=noticeService.boardDelete(num);
		String message ="DELETE FAIL";
		
		if(result>0){
			message="DELETE SUCCESS";
		}
		
		
		
		rd.addFlashAttribute("message", message);
		return "redirect:noticeList";
		//if문 성공 실패
	}
	
	

}